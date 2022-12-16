package com.project.ama.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ama.dto.UserGetResponse;
import com.project.ama.dto.converter.UserDtoConverter;
import com.project.ama.entities.User;
import com.project.ama.exceptions.UserNotFoundException;
import com.project.ama.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserDtoConverter converter;
	
	public UserService(UserRepository userRepository,
			UserDtoConverter converter) {
		this.userRepository = userRepository;
		this.converter = converter;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	protected User findOneUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new UserNotFoundException("User could not find by id: " + userId));
	}

	public ResponseEntity<Void> createOneUser(User newUser) {
		User user = saveOneUser(newUser);
		
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public UserGetResponse getOneUserById(Long userId) {		
		return converter.convertToUserDtoWhenGetResponse(findOneUserById(userId));
	}

	public ResponseEntity<Void> updateOneUser(Long userId, User newUser) {
		User userToBeUpdated = findOneUserById(userId);
		
		userToBeUpdated.setUserName(newUser.getUserName());
		userToBeUpdated.setPassword(newUser.getPassword());
			
		saveOneUser(userToBeUpdated);
			
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public void deleteOneUserById(Long userId) {
		userRepository.deleteById(userId);
	}
	
}
