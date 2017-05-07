package com.order.service.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.domain.Order;
import com.order.service.domain.OrderRequest;
import com.order.service.impl.OrderService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

	/**
	 * Logger
	 */
	protected Logger logger = Logger.getLogger(OrderController.class.getName());

	@Resource
	private OrderService orderServiceImpl;

	/**
	 * Place order
	 *
	 * @param order
	 */

	@HystrixCommand(fallbackMethod = "defaultPlaceOrder")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest request) {
		logger.info(String.format("order-service placeOrder() invoked: placed by user id %s for item id %s",
				request.getItemId(), request.getItemId()));
		try {
			orderServiceImpl.placeOrder(request);
		} catch (Exception ex) {
			logger.error("Exception raised add Order service REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@HystrixCommand(fallbackMethod = "defaultFindAllOrders")
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Order>> findAllOrders() {
		logger.info("order-service findAll invoked");
		try {
			return new ResponseEntity<>(orderServiceImpl.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception raised find all Order service REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}


	public ResponseEntity<OrderRequest> defaultPlaceOrder(OrderRequest request) {
		logger.info(String.format(
				"Fallback method for order-service place arder is being used.  args :  user id %s for item id %s",
				request.getUserId(), request.getItemId()));
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Collection<Order>> defaultFindAllOrders() {
		logger.info("Fallback method for order-service find all arder is being used.");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
