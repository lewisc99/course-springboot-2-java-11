 package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.course.entities.Order;



@Repository //always when need to use inject dependency
//you need to put this prefixix @ (name of the Dependency)
//and in the class that will use dependency inject.
//put @Autowired, in this class don't necessary need to add
//@repository because already implements JapRepository.
public interface OrderRepository extends JpaRepository<Order,Long> {
	
	
	

}
