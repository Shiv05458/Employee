package com.employee.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data

@Document(collection = "Payroll")
public class Payroll {


}
