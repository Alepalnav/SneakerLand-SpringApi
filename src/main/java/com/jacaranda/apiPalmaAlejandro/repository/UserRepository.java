package com.jacaranda.apiPalmaAlejandro.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.apiPalmaAlejandro.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByEmail(String email);
	
	List<User> findByUsername(String username);



}
