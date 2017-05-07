package com.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.user.service.domain.User;

public interface UserService {
	
    /**
    *
    * @param booking
    * @throws Exception
    */
   public void add(User user) throws Exception;

   /**
    *
    * @param booking
    * @throws Exception
    */
   public void update(User user) throws Exception;

   /**
    *
    * @param id
    * @throws Exception
    */
   public void delete(long id) throws Exception;

   /**
    *
    * @param restaurantId
    * @return
    * @throws Exception
    */
   public User findById(long id) throws Exception;

   /**
    *
    * @param name
    * @return
    * @throws Exception
    */
   public Collection<User> findByName(String name) throws Exception;

   /**
    *
    * @param name
    * @return
    * @throws Exception
    */
   public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

}
