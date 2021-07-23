package com.unt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unt.model.User;
import com.unt.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin()
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public User login(@RequestBody User user) {
		return userService.checkCredentials(user);
	}

	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(@RequestBody User user) {
		if (this.login(user)==null){
			User userObj = userService.registerUser(user);
			return 	"User Created";
		}
		return "User Exists";
	}

	//	user total penalty, no of books taken, no of books returned, no of books not returned, no books is pending
	@RequestMapping(value = "/userDetails/{userid}", method=RequestMethod.GET)
	public String userDetails(@PathVariable("userid") Integer userId){
		System.out.println("userid"+userId);
		return userService.userDetails(userId);
	}

	//librarian user details
	@RequestMapping(value = "/userDetails/lib/{userid}", method=RequestMethod.GET)
	public String libuserDetails(@PathVariable("userid") Integer userId){
		System.out.println("userid"+userId);
		return userService.libuserDetails(userId);
	}

	@RequestMapping(value = "/userDetails", method=RequestMethod.POST)
	public User saveUserDetails(@RequestBody User user){
		User oldUser = userService.getUser(user.getUserId());
		oldUser.setName(user.getName());
		oldUser.setContact(user.getContact());
		oldUser.setEmail(user.getEmail());
		return userService.registerUser(oldUser);
	}

	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public String test() {
		return "connected";
	}

}
