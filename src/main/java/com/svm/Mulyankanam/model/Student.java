package com.svm.Mulyankanam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
@Data
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    private String grNo;
    private String rollNo;
    private String parentMobileNo;
    private String dateOfBirth;
    private String city;
}