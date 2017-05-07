package com.registration.service.impl;

import java.util.Collection;

import com.registration.service.domain.Registration;



public interface RegistrationService {
    /**
    *
    * @param registration
    * @throws Exception
    */
   public void add(Registration Registration) throws Exception;

   /**
    *
    * @param registration
    * @throws Exception
    */
   public void update(Registration Registration) throws Exception;

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
   public Registration findById(long id) throws Exception;
   
   
   public Collection<Registration> getAll() throws Exception;

}
