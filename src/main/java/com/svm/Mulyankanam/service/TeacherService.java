package com.svm.Mulyankanam.service;

import com.svm.Mulyankanam.model.Teacher;
import com.svm.Mulyankanam.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher createTeacher(String medium, String teacherId, String name, String mobileNo,
                                 String email, String profileImage, String sign, LocalDate joiningDate, String password) {
        // Check if teacher already exists by teacherId or email
        Teacher existingTeacher = teacherRepository.findByTeacherId(teacherId);
        if (existingTeacher != null) {
            return existingTeacher; // Teacher already exists, return it
        }

        existingTeacher = teacherRepository.findByEmail(email);
        if (existingTeacher != null) {
            throw new RuntimeException("Teacher with email " + email + " already exists");
        }

        Teacher teacher = new Teacher();
        teacher.setMedium(medium);
        teacher.setTeacherId(teacherId);
        teacher.setName(name);
        teacher.setMobileNo(mobileNo);
        teacher.setEmail(email);
        teacher.setProfileImage(profileImage);
        teacher.setSign(sign);
        teacher.setJoiningDate(joiningDate);
        teacher.setActive(true); // Default to active
        teacher.setPassword(password); // Default password not set
        teacher.setClassTeacherOf(new ArrayList<>());
        teacher.setSubjectTeachingAssignments(new HashMap<>());

        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> getTeacherById(String id) {
        return teacherRepository.findById(id);
    }

    public Optional<Teacher> getTeacherByTeacherId(String teacherId) {
        return Optional.ofNullable(teacherRepository.findByTeacherId(teacherId));
    }

    public Optional<Teacher> getTeacherByEmail(String email) {
        return Optional.ofNullable(teacherRepository.findByEmail(email));
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Teacher> getTeachersByMedium(String medium) {
        return teacherRepository.findByMedium(medium);
    }

    public List<Teacher> getActiveTeachers() {
        return teacherRepository.findByIsActive(true);
    }

    public List<Teacher> getInactiveTeachers() {
        return teacherRepository.findByIsActive(false);
    }

    public Teacher updateTeacher(String id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        // Check if email is being changed and if new email already exists
        if (!teacher.getEmail().equals(teacherDetails.getEmail())) {
            Teacher existingTeacher = teacherRepository.findByEmail(teacherDetails.getEmail());
            if (existingTeacher != null && !existingTeacher.getId().equals(id)) {
                throw new RuntimeException("Teacher with email " + teacherDetails.getEmail() + " already exists");
            }
        }

        teacher.setMedium(teacherDetails.getMedium());
        teacher.setTeacherId(teacherDetails.getTeacherId());
        teacher.setName(teacherDetails.getName());
        teacher.setMobileNo(teacherDetails.getMobileNo());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setProfileImage(teacherDetails.getProfileImage());
        teacher.setSign(teacherDetails.getSign());
        teacher.setJoiningDate(teacherDetails.getJoiningDate());
        teacher.setActive(teacherDetails.isActive());
        teacher.setPassword(teacherDetails.getPassword());

        return teacherRepository.save(teacher);
    }

    // Class Teacher Management
    public Teacher assignAsClassTeacher(String teacherId, String classId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        if (teacher.getClassTeacherOf() == null) {
            teacher.setClassTeacherOf(new ArrayList<>());
        }

        if (!teacher.getClassTeacherOf().contains(classId)) {
            teacher.getClassTeacherOf().add(classId);
            teacherRepository.save(teacher);
        }

        return teacher;
    }

    public Teacher removeAsClassTeacher(String teacherId, String classId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        if (teacher.getClassTeacherOf() != null) {
            teacher.getClassTeacherOf().remove(classId);
            teacherRepository.save(teacher);
        }

        return teacher;
    }

    public List<Teacher> getClassTeachersForClass(String classId) {
        return teacherRepository.findByClassTeacherOfContaining(classId);
    }

    // Subject Teaching Assignment Management
    public Teacher assignClassSubjectToTeacher(String teacherId, String classId, String subjectId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        if (teacher.getSubjectTeachingAssignments() == null) {
            teacher.setSubjectTeachingAssignments(new HashMap<>());
        }

        teacher.getSubjectTeachingAssignments()
                .computeIfAbsent(classId, k -> new ArrayList<>())
                .add(subjectId);

        return teacherRepository.save(teacher);
    }

    public Teacher removeClassSubjectFromTeacher(String teacherId, String classId, String subjectId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        if (teacher.getSubjectTeachingAssignments() != null &&
                teacher.getSubjectTeachingAssignments().containsKey(classId)) {

            teacher.getSubjectTeachingAssignments().get(classId).remove(subjectId);

            // Remove class entry if no subjects left
            if (teacher.getSubjectTeachingAssignments().get(classId).isEmpty()) {
                teacher.getSubjectTeachingAssignments().remove(classId);
            }

            teacherRepository.save(teacher);
        }

        return teacher;
    }

    public Teacher updateClassSubjectAssignmentsOfTeacher(String teacherId, Map<String, List<String>> assignments) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        teacher.setSubjectTeachingAssignments(assignments != null ? assignments : new HashMap<>());
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getTeachersForClass(String classId) {
        return teacherRepository.findByClassTeacherOfContaining(classId);
    }

    public Teacher activateTeacher(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        teacher.setActive(true);
        return teacherRepository.save(teacher);
    }

    public Teacher deactivateTeacher(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        teacher.setActive(false);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    // Password Management Methods (Add to TeacherService)
    public Teacher updatePassword(String teacherId, String newPassword) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        teacher.setPassword(newPassword);
        return teacherRepository.save(teacher);
    }

    public boolean hasPassword(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        return teacher.getPassword() != null && !teacher.getPassword().isEmpty();
    }
}