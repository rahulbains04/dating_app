package com.date.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.date.entities.Hobby;


public interface HobbyRepository extends JpaRepository<Hobby,Integer> {

	Optional<Hobby> findByName(String name);

}
