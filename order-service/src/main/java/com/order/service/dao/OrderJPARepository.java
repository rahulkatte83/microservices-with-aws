package com.order.service.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.order.service.domain.Order;

@Transactional
public interface OrderJPARepository extends CrudRepository<Order, Long>{
	
	Order save(Order entity);
	
	Order findOne(Long id);

	Collection<Order> findAll();

}
