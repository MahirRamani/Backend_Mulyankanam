package com.svm.Mulyankanam.errors;

import com.svm.Mulyankanam.model.Student;

public class StudentError {
    private int index;
    private Student student;
    private String errorMessage;

    public StudentError(int index, Student student, String errorMessage) {
        this.index = index;
        this.student = student;
        this.errorMessage = errorMessage;
    }

    // Getters and setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}