package test.task.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import test.task.task.dao.AppearDao;
import test.task.task.dao.AppearDaoImpl;
import test.task.task.dao.UserDao;
import test.task.task.dao.UserDaoImpl;
import test.task.task.entity.Appear;
import test.task.task.service.AppearService;
import test.task.task.service.AppearServiceImpl;

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

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public AppearDao getAppearDao() {
        return new AppearDaoImpl(getJdbcTemplate());
    }


    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl(getJdbcTemplate());
    }
}



