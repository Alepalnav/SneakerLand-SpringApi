package com.jacaranda.apiPalmaAlejandro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacaranda.apiPalmaAlejandro.dto.UserDTO;
import com.jacaranda.apiPalmaAlejandro.exception.ExceptionCredentialNotValid;
import com.jacaranda.apiPalmaAlejandro.model.User;
import com.jacaranda.apiPalmaAlejandro.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<User> result =  userRepository.findByUsername(username);
		// Como busco por username aunque la id se la primary key
		// comprueba que no hay dos usuario con el mismo usernam, si lo hay
		// devuelvo error.
		if (result != null && result.size()== 1)
			return result.get(0);
		else
			throw new ExceptionCredentialNotValid("Usuario no encontrado username: " + username);
		
	}
	
	public List<UserDTO> getAll(){
		List<User>list = userRepository.findAll();
        return UserDTO.convertUserToDTO(list);	   
	}
	
	public User addUser(User userDTO) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(encodedPassword);
		String username = userDTO.getUsername();
		try {
			User user = new User(userDTO.getId(),userDTO.getUsername(),userDTO.getName(),userDTO.getEmail(), userDTO.getPassword(), userDTO.getAddress(), userDTO.getRole()); 
			userRepository.save(user);
			return user;			
		}catch(Exception e) {
			throw new ExceptionCredentialNotValid(e.getMessage());
		}
	}
	
	public List<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
