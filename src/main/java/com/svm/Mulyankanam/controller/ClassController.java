package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.lookup.Standard;
import com.svm.Mulyankanam.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/class")
public class ClassController {

    private final ClassService classService;

    @PostMapping()
    public ResponseEntity<Class> createClass(@RequestBody Class classEntity) {
        Class savedClass = classService.createClass(
                classEntity.getMedium(),
                classEntity.getStandard(),
                classEntity.getDivision(),
                classEntity.getSubjectIds()
        );

        return ResponseEntity.ok(savedClass);
    }

    @GetMapping("/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable String classId) {
        Optional<Class> classEntity = classService.getClassById(classId);

        return classEntity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/medium/{medium}")
    public ResponseEntity<List<Class>> getClassesByMedium(@PathVariable EMedium medium) {
        List<Class> classes = classService.getClassesByMedium(medium);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/standard/{standard}")
    public ResponseEntity<List<Class>> getClassesByStandard(@PathVariable Standard standard) {
        List<Class> classes = classService.getClassesByStandard(standard);
        return ResponseEntity.ok(classes);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Class> updateClass(@PathVariable String classId, @RequestBody Class classDetails) {
        try {
            Class updatedClass = classService.updateClass(classId, classDetails);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{classId}/subjects/{subjectId}")
    public ResponseEntity<Class> addSubjectToClass(@PathVariable String classId, @PathVariable String subjectId) {
        try {
            Class updatedClass = classService.addSubjectToClass(classId, subjectId);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{classId}/subjects/{subjectId}")
    public ResponseEntity<Class> removeSubjectFromClass(@PathVariable String classId, @PathVariable String subjectId) {
        try {
            Class updatedClass = classService.removeSubjectFromClass(classId, subjectId);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable String classId) {
        classService.deleteClass(classId);
        return ResponseEntity.noContent().build();
    }

}
