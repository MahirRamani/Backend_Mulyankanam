package com.svm.Mulyankanam.model;

import com.svm.Mulyankanam.model.lookup.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "teachers")
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    private String id;
    private String medium;
    private String teacherId;
    private String name;
    private String mobileNo;
    private String email;
    private String profileImage;
    private String sign;
    private LocalDate joiningDate;
    private boolean isActive;
    private String password;

    // Classes where this teacher is the class teacher
    private List<String> classTeacherOf;

    // Subject teaching assignments - class to subjects mapping
    private Map<String, List<String>> subjectTeachingAssignments;
}