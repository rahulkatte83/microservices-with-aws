package com.order.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.order.service.dao.OrderJPARepository;
import com.order.service.domain.Order;
import com.order.service.domain.OrderRequest;
import com.order.service.domain.Registration;
import com.order.service.domain.User;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderJPARepository jpaRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void placeOrder(OrderRequest request) {
		User user = getCustomer(request.getUserId());
		Registration registration = getItem(request.getItemId());
		Order order = prepareOrder(user, registration);
		jpaRepository.save(order);
	}

	private Order prepareOrder(User user, Registration registration) {
		Order order = new Order();
		order.setCustomer(user);
		order.setItem(registration);
		return order;
	}

	private Registration getItem(Long itemId) {
		ResponseEntity<Registration> registration = restTemplate.exchange(
				"http://REGISTRATIONSERVICE/v1/registration/findById?id="+itemId, HttpMethod.GET, null, Registration.class);
		return registration.getBody();
	}

	private User getCustomer(Long userId) {
		Map<String, Long> map = new HashMap<>();
		map.put("id", userId);
		ResponseEntity<User> user = restTemplate.exchange("http://USERSERVICE/v1/users/{id}", HttpMethod.GET, null,
				User.class, map);
		return user.getBody();
	}

	@Override
	public Collection<Order> findAll() {
		return jpaRepository.findAll();
	}

}
