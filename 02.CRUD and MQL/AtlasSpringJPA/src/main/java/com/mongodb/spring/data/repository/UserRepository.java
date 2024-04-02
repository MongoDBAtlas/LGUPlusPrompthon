package com.mongodb.spring.data.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mongodb.spring.data.model.User;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	@Autowired
	public List<User> findByssn(String ssn);
	
	@Query("{'ssn': ?0, 'age': {$gt:?1}}")
	List<User> findUserWithAge(String ssn, int age);
}
