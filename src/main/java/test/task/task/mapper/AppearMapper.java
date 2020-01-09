package test.task.task.mapper;

import test.task.task.entity.Appear;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class AppearMapper implements RowMapper<Appear> {


    @Override
    public Appear mapRow(ResultSet resultSet, int i) throws SQLException {
        Appear appear = new Appear();
        appear.setId(resultSet.getLong("id"));
        appear.setDate(resultSet.getString("date"));
        appear.setType(resultSet.getString("type"));
        appear.setText(resultSet.getString("text"));
        appear.setUserName(resultSet.getString("username"));
        appear.setLattitude(Double.parseDouble(resultSet.getString("lattitude")));
        appear.setLongtitude(Double.parseDouble(resultSet.getString("longtitude")));
        return appear;
    }
}
