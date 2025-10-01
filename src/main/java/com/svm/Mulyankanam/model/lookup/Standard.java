package com.svm.Mulyankanam.model.lookup;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mediums")
@Data
@NoArgsConstructor
public class Standard {
    @Id
    private String id;
    private String standard;
}

