package com.svm.Mulyankanam.model;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.model.lookup.Standard;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "classes")
@Data
@NoArgsConstructor
public class Class {
    @Id
    private String id;
    private EMedium medium;
    private Standard standard;
    private String division;

    // Array of subject IDs taught in this class
    private List<String> subjectIds;
}
