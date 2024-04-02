package com.mongodb.spring.template;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.spring.template.component.UserOperations;
import com.mongodb.spring.template.model.Address;
import com.mongodb.spring.template.model.User;

@SpringBootApplication
public class AtlasSpringMongoTemplateApplication {
	
	@Autowired
	private UserOperations userOp;

	public static void main(String[] args) {
		SpringApplication.run(AtlasSpringMongoTemplateApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			User user = new User();
			user.setAge(50);
			user.setEmail("gildong.hong@email.com");
			user.setName("Gildong Hong");
			user.setSsn("123-456-7890");
			user.setDateOfBirth("Jan. 1st");
			
			List<String> Hobbies = new ArrayList();
			Hobbies.add("Martial arts");
			
			
			user.setHobbies(Hobbies);
			
			List<Address> Address = new ArrayList();
			
			Address.add(new Address("office","서울시 강남구 삼성동","코엑스 6","06132"));
			Address.add(new Address("home","서울시 강남구 역삼동","역삼 한국 아파트 101동 101호","06320"));
			
			user.setAddresses(Address);
			
			userOp.insertUser(user);
			//userOp.updateHobby("123-456-7890", "Reading");
			//userOp.updateAddress("123-456-7890", "home", "06230");
			//userOp.deleteBySSN("123-456-7890");
		};
	}
}
