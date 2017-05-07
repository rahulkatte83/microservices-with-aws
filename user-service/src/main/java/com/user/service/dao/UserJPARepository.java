package com.user.service.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.user.service.domain.User;

@Transactional
public interface UserJPARepository extends CrudRepository<User, Long>{

	User save(User entity);

	User findOne(Long id);

	Collection<User> findAll();

	Collection<User> findByName(String name) throws Exception;
	
}
