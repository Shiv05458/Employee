package com.employee.service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;


    public Employee createEmployee(Employee employee){
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees(){
         return repository.findAll();
    }

    public Employee getEmployeeById(int id){
        return repository.findById(id).get();
    }

    public Employee updateEmployeeDetails(int id, Employee employee){
        Employee emp =  repository.findById(id).get();

        if(emp!=null){
            emp.setAge(employee.getAge());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setCtc(employee.getCtc());
            emp.setOrganisation(employee.getOrganisation());
        }
        return repository.save(emp);
    }

    public String deleteEmployee(int id){
        Employee emp = repository.findById(id).get();
        if(emp!=null) {
            repository.deleteById(id);
            return "Employee deleted successfully with id : "+id;
        }else{
            return "Employee does not exist please give valid Employee Id";
        }
    }
}
