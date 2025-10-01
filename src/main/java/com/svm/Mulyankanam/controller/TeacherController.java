package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.model.Teacher;
import com.svm.Mulyankanam.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.createTeacher(
                    teacher.getMedium(),
                    teacher.getTeacherId(),
                    teacher.getName(),
                    teacher.getMobileNo(),
                    teacher.getEmail(),
                    teacher.getProfileImage(),
                    teacher.getSign(),
                    teacher.getJoiningDate(),
                    teacher.getPassword()
            );
            return ResponseEntity.ok(savedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId) {
        Optional<Teacher> teacher = teacherService.getTeacherById(teacherId);
        return teacher.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teacherId/{teacherId}")
    public ResponseEntity<Teacher> getTeacherByTeacherId(@PathVariable String teacherId) {
        Optional<Teacher> teacher = teacherService.getTeacherByTeacherId(teacherId);
        return teacher.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        Optional<Teacher> teacher = teacherService.getTeacherByEmail(email);
        return teacher.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/medium/{medium}")
    public ResponseEntity<List<Teacher>> getTeachersByMedium(@PathVariable String medium) {
        List<Teacher> teachers = teacherService.getTeachersByMedium(medium);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Teacher>> getActiveTeachers() {
        List<Teacher> teachers = teacherService.getActiveTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Teacher>> getInactiveTeachers() {
        List<Teacher> teachers = teacherService.getInactiveTeachers();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String teacherId, @RequestBody Teacher teacherDetails) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(teacherId, teacherDetails);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Class Teacher Management Endpoints
    @PostMapping("/{teacherId}/classTeacher/{classId}")
    public ResponseEntity<Teacher> assignAsClassTeacher(@PathVariable String teacherId, @PathVariable String classId) {
        try {
            Teacher updatedTeacher = teacherService.assignAsClassTeacher(teacherId, classId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{teacherId}/classTeacher/{classId}")
    public ResponseEntity<Teacher> removeAsClassTeacher(@PathVariable String teacherId, @PathVariable String classId) {
        try {
            Teacher updatedTeacher = teacherService.removeAsClassTeacher(teacherId, classId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/classTeacher/class/{classId}")
    public ResponseEntity<List<Teacher>> getClassTeachersForClass(@PathVariable String classId) {
        List<Teacher> teachers = teacherService.getClassTeachersForClass(classId);
        return ResponseEntity.ok(teachers);
    }

    // Subject Teaching Assignment Endpoints
    // --------------------------------------
    @PostMapping("/{teacherId}/subject/{classId}/{subjectId}")
    public ResponseEntity<Teacher> assignClassSubjectToTeacher(@PathVariable String teacherId,
                                                               @PathVariable String classId,
                                                               @PathVariable String subjectId) {
        try {
            Teacher updatedTeacher = teacherService.assignClassSubjectToTeacher(teacherId, classId, subjectId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{teacherId}/subject/{classId}/{subjectId}")
    public ResponseEntity<Teacher> removeClassSubjectFromTeacher(@PathVariable String teacherId,
                                                                 @PathVariable String classId,
                                                                 @PathVariable String subjectId) {
        try {
            Teacher updatedTeacher = teacherService.removeClassSubjectFromTeacher(teacherId, classId, subjectId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{teacherId}/assignments")
    public ResponseEntity<Teacher> updateClassSubjectAssignmentsOfTeacher(@PathVariable String teacherId,
                                                                          @RequestBody Map<String, List<String>> assignments) {
        try {
            Teacher updatedTeacher = teacherService.updateClassSubjectAssignmentsOfTeacher(teacherId, assignments);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Teacher>> getTeachersForClass(@PathVariable String classId) {
        List<Teacher> teachers = teacherService.getTeachersForClass(classId);
        return ResponseEntity.ok(teachers);
    }

    // Teacher Status Management
    @PutMapping("/{teacherId}/activate")
    public ResponseEntity<Teacher> activateTeacher(@PathVariable String teacherId) {
        try {
            Teacher updatedTeacher = teacherService.activateTeacher(teacherId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{teacherId}/deactivate")
    public ResponseEntity<Teacher> deactivateTeacher(@PathVariable String teacherId) {
        try {
            Teacher updatedTeacher = teacherService.deactivateTeacher(teacherId);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

    // Password Management Endpoints (Add to TeacherController)
    @PutMapping("/teacher/{teacherId}/password")
    public ResponseEntity<Teacher> updatePassword(@PathVariable String teacherId,
                                                  @RequestBody Map<String, String> passwordData) {
        try {
            String newPassword = passwordData.get("password");
            Teacher updatedTeacher = teacherService.updatePassword(teacherId, newPassword);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/teacher/{teacherId}/hasPassword")
    public ResponseEntity<Map<String, Boolean>> checkPasswordExists(@PathVariable String teacherId) {
        try {
            boolean hasPassword = teacherService.hasPassword(teacherId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("hasPassword", hasPassword);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}