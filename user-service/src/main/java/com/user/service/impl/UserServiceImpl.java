package com.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.service.dao.UserJPARepository;
import com.user.service.domain.User;

@Component
public class UserServiceImpl implements UserService{


	@Autowired
	private UserJPARepository userDaoImpl;
	
	@Override
	public void add(User user) throws Exception {
		userDaoImpl.save(user);
	}

	@Override
	public void update(User user) throws Exception {
	}

	@Override
	public void delete(long id) throws Exception {
	}

	@Override
	public User findById(long id) throws Exception {
		return userDaoImpl.findOne(id);
	}

	@Override
	public Collection<User> findByName(String name) throws Exception {
		return userDaoImpl.findByName(name);
	}

	@Override
	public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		return null;
	}

}
