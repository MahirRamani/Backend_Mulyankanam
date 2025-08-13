package com.svm.Mulyankanam.model;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "classes")
@Data
@NoArgsConstructor
public class Class {
    @Id
    private String id;
    private EMedium medium;
    private EStandard standard;
    private String division;
    private User classTeacher;
}
