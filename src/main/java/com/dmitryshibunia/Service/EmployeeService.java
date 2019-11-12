package com.dmitryshibunia.Service;

import com.dmitryshibunia.DAO.EmployeeDAO;
import com.dmitryshibunia.Model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    EmployeeService (EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmpoyees(){
        LOGGER.info("Call getAllEmployees() method of EmployeeService");
        return employeeDAO.getAllEmpoyees();
    }

    public Employee getEmployeeById(Long id) {
        LOGGER.info("Call getEmployeeById() method of EmployeeService");
        return  employeeDAO.getEmployeeById(id);
    }

    public int addEmployee(Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeService");
        return employeeDAO.addEmployee(employee);
    }

    public int updateEmployee(Employee employee){
        LOGGER.info("Call updateEmployee() method of EmployeeService");
        return employeeDAO.updateEmployee(employee);
    }

    public int deleteEmployee(Long id){
        LOGGER.info("Call deleteEmployee() method of EmployeeService");
        return employeeDAO.deleteEmployee(id);
    }
}
