package com.store.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.domain.Order;
import com.store.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResources {
	
	@Autowired
	private OrderService service;

	//GET METHODS -----------------------
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// POST METHODS -----------------------

	// PUT METHODS -----------------------

	// DELETE METHODS -----------------------

}
