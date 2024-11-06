package com.date.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.date.dao.UserRepository;
import com.date.entities.User;
import com.date.service.UserService;

@RestController
public class UserController {
	
	UserService userService;
	

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user")
	public String check()
	{
		return "hum hai Singham";
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user)
	{
		User freshUser = userService.saveUserWithHobbies(user);
		return new ResponseEntity<User>(freshUser,HttpStatus.CREATED);
		
	}

}
