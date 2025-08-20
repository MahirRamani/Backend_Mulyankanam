package com.svm.Mulyankanam.service;

import com.svm.Mulyankanam.dto.BulkStudentResponse;
import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import com.svm.Mulyankanam.errors.StudentError;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.Student;
import com.svm.Mulyankanam.model.User;
import com.svm.Mulyankanam.repository.ClassRepository;
import com.svm.Mulyankanam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    private final UserRepository userRepository;

    public Class addClass(EMedium medium, EStandard standard, String division) {
        Class dbClass = classRepository.findByMediumAndStandardAndDivision(medium, standard, division);
        if (dbClass != null) {
            return dbClass; // Class already exists, return it
        }
        Class newClass = new Class();
        newClass.setMedium(medium);
        newClass.setStandard(standard);
        newClass.setDivision(division);
        classRepository.save(newClass);
        return newClass;
    }

    public Class assignTeacherToClass(String classId, String teacherId) {
        User teacher = userRepository.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
        }
        Class dbClass = classRepository.findById(classId).orElse(null);
        if (dbClass == null) {
            throw new IllegalArgumentException("Class not found with ID: " + classId);
        }
        dbClass.setClassTeacher(teacher);
        classRepository.save(dbClass);
        return dbClass;
    }

    public Class addStudentToClass(String classId, Student student) {
        // Validate inputs
        if (!StringUtils.hasText(classId)) {
            throw new IllegalArgumentException("Class ID cannot be null or empty");
        }
        if (student == null || !StringUtils.hasText(student.getRollNo()) || !StringUtils.hasText(student.getName())) {
            throw new IllegalArgumentException("Student roll number and name are required");
        }

        // Find the class
        Class dbClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        // Check if student with same roll number already exists in this class
        if (dbClass.findStudentByRollNo(student.getRollNo()) != null) {
            throw new IllegalArgumentException("Student with roll number " + student.getRollNo() + " already exists in this class");
        }

        // Check if student with same roll number exists in any other class
        Class existingClass = classRepository.findByStudentRollNo(student.getRollNo());
        if (existingClass != null && !existingClass.getId().equals(classId)) {
            throw new IllegalArgumentException("Student with roll number " + student.getRollNo() +
                    " already exists in another class: " + existingClass.getMedium() + " " +
                    existingClass.getStandard() + " " + existingClass.getDivision());
        }

        // Add student to class
        dbClass.addStudent(student);
        return classRepository.save(dbClass);
    }

    // Helper methods
    private boolean isValidStudent(Student student) {
        return student != null &&
                StringUtils.hasText(student.getRollNo()) &&
                StringUtils.hasText(student.getName());
    }

    private Set<String> getAllExistingRollNumbers() {
        // This could be optimized based on your repository structure
        return classRepository.findAll().stream()
                .flatMap(cls -> cls.getStudents().stream())
                .map(Student::getRollNo)
                .collect(Collectors.toSet());
    }

    @Transactional
    public BulkStudentResponse addStudentsToClass(String classId, List<Student> students) {
        // Validate inputs
        if (!StringUtils.hasText(classId)) {
            throw new IllegalArgumentException("Class ID cannot be null or empty");
        }
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("Students list cannot be null or empty");
        }

        // Find the class
        Class dbClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        BulkStudentResponse response = new BulkStudentResponse();
        List<Student> successfullyAdded = new ArrayList<>();
        List<StudentError> errors = new ArrayList<>();

        // Get all existing roll numbers in the system for validation
        Set<String> existingRollNumbers = getAllExistingRollNumbers();
        Set<String> currentClassRollNumbers = dbClass.getStudents().stream()
                .map(Student::getRollNo)
                .collect(Collectors.toSet());

        // Check for duplicates within the input list
        Set<String> inputRollNumbers = new HashSet<>();
        Map<String, Integer> duplicateCount = new HashMap<>();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            // Validate individual student
            if (!isValidStudent(student)) {
                errors.add(new StudentError(i, student, "Student roll number and name are required"));
                continue;
            }

            String rollNo = student.getRollNo();

            // Check for duplicates within input
            if (inputRollNumbers.contains(rollNo)) {
                duplicateCount.put(rollNo, duplicateCount.getOrDefault(rollNo, 1) + 1);
                errors.add(new StudentError(i, student, "Duplicate roll number in input list: " + rollNo));
                continue;
            }
            inputRollNumbers.add(rollNo);

            // Check if student already exists in current class
            if (currentClassRollNumbers.contains(rollNo)) {
                errors.add(new StudentError(i, student, "Student with roll number " + rollNo + " already exists in this class"));
                continue;
            }

            // Check if student exists in another class
            if (existingRollNumbers.contains(rollNo)) {
                Class existingClass = classRepository.findByStudentRollNo(rollNo);
                if (existingClass != null && !existingClass.getId().equals(classId)) {
                    errors.add(new StudentError(i, student, "Student with roll number " + rollNo +
                            " already exists in another class: " + existingClass.getMedium() + " " +
                            existingClass.getStandard() + " " + existingClass.getDivision()));
                    continue;
                }
            }

            // If all validations pass, add to successful list
            successfullyAdded.add(student);
        }

        // Add all valid students to the class
        for (Student student : successfullyAdded) {
            dbClass.addStudent(student);
        }

        // Save the class with new students
        if (!successfullyAdded.isEmpty()) {
            dbClass = classRepository.save(dbClass);
        }

        // Prepare response
        response.setUpdatedClass(dbClass.getClass());
        response.setSuccessfullyAdded(successfullyAdded);
        response.setErrors(errors);
        response.setTotalRequested(students.size());
        response.setTotalAdded(successfullyAdded.size());
        response.setTotalErrors(errors.size());

        return response;
    }
}
