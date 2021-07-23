package com.unt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.model.User;
import com.unt.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public User checkCredentials(User user) {
		System.out.println(user.getLoginId() + user.getPassword());
		User userobj = userRepository.checkCredentials(user.getLoginId(), user.getPassword());
		System.out.println("userobj "+userobj);
		if (userobj == null) {
			return null;
		}
		return userobj;
	}

	public User registerUser(User user) {
		return userRepository.save(user);
	}

	public User getUser(int userId) {
		return userRepository.getUser(userId);
	}

	public String userDetails(int userId) {
		return userRepository.getUserDetails(userId);
	}

	public String libuserDetails(int userId) {
		return userRepository.getLibUserDetails(userId);
	}
}
