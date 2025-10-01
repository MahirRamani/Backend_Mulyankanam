package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.model.lookup.ExamType;
import com.svm.Mulyankanam.model.lookup.Standard;
import com.svm.Mulyankanam.model.lookup.Subject;
import com.svm.Mulyankanam.service.lookupService.ExamTypeService;
import com.svm.Mulyankanam.service.lookupService.StandardService;
import com.svm.Mulyankanam.service.lookupService.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lookup")
public class LookupController {

    private final ExamTypeService examTypeService;

    @PostMapping("/examType")
    public ResponseEntity<ExamType> createExamType(@RequestBody ExamType examType) {
        ExamType savedExamtype = examTypeService.createExamType(examType.getExamTypeName(), examType.getDescription());

        return ResponseEntity.ok(savedExamtype);
    }

    @GetMapping("/examType/{examTypeId}")
    public ResponseEntity<ExamType> getExamTypeById(@PathVariable String examTypeId) {
        Optional<ExamType> examType = examTypeService.getExamTypeById(examTypeId);

        return examType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/examType")
    public ResponseEntity<List<ExamType>> getExamTypes() {
        List<ExamType> examTypes = examTypeService.getAllExamTypes();

        return ResponseEntity.ok(examTypes);
    }

    @PutMapping("/examType/{examTypeId}")
    public ResponseEntity<ExamType> updateExamType(@PathVariable String examTypeId, @RequestBody ExamType examTypeDetails) {
        try {
            ExamType updatedExamType = examTypeService.updateExamType(examTypeId, examTypeDetails);
            return ResponseEntity.ok(updatedExamType);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/examType/{examTypeId}")
    public ResponseEntity<Void> deleteExamType(@PathVariable String examTypeId) {
        examTypeService.deleteExamType(examTypeId);

        return ResponseEntity.noContent().build();
    }


    private final StandardService standardService;

    @PostMapping("/standard")
    public ResponseEntity<Standard> createStandard(@RequestBody Standard standard) {
        Standard savedStandard = standardService.createStandard(standard.getStandard());

        return ResponseEntity.ok(savedStandard);
    }

    @GetMapping("/standard/{standardId}")
    public ResponseEntity<Standard> getStandardById(@PathVariable String standardId) {
        Optional<Standard> examType = standardService.getStandardById(standardId);

        return examType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/standard")
    public ResponseEntity<List<Standard>> getStandards() {
        List<Standard> examTypes = standardService.getAllStandards();

        return ResponseEntity.ok(examTypes);
    }

    @PutMapping("/standard/{standardId}")
    public ResponseEntity<Standard> updateStandard(@PathVariable String standardId, @RequestBody Standard standard) {
        try {
            Standard updatedStandard = standardService.updateStandard(standardId, standard);
            return ResponseEntity.ok(updatedStandard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/standard/{standardId}")
    public ResponseEntity<Void> deleteStandard(@PathVariable String examTypeId) {
        standardService.deleteStandard(examTypeId);

        return ResponseEntity.noContent().build();
    }


    private final SubjectService subjectService;

    @PostMapping("/subject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.createSubject(subject.getSubjectName(), subject.getDescription());

        return ResponseEntity.ok(savedSubject);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String subjectId) {
        Optional<Subject> examType = subjectService.getSubjectById(subjectId);

        return examType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subject")
    public ResponseEntity<List<Subject>> getSubjects() {
        List<Subject> examTypes = subjectService.getAllSubjects();

        return ResponseEntity.ok(examTypes);
    }

    @PutMapping("/subject/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable String subjectId, @RequestBody Subject subject) {
        try {
            Subject updatedSubject = subjectService.updateSubject(subjectId, subject);
            return ResponseEntity.ok(updatedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/subject/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String examTypeId) {
        subjectService.deleteSubject(examTypeId);

        return ResponseEntity.noContent().build();
    }
}