package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.dto.BulkStudentResponse;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.Student;
import com.svm.Mulyankanam.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity assignTeacherToClass(@RequestParam("classId") String classId, @RequestParam("teacherId") String teacherId) {
        Class updatedClass = classService.assignTeacherToClass(classId, teacherId);
        if (updatedClass != null) {
            return ResponseEntity.ok(updatedClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{classId}/students")
    public ResponseEntity<Class> addStudentToClass(@PathVariable String classId, @RequestBody Student student) {
        Class updatedClass = classService.addStudentToClass(classId, student);
        return ResponseEntity.ok(updatedClass);
    }

    @PostMapping("/{classId}/students/bulk")
    public ResponseEntity<BulkStudentResponse> addStudentsToClass(
            @PathVariable String classId,
            @RequestBody List<Student> students) {

        BulkStudentResponse response = classService.addStudentsToClass(classId, students);

        if (response.hasErrors()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

}
