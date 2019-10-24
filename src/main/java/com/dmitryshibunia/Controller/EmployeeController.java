package com.dmitryshibunia.Controller;

import com.dmitryshibunia.Model.Employee;
import com.dmitryshibunia.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmpoyees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping(value = "/employees")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody void updateTeam(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployee(id);
    }
}
