package com.svm.Mulyankanam.repository;

import com.svm.Mulyankanam.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
