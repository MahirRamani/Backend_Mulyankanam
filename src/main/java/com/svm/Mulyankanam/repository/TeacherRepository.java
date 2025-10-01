package com.svm.Mulyankanam.repository;

import com.svm.Mulyankanam.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {

    Teacher findByTeacherId(String teacherId);

    Teacher findByEmail(String email);

    Teacher findByMobileNo(String mobileNo);

    List<Teacher> findByMedium(String medium);

    List<Teacher> findByIsActive(boolean isActive);

    List<Teacher> findByClassTeacherOfContaining(String classId);

    @Query("{'subjectTeachingAssignments.key': {$regex: ?0}")
    List<Teacher> findBySubjectTeachingAssignmentsKeyContaining(String key);
}