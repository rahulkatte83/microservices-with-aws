package com.user.service.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.user.service.domain.User;
import com.user.service.impl.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	
	/**
     * Logger
     */
    protected Logger logger = Logger.getLogger(UserController.class.getName());

    @Resource
    protected UserService userServiceImpl;

    /**
     * @param name
     * @return A non-null, non-empty collection of users.
     */
    @HystrixCommand(fallbackMethod = "defaultUsers")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("user-service findByName() invoked:{} for {} ", userServiceImpl.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<User> users;
        try {
            users = userServiceImpl.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return users.size() > 0 ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @param id
     * @return A non-null, non-empty collection of users.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultUser")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        logger.info(String.format("user-service findById() invoked:{} for {} ", userServiceImpl.getClass().getName(), id));
        User user;
        try {
            user = userServiceImpl.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARN, "Exception raised findById REST Call {0}", ex);
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add user with the specified information.
     *
     * @param userVO
     * @return A non-null user.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody User user) {
        logger.info(String.format("user-service add() invoked: for %s", user.getName()));
        try {
            userServiceImpl.add(user);
        } catch (Exception ex) {
            logger.log(Level.WARN, "Exception raised add Booking REST Call {0}", ex);
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
    public ResponseEntity<User> defaultUser(Long input) {
        logger.warn("Fallback method for user-service is being used.");
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /**
     * Fallback method for findByName()
     *
     * @param input
     * @return
     */
    public ResponseEntity<Collection<User>> defaultUsers(String input) {
        logger.warn("Fallback method for user-service is being used.");
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
	
}
