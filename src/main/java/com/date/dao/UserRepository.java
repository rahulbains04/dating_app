package com.date.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.date.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	
	
}
