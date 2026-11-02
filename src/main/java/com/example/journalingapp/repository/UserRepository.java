package com.example.journalingapp.repository;

import com.example.journalingapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//@EnableMongoRepositories
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);//2
    void deleteByUsername(String username);
}
