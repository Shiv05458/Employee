package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    private EmployeeService service;

    @PostMapping("/v1/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws JsonProcessingException {
            Employee emp = service.createEmployee(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }

    @GetMapping("/v1/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = service.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/v1/byId/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee emp = service.getEmployeeById(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("/v1/update/{id}")
    public ResponseEntity<Employee> updateEmployeeDetailsById(@PathVariable int id, @RequestBody Employee employee) {
        Employee emp = service.updateEmployeeDetails(id, employee);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @DeleteMapping("/v1/delete/byId/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id){
        String result = service.deleteEmployee(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
