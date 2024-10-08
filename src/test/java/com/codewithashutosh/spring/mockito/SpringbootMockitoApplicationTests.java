package com.codewithashutosh.spring.mockito;

import com.codewithashutosh.spring.mockito.entity.User;
import com.codewithashutosh.spring.mockito.repository.UserRepository;
import com.codewithashutosh.spring.mockito.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringbootMockitoApplicationTests {



	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "alice", 31, "btm"), new User(958, "bob", 35, "electronic city")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Ashutosh", 31, "Bangalore")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "ashutosh", 33, "Pune");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "subir", 33, "Pune");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

	/*
	@ExtendWith(SpringExtension.class):

This annotation is part of JUnit 5 and is
used to integrate the Spring TestContext
Framework into JUnit 5 tests.
SpringExtension allows the use
of Spring's features (like dependency injection)
 in your unit tests. Essentially, it makes
 sure that Spring's context is loaded for the test.

@SpringBootTest:

This annotation is used to tell Spring Boot
 to look for a SpringBootApplication or
  @Configuration class and use it to load
  the entire Spring application context.
  It is typically used for integration
  tests where you want to load the full
  application context and test how different
  components interact.

@MockBean:

This annotation is used to create and
inject a mock of a Spring component
(like a repository or service).
It is provided by Spring Boot and used
in tests to mock dependencies, ensuring that
 actual beans are not invoked during unit tests.

verify(repository, times(1)).delete(user);:

This statement is from Mockito,
a popular mocking framework in Java.
 It verifies that the delete method of the
 repository is called exactly once with
  the user object as a parameter. verify
   ensures that the correct interactions
   happen with the mock objects.
	 */
}
