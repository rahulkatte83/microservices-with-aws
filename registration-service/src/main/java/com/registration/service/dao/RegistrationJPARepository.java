package com.registration.service.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.registration.service.domain.Registration;

@Transactional
public interface RegistrationJPARepository  extends CrudRepository<Registration, Long>{
	
	Registration save(Registration entity);

	Registration findOne(Long id);
	
	Iterable<Registration> findAll();
	
}
