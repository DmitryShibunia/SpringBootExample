package com.dmitryshibunia.DAO;

import com.dmitryshibunia.Model.Employee;
import com.dmitryshibunia.Model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EmployeeDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM employee ORDER BY employee_id";
    private final String GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM employee WHERE employee_id=:id";
    private final String ADD_EMPLOYEE_SQL = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (:first_name, :last_name, :department_id, :job_title, CAST (:gender AS gender_t), :date_of_birth)";
    private final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET first_name =:first_name, last_name =:last_name, department_id =:department_id, job_title =:job_title, gender = CAST (:gender AS gender_t), date_of_birth =:date_of_birth WHERE employee_id = :employee_id";
    private final String DELETE_EMPLOYEE_SQL = "delete from employee where employee_id=?";

    @Autowired
    EmployeeDAO (JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Employee> getAllEmpoyees(){
        return jdbcTemplate.query(GET_ALL_EMPLOYEES_SQL, new EmployeeRowMapper());
    }

    public Employee getEmployeeById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID_SQL, namedParameters, new EmployeeRowMapper());
    }

    public int addEmployee(Employee employee){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("first_name", employee.getFirstName());
        parameterSource.addValue("last_name", employee.getLastName());
        parameterSource.addValue("department_id", employee.getDepartmentId());
        parameterSource.addValue("job_title", employee.getJobTitle());
        parameterSource.addValue("gender", employee.getGender().toString());
        parameterSource.addValue("date_of_birth", employee.getDateOfBirth());

        namedParameterJdbcTemplate.update(ADD_EMPLOYEE_SQL, parameterSource, keyHolder, new String[] { "employee_id" });
       return keyHolder.getKey().intValue();
    }

    public int updateEmployee(Employee employee){

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("employee_id", employee.getEmployeeId());
        parameterSource.addValue("first_name", employee.getFirstName());
        parameterSource.addValue("last_name", employee.getLastName());
        parameterSource.addValue("department_id", employee.getDepartmentId());
        parameterSource.addValue("job_title", employee.getJobTitle());
        parameterSource.addValue("gender", employee.getGender().toString());
        parameterSource.addValue("date_of_birth", employee.getDateOfBirth());

        return namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_SQL, parameterSource);
    }

    public int deleteEmployee(Long id){
        return jdbcTemplate.update(DELETE_EMPLOYEE_SQL, new Object[] { id });
    }

    class EmployeeRowMapper implements RowMapper <Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee(   rs.getLong("employee_id"),
                                                rs.getString("first_name"),
                                                rs.getString("last_name"),
                                                rs.getInt("department_id"),
                                                rs.getString("job_title"),
                                                Gender.valueOf(rs.getString("gender")),
                                                LocalDate.parse(rs.getString("date_of_birth"))
            );
            return employee;
        }
    }

}
