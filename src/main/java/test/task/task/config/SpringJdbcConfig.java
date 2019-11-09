package test.task.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import test.task.task.dao.AppearDao;
import test.task.task.dao.AppearDaoImpl;
import test.task.task.dao.UserDao;
import test.task.task.dao.UserDaoImpl;


import javax.sql.DataSource;



@Configuration
public class SpringJdbcConfig {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("Gena");
        dataSource.setPassword("pas");
        return dataSource;
    }


    private void createTable(){
        getJdbcTemplate().execute("CREATE TABLE IF NOT EXISTS users" +
                "(" +
                "id text NOT NULL ," +
                "name  text PRIMARY KEY ,\n" +
                "email text not null\n" +
                ");");

        getJdbcTemplate().execute(
                "CREATE TABLE IF NOT EXISTS appears" +
                        " (id serial PRIMARY KEY , " +
                        " date text default CURRENT_DATE not null," +
                        " type text not null," +
                        " text text not null," +
                        " username text REFERENCES users(name) ON UPDATE CASCADE ON DELETE CASCADE" +
                        ")");

    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public AppearDao getAppearDao(){
        return new AppearDaoImpl(getJdbcTemplate());
    }

    @Bean
    public UserDao getUserDao(){
        createTable();
        return new UserDaoImpl(getJdbcTemplate());
    }
}



