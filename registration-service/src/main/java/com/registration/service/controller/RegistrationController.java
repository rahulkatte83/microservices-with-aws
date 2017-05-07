package com.registration.service.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.registration.service.domain.Registration;
import com.registration.service.impl.RegistrationService;

@RestController
@RequestMapping("/v1/registration")
public class RegistrationController {

	/**
	 * Logger
	 */
	protected Logger logger = Logger.getLogger(RegistrationController.class.getName());

	@Resource
	protected RegistrationService registrationServiceImpl;
	
	
	
	@HystrixCommand(fallbackMethod = "defaultRegistration")
	@RequestMapping(path ="/findById", method = RequestMethod.GET)
	public ResponseEntity<Registration> findById(@RequestParam("id") Long id) {
		logger.info(String.format("registration-service findById() invoked: for {} ", id));
		Registration registration;
		try {
			registration = registrationServiceImpl.findById(id);
		} catch (Exception ex) {
			logger.error("registration-service Exception raised findById() REST Call", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return registration != null ? new ResponseEntity<>(registration, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@HystrixCommand(fallbackMethod = "defaultRegistrations")
	@RequestMapping(path ="/findAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Registration>> findAll() {
		logger.info(String.format("user-service find all invoked:{}  ", registrationServiceImpl.getClass().getName()));
		Collection<Registration> registration;
		try {
			registration = registrationServiceImpl.getAll();
		} catch (Exception ex) {
			logger.error("Exception raised findByName REST Call", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return registration != null ? new ResponseEntity<>(registration, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Add user with the specified information.
	 *
	 * @param registration
	 * @return registration vo.
	 */
	@RequestMapping(path ="/add", method = RequestMethod.POST)
	public ResponseEntity<Registration> add(@RequestBody Registration registration) {
		logger.info(String.format("user-service add() invoked: %s for %s", registrationServiceImpl.getClass().getName(),
				registration.getItem()));
		try {
			registrationServiceImpl.add(registration);
		} catch (Exception ex) {
			logger.log(Level.WARN, "Exception raised add Registration REST Call {0}", ex);
		
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Fallback method for findById()
	 *
	 * @param input
	 * @return
	 */
	public ResponseEntity<Registration> defaultRegistration(Long input) {
		logger.warn("Fallback method for registration-service findById() is being used.");
		System.out.println(" Default method for get registration getting called  ##### fallback");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	/**
	 * Fallback method for findAll()
	 *
	 * @param input
	 * @return
	 */
	public ResponseEntity<Collection<Registration>> defaultRegistrations() {
		logger.warn("Fallback method for registration-service findAll() is being used.");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	
}
