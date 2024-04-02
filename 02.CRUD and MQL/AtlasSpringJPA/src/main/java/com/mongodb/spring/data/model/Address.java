package com.mongodb.spring.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Address {
	private String type;
	private String address;
	private String detail;
	private String postcode;
	
	
	public Address(String type, String address, String detail, String postcode) {
		super();
		this.type = type;
		this.address = address;
		this.detail = detail;
		this.postcode = postcode;
	}
	
	public Address() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
