package com.unt.model;

import lombok.Data;

import javax.persistence.*;


@Entity(name="user")
public @Data class  User {

	public User(){

	}

	public User(String loginId, String password, String name, String email, String contact, String type) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.type = type;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
	private Integer userId;
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String contact;
	private String type;

}
