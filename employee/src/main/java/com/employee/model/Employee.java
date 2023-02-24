package com.employee.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Document(collection="Employee")
public class Employee {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double ctc;
    private String organisation;



}

