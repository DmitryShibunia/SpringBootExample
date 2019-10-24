package com.dmitryshibunia.DAO;

import com.dmitryshibunia.Model.Employee;
import com.dmitryshibunia.Model.Gender;

import com.dmitryshibunia.config.TestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader= AnnotationConfigContextLoader.class,
                        classes={TestContext.class})
public class EmployeeDAOTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Test
    public void getAllEmployeesTest(){
       List<Employee> employee = employeeDAO.getAllEmpoyees();
        Assert.assertTrue(employee.size()==1);
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee employee = employeeDAO.getEmployeeById((long) 1);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.equals(new Employee((long) 1, "Dmitry", "Shibunia", 1, "aa", Gender.MALE,  LocalDate.parse("2000-10-10"))));
    }

    @Test
    public void addEmployeeTest(){
        Employee employee = new Employee( "Peter", "Ivanov", 1, "aa", Gender.MALE,  LocalDate.parse("2000-10-10"));
        employeeDAO.addEmployee(employee);
        Assert.assertTrue(employeeDAO.getAllEmpoyees().size() == 2);
    }

    @Test
    public void updateEmployeeTest(){
        Employee employee = new Employee( (long) 2,"Vasya", "Petrov", 1, "aa", Gender.MALE,  LocalDate.parse("2001-10-10"));
        employeeDAO.updateEmployee(employee);
        Employee employee1 = employeeDAO.getEmployeeById((long) 2);
        Assert.assertTrue(employee1.equals(employee));

    }

    @Test
    public void deleteEmployeeTest(){
        employeeDAO.deleteEmployee((long) 1);
        Assert.assertTrue(employeeDAO.getAllEmpoyees().size() == 1);
    }
}
