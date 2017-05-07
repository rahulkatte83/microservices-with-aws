package com.order.service.impl;

import java.util.Collection;

import com.order.service.domain.Order;
import com.order.service.domain.OrderRequest;

public interface OrderService {
	
	void placeOrder(OrderRequest request);
	
	Collection<Order> findAll();

}
