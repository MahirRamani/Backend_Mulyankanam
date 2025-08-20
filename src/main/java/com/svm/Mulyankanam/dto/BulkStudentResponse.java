package com.svm.Mulyankanam.dto;

import com.svm.Mulyankanam.errors.StudentError;
import com.svm.Mulyankanam.model.Student;

import java.util.List;

// Response DTOs
public class BulkStudentResponse {
    private Class updatedClass;
    private List<Student> successfullyAdded;
    private List<StudentError> errors;
    private int totalRequested;
    private int totalAdded;
    private int totalErrors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    // Getters and setters
    public Class getUpdatedClass() {
        return updatedClass;
    }

    public void setUpdatedClass(Class updatedClass) {
        this.updatedClass = updatedClass;
    }

    public List<Student> getSuccessfullyAdded() {
        return successfullyAdded;
    }

    public void setSuccessfullyAdded(List<Student> successfullyAdded) {
        this.successfullyAdded = successfullyAdded;
    }

    public List<StudentError> getErrors() {
        return errors;
    }

    public void setErrors(List<StudentError> errors) {
        this.errors = errors;
    }

    public int getTotalRequested() {
        return totalRequested;
    }

    public void setTotalRequested(int totalRequested) {
        this.totalRequested = totalRequested;
    }

    public int getTotalAdded() {
        return totalAdded;
    }

    public void setTotalAdded(int totalAdded) {
        this.totalAdded = totalAdded;
    }

    public int getTotalErrors() {
        return totalErrors;
    }

    public void setTotalErrors(int totalErrors) {
        this.totalErrors = totalErrors;
    }
}
