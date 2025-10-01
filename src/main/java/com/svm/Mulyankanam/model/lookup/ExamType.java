package com.svm.Mulyankanam.model.lookup;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "examType")
@Data
@NoArgsConstructor
public class ExamType {
    @Id
    private String id;
    private String examTypeName;
    private String description;
}