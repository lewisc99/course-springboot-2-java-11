package com.example.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;


 
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	
	
	@GetMapping //create a get method
	public ResponseEntity<User> finAll()
	{
		User u = new User(1L,"maria","maria@gmail.com","999999","12345");
		return ResponseEntity.ok().body(u);
	}

}
