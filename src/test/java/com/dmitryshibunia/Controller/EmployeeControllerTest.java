package com.dmitryshibunia.Controller;


import com.dmitryshibunia.Model.Employee;
import com.dmitryshibunia.Model.Gender;
import com.dmitryshibunia.SpringBootExampleApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = SpringBootExampleApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getAllEmployeesTest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/1", Employee.class);
        System.out.println(employee.getFirstName());
        Assert.assertNotNull(employee);
    }

    @Test
    public void addEmployeeTest() {
        Employee employee = new Employee( "Peter", "Ivanov", 1, "aa", Gender.MALE,  LocalDate.parse("2000-10-10"));
        ResponseEntity<Integer> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", employee, Integer.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        Assert.assertNotNull(employee);
        restTemplate.delete(getRootUrl() + "/employees/" + id);
        try {
            employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


}
