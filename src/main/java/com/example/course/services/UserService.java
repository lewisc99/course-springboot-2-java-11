package com.example.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;


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
		
	//	return obj.get(); //will return the object inside the optional.
		return  obj.orElseThrow(() -> new ResourceNotFoundException(id));			//if doesn't have user will return a exception
	
	
	}
	
	
	public User insert(User obj)
	{
		return repository.save(obj);
		//for pattern return the save obj.
	}
	
	
	public void delete(Long id)
	{
		try {
			repository.deleteById(id);
		}
		// to catch the exception catch (RuntimeException e)
		catch (EmptyResultDataAccessException e)
		{
		//	e.printStackTrace(); //to track the exception
			
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	
	public User update(Long id, User obj)
	{
		
		try {
			User entity = repository.getOne(id); //prepare the object.. 
			//tracked to change, and then make a operation with the database
			
			
			updateData(entity,obj);
			return repository.save(entity);
		}
	
	//	catch (RuntimeException e)
		
		catch(EntityNotFoundException e )
		{
		//	e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
		
		
	}

	private void updateData(User entity, User obj) {
		 
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
	
	
}
