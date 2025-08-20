package com.svm.Mulyankanam.repository;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.enums.EStandard;
import com.svm.Mulyankanam.model.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
    public Class findByMediumAndStandardAndDivision(EMedium medium, EStandard standard, String division);

    // Find class by student roll number
    @Query("{'students.rollNo': ?0}")
    Class findByStudentRollNo(String rollNo);

    // Find all classes by medium and standard
    List<Class> findByMediumAndStandard(EMedium medium, EStandard standard);
}
