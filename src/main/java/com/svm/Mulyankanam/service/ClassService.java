package com.svm.Mulyankanam.service;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.lookup.Standard;
import com.svm.Mulyankanam.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    public Class createClass(EMedium medium, Standard standard, String division, List<String> subjectIds) {
        Class dbClass = classRepository.findByMediumAndStandardAndDivision(medium, standard, division);

        if (dbClass != null) {
            return dbClass; // Class already exists, return it
        }

        Class classEntity = new Class();
        classEntity.setMedium(medium);
        classEntity.setStandard(standard);
        classEntity.setDivision(division);
        classEntity.setSubjectIds(subjectIds != null ? subjectIds : new ArrayList<>());

        classRepository.save(classEntity);

        return classEntity;
    }

    public Optional<Class> getClassById(String classId) {
        return classRepository.findById(classId);
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public List<Class> getClassesByMedium(EMedium medium) {
        return classRepository.findByMedium(medium);
    }

    public List<Class> getClassesByStandard(Standard standard) {
        return classRepository.findByStandard(standard);
    }

    public Class updateClass(String classId, Class classDetails) {
        Class classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + classId));

        classEntity.setMedium(classDetails.getMedium());
        classEntity.setStandard(classDetails.getStandard());
        classEntity.setDivision(classDetails.getDivision());
        classEntity.setSubjectIds(classDetails.getSubjectIds() != null ?
                classDetails.getSubjectIds() : new ArrayList<>());

        return classRepository.save(classEntity);
    }

    public Class addSubjectToClass(String classId, String subjectId) {
        Class classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + classId));

        if (classEntity.getSubjectIds() == null) {
            classEntity.setSubjectIds(new ArrayList<>());
        }

        if (!classEntity.getSubjectIds().contains(subjectId)) {
            classEntity.getSubjectIds().add(subjectId);
            classRepository.save(classEntity);
        }

        return classEntity;
    }

    public Class removeSubjectFromClass(String classId, String subjectId) {
        Class classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + classId));

        if (classEntity.getSubjectIds() != null) {
            classEntity.getSubjectIds().remove(subjectId);
            classRepository.save(classEntity);
        }

        return classEntity;
    }

    public void deleteClass(String classId) {
        classRepository.deleteById(classId);
    }
}
