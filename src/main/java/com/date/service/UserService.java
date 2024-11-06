package com.date.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.date.dao.HobbyRepository;
import com.date.dao.UserRepository;
import com.date.entities.Hobby;
import com.date.entities.User;

import jakarta.transaction.Transactional;

@Service

public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HobbyRepository hobbyRepository;
	
	
	
	public UserService(UserRepository userRepository,HobbyRepository hobbyRepository)
	{
	this.userRepository=userRepository;	
	this.hobbyRepository=hobbyRepository;
	}
	
	
	public User saveUserWithHobbies(User user)
	{
		Set<Hobby> uniqueHobby= new HashSet<>();
		
		for(Hobby hobby : user.getHobbies())
		{
			Optional<Hobby>existingHobby = hobbyRepository.findByName(hobby.getName());
			if(existingHobby.isPresent())
			{
				uniqueHobby.add(existingHobby.get());
			}
			else
			{
				Hobby newHobby=hobbyRepository.save(hobby);
				uniqueHobby.add(newHobby);
			}
		}
		
		user.setHobbies(uniqueHobby);
		return userRepository.save(user);
	}
	
	
	

}
