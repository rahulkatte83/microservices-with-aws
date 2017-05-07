package com.registration.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.service.dao.RegistrationJPARepository;
import com.registration.service.domain.Registration;
import com.registration.service.domain.User;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	
	@Autowired
	private RestTemplate restTemplate;

	
	@Autowired
	private RegistrationJPARepository registrationDao;
	
	@Override
	public void add(Registration registration) throws Exception {
		Map<String, Long> map = new HashMap<>();
		map.put("id", registration.getUserId());
		ResponseEntity<User> user = restTemplate.exchange("http://USERSERVICE/v1/users/{id}", HttpMethod.GET, null, User.class, map);
		registration.setProvider(user.getBody());
		registrationDao.save(registration);
	}

	@Override
	public void update(Registration registration) throws Exception {
	}

	@Override
	public void delete(long id) throws Exception {
		
	}

	@Override
	public Registration findById(long id) throws Exception {
		return registrationDao.findOne(id);
	}

	@Override
	public Collection<Registration> getAll() throws Exception {
		return (Collection<Registration>) registrationDao.findAll();
	}

}
