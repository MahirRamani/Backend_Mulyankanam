package com.svm.Mulyankanam.repository.lookup;

import com.svm.Mulyankanam.model.lookup.Standard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandardRepository extends MongoRepository<Standard, String> {
    Standard findByStandard(String standard);
}

