package com.svm.Mulyankanam.repository.lookup;

import com.svm.Mulyankanam.model.lookup.ExamType;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ExamTypeRepository extends MongoRepository<ExamType, String> {

    ExamType findByExamTypeName(String examTypeName);
}

