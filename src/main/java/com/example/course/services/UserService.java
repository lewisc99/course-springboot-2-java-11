package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;


@Service //to be inject in the controller.
public class UserService {

	@Autowired //injection dependency
	private UserRepository repository;
	
	public List<User> findAll()
	{
		return repository.findAll();
	}
	
	public User findById(Long id)
	{
		Optional<User> obj = repository.findById(id);
		
		return obj.get(); //will return the object inside the optional.
	}
	
	
	public User insert(User obj)
	{
		return repository.save(obj);
		//for pattern return the save obj.
	}
	
	
	public void delete(Long id)
	{
		repository.deleteById(id);
	}
	
}
