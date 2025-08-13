package com.svm.Mulyankanam.repository;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import com.svm.Mulyankanam.model.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
    public Class findByMediumAndStandardAndDivision(EMedium medium, EStandard standard, String division);
}
