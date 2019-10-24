package com.dmitryshibunia.config;

import com.dmitryshibunia.DAO.EmployeeDAO;
import com.dmitryshibunia.Service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class ServiceTestContext {
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        Resource initSchema = new ClassPathResource("create-db.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        Resource initSchema1 = new ClassPathResource("test-data.sql");
        DatabasePopulator databasePopulator1 = new ResourceDatabasePopulator(initSchema1);
        DatabasePopulatorUtils.execute(databasePopulator1, dataSource);

        return dataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public  NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public EmployeeDAO employeeDAO(){
        return  new EmployeeDAO();
    }

    @Bean
    public EmployeeService employeeService() {return new EmployeeService();}

}
