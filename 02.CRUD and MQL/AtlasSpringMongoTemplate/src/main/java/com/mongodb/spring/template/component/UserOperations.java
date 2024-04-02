package com.mongodb.spring.template.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.spring.template.model.User;

@Component
public class UserOperations {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public void insertUser(User user)
	{
		mongoTemplate.insert(user);
	}
	
	public void updateHobby(String ssn, String hobby)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("ssn").is(ssn));
		
		Update update = new Update();
		update.push("hobbies", hobby);
		UpdateResult rs = mongoTemplate.updateFirst(query, update, User.class);
		
		System.out.println("Update Result : "+ rs);
	}
	
	public void updateAddress(String ssn, String type, String postalCode)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("ssn").is(ssn));
		query.addCriteria(Criteria.where("addresses.type").is(type));
		
		Update update = new Update();
		update.set("addresses.$.postcode", postalCode);
		
		UpdateResult rs = mongoTemplate.updateFirst(query, update, User.class);
		
		System.out.println("Update Result : "+ rs);
	}
	
	public void findBySSN(String ssn)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("ssn").is(ssn));
		
		List<User> users = mongoTemplate.find(query, User.class);
		
		for(int i=0;i<users.size();i++){
		    System.out.println(users.get(i));
		} 
		
	}
	
	public void deleteBySSN(String ssn)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("ssn").is(ssn));
		
		DeleteResult rs =
		mongoTemplate.remove(query, User.class);
		
		System.out.println("Delete Result : "+ rs);
		
	}

}