package com.svm.Mulyankanam.model;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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

    private List<Student> students = new ArrayList<>();

    // Helper method to add student
    public void addStudent(Student student) {
        if (this.students == null) {
            this.students = new ArrayList<>();
        }
        this.students.add(student);
    }

    // Helper method to remove student by roll number
    public boolean removeStudent(String rollNo) {
        if (this.students == null) {
            return false;
        }
        return this.students.removeIf(student -> student.getRollNo().equals(rollNo));
    }

    // Helper method to find student by roll number
    public Student findStudentByRollNo(String rollNo) {
        if (this.students == null) {
            return null;
        }
        return this.students.stream()
                .filter(student -> student.getRollNo().equals(rollNo))
                .findFirst()
                .orElse(null);
    }
}
