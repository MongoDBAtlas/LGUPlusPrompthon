package com.mongodb.spring.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.spring.data.repository.UserRepository;
import com.mongodb.spring.data.model.Address;
import com.mongodb.spring.data.model.User;

@SpringBootApplication
public class AtlasSpringDataApplication {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AtlasSpringDataApplication.class, args);
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
			
			
			userRepository.insert(user);
			
			/*
			List<User> rs = userRepository.findByssn("123-456-7890");
			
			for (int i=0;i<rs.size();i++)
			{
				System.out.println(rs.get(i));
			}
			*/
			
			List<User> rs = userRepository.findUserWithAge("123-456-7890",40);
			
			for (int i=0;i<rs.size();i++)
			{
				System.out.println(rs.get(i));
			}
			
			//userOp.insertUser(user);
			//userOp.updateHobby("123-456-7890", "Reading");
			//userOp.updateAddress("123-456-7890", "home", "06230");
			//userOp.deleteBySSN("123-456-7890");
		};
	}

}
