package com.svm.Mulyankanam.repository;

import com.svm.Mulyankanam.enums.EMedium;
import com.svm.Mulyankanam.model.Class;
import com.svm.Mulyankanam.model.lookup.Standard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {

    Class findByMediumAndStandardAndDivision(EMedium medium, Standard standard, String division);

    List<Class> findByMedium(EMedium medium);

    List<Class> findByStandard(Standard standard);
}
