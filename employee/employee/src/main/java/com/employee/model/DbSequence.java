package com.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection = "db_sequence")
public class DbSequence {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    private String id;
    private int seqNo;
}
