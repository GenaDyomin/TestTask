package test.task.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import test.task.task.entity.User;
import test.task.task.mapper.AppearMapper;
import test.task.task.mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id =? ";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @Override
    public User insertUser(User user) {
        final String sql = "INSERT INTO users (id ,name, email) VALUES (?,?,?)";
        jdbcTemplate.update(sql, user.getId(),user.getName(),user.getEmail());
        return user;

    }
}
