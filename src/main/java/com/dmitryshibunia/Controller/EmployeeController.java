package com.dmitryshibunia.Controller;

import com.dmitryshibunia.Model.Employee;
import com.dmitryshibunia.Service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private final Logger LOGGER = LogManager.getLogger();
    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("Call getAllEmployees() method of EmployeeController");
        return employeeService.getAllEmpoyees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        LOGGER.info("Call getEmployeeById() method of EmployeeController");
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addEmployee(@RequestBody Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeController");
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTeam(@RequestBody Employee employee){
        LOGGER.info("Call updateTeam() method of EmployeeController");
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id){
        LOGGER.info("Call deleteEmployee() method of EmployeeController");
        employeeService.deleteEmployee(id);
    }
}
