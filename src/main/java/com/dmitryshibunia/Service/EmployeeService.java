package com.dmitryshibunia.Service;

import com.dmitryshibunia.DAO.EmployeeDAO;
import com.dmitryshibunia.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public List<Employee> getAllEmpoyees(){
        return employeeDAO.getAllEmpoyees();
    }

    public Employee getEmployeeById(Long id) {return  employeeDAO.getEmployeeById(id); }

    public int addEmployee(Employee employee){
        return employeeDAO.addEmployee(employee);
    }

    public int updateEmployee(Employee employee){
        return employeeDAO.updateEmployee(employee);
    }

    public int deleteEmployee(Long id){
        return employeeDAO.deleteEmployee(id);
    }
}
