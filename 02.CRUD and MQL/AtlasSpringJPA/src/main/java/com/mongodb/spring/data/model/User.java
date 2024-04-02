package com.mongodb.spring.data.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="Users")
public class User {
	@Id
    private ObjectId id;
	private String ssn;
    public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	private String name;
  	private int age;
  	private String email;
  	
  	private List<String> hobbies;
  	private List<Address> addresses;
  	
  	private String DateOfBirth;
  	
  	
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User()
	{
		super();
	}
	
	
	public User(String ssn, String name, int age, String email, List<String> hobbies, List<Address> addresses,
			String dateOfBirth) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.age = age;
		this.email = email;
		this.hobbies = hobbies;
		this.addresses = addresses;
		DateOfBirth = dateOfBirth;
	}
	
	
	public User(String name, int age, String email) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", ssn=" + ssn + ", name=" + name + ", age=" + age + ", email=" + email + ", hobbies="
				+ hobbies + ", addresses=" + addresses + ", DateOfBirth=" + DateOfBirth + "]";
	}
  	
}