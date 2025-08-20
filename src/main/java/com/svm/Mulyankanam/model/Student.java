package com.svm.Mulyankanam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
@Data
@NoArgsConstructor
public class Student {
    private String rollNo;
    private String name;
}