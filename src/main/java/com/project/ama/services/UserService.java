package com.project.ama.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ama.entities.User;
import com.project.ama.exceptions.UserNotFoundException;
import com.project.ama.repositories.UserRepository;
import com.project.ama.responses.UserGetResponse;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public ResponseEntity<Void> createOneUser(User newUser) {
		User user = this.saveOneUser(newUser);
		
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public UserGetResponse getOneUser(Long userId) {
		User user = this.getOneUserById(userId);
		
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		return new UserGetResponse(user);
	}

	public ResponseEntity<Void> updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isPresent()) {
			User userToBeUpdated = user.get();
			
			userToBeUpdated.setUserName(newUser.getUserName());
			userToBeUpdated.setPassword(newUser.getPassword());
			
			this.saveOneUser(userToBeUpdated);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public void deleteOneUserById(Long userId) {
		userRepository.deleteById(userId);
	}
	
}
