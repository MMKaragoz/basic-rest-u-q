package com.project.ama.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ama.dto.GetUserResponse;
import com.project.ama.entities.User;
import com.project.ama.services.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// -- "/" --
	
	@GetMapping
	public List<GetUserResponse> getAllUsers() {
		return userService.getAllUsers().stream().map(user -> new GetUserResponse(user)).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<Void> createOneUser(@RequestBody User newUser) {
		return userService.createOneUser(newUser);
	}
	
	// -- "/{userId}" --
	
	@GetMapping("/{userId}")
	public GetUserResponse getOneUser(@PathVariable Long userId) {
		return userService.getOneUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateOneUser(userId, newUser);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUserById(@PathVariable Long userId) {
		userService.deleteOneUserById(userId);
	}	
	
}
