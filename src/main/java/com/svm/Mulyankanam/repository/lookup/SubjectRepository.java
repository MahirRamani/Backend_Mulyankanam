package com.svm.Mulyankanam.repository.lookup;

import com.svm.Mulyankanam.model.lookup.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
    Subject findBySubjectName(String subjectName);
}

