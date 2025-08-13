package com.svm.Mulyankanam.service;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.User;
import com.svm.Mulyankanam.repository.ClassRepository;
import com.svm.Mulyankanam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    private final UserRepository userRepository;

    public Class addClass(EMedium medium, EStandard standard, String division) {
        Class dbClass = classRepository.findByMediumAndStandardAndDivision(medium, standard, division);
        if(dbClass != null) {
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
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
        }
        Class dbClass = classRepository.findById(classId).orElse(null);
        if(dbClass == null) {
            throw new IllegalArgumentException("Class not found with ID: " + classId);
        }
        dbClass.setClassTeacher(teacher);
        classRepository.save(dbClass);
        return dbClass;
    }
}
