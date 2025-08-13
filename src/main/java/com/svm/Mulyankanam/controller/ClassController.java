package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;

    @PostMapping("/add-class")
    public ResponseEntity<com.svm.Mulyankanam.model.Class> createClass(@RequestBody com.svm.Mulyankanam.model.Class newClass) {
        Class savedClass = classService.addClass(newClass.getMedium(), newClass.getStandard(), newClass.getDivision());
        return ResponseEntity.ok(savedClass);
    }

    @PostMapping("/assign-teacher")
    public ResponseEntity assignTeacherToClass(@RequestParam("classId") String classId,@RequestParam("teacherId") String teacherId) {
        Class updatedClass = classService.assignTeacherToClass(classId, teacherId);
        if (updatedClass != null) {
            return ResponseEntity.ok(updatedClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
