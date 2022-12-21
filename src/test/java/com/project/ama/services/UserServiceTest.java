package com.project.ama.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.ama.dto.GetUserResponse;
import com.project.ama.dto.converter.UserDtoConverter;
import com.project.ama.entities.User;
import com.project.ama.exceptions.UserNotFoundException;
import com.project.ama.repositories.UserRepository;

public class UserServiceTest {

	private UserService service;
	private UserRepository userRepository;
	private UserDtoConverter converter;
	
	@BeforeEach
	public void setUp() {
		userRepository = mock(UserRepository.class);
		converter = mock(UserDtoConverter.class);
		service = new UserService(userRepository, converter);
	}
	
	@Test
	public void testFindOneUserById_whenUserIdExists_shouldReturnUser() {
		User actual = new User(0L, "name", "password");
		
		Mockito.when(userRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
		
		User expected = service.findOneUserById(actual.getId());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindOneUserById_whenUserIdDoesNotExists_shouldThrowUserNotFoundException() {
		
		Mockito.when(userRepository.findById(0L)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> service.findOneUserById(0L));
	}
	
	@Test
	public void testGetOneUserById_whenUserIdExists_shouldReturnGetUserResponse() {
		User user = new User(0L, "name", "password");
		GetUserResponse userGetResponse = new GetUserResponse(user);
		
		Mockito.when(userRepository.findById(0L)).thenReturn(Optional.of(user));
		Mockito.when(converter.convertToUserDtoWhenGetResponse(user)).thenReturn(userGetResponse);
		
		GetUserResponse result = service.getOneUserById(0L);
		
		assertEquals(result, userGetResponse);
	}
	
	@Test
	public void testGetOneUserById_whenUserIdDoesNotExists_shouldThrowUserNotFoundException() {
		
		Mockito.when(userRepository.findById(0L)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> service.getOneUserById(0L));
		
		Mockito.verifyNoInteractions(converter);
	}
	
}
