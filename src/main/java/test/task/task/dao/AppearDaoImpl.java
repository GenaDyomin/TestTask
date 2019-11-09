package test.task.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import test.task.task.entity.Appear;
import test.task.task.mapper.AppearMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppearDaoImpl implements AppearDao {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppearDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Appear> getAllAppear(String username) {
        String sql = "SELECT * FROM appears WHERE username = '" + username + "'";
        return jdbcTemplate.query(sql, new AppearMapper());
    }

    public Appear getAppear(Long id) {
        String sql = "SELECT * FROM appears WHERE id =? ";
        return jdbcTemplate.queryForObject(sql, new AppearMapper(), id);
    }

    @Override
    public Appear insertAppear(Appear appear) {
        final String sql = "INSERT INTO appears (date, type, text, username) VALUES (?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, appear.getDate());
                ps.setString(2, appear.getType());
                ps.setString(3, appear.getText());
                ps.setString(4, appear.getUserName());
                return ps;
            }
        }, holder);
        int id = (int) holder.getKeys().get("id");
        appear.setId((long) id);
        return appear;
    }

    @Override
    public Appear updateAppear(Appear appear) {
        String sql = "UPDATE appears SET date=?, type=?, text=?,username=? WHERE id=?";
        jdbcTemplate.update(sql, appear.getDate(), appear.getType(),
                appear.getText(), appear.getUserName(), appear.getId());
        System.out.println(getAppear(appear.getId()).toString());
        return getAppear(appear.getId());
    }

    @Override
    public void deleteAppear(Long id) {
        String sql = "DELETE FROM appears WHERE id =?";
        jdbcTemplate.update(sql, id);
    }
}
