package com.svm.Mulyankanam.model.lookup;

import com.svm.Mulyankanam.model.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subjects")
@Data
@NoArgsConstructor
public class Subject {
    @Id
    private String id;
    private String subjectName;
    private String description;
}