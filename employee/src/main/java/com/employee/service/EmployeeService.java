package com.employee.service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper om = new ObjectMapper();

    public Employee createEmployee(Employee employee) throws JsonProcessingException {
        //employee.setId(sequenceGenerator.getSequence(DbSequence.SEQUENCE_NAME));

        List<Employee> emp = repository.findAll();
        List<Employee> result = emp.stream().sorted(Comparator.comparingInt(Employee::getId)).collect(Collectors.toList());

        if(result.size()==0){
            employee.setId(1);
        }else {
            int id = result.get(result.size() - 1).getId() != 0 ? result.get(result.size() - 1).getId() + 1 : 1;
            employee.setId(id);
        }
        kafkaTemplate.send("Annaul_CTC", String.valueOf(employee.getCtc()));
        String str = om.writeValueAsString(employee);
        kafkaTemplate.send("Annual_CTC_Update", str);
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees(){
         return repository.findAll();
    }

    public Employee getEmployeeById(int id){
        return repository.findById(id).get();
    }

    public Employee updateEmployeeDetails(int id, Employee employee) {
        Employee emp =  repository.findById(id).get();

        if(emp!=null){
            emp.setAge(employee.getAge());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setCtc(employee.getCtc());
            emp.setOrganisation(employee.getOrganisation());
        }
        try {
            String str = om.writeValueAsString(emp);
            kafkaTemplate.send("Update_Annaul_CTC", str);
        }catch(Exception e){
            e.printStackTrace();
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
