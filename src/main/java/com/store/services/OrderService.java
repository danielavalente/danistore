package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.domain.Order;
import com.store.repositories.OrderRepository;
import com.store.services.exceptions.ObjectNotFoundException;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	//Exceptions treatment
	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName())); 
	}
	
	public List<Order> findAll() {
		List<Order> list = repo.findAll();
		return list;
	}
}
