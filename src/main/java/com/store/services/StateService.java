package com.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.domain.State;
import com.store.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	// Find All Ordered
	public List<State> findAllByOrderByName() {
		return stateRepository.findAllByOrderByName();
	}
	
	// Find By Id
	public State findById(Integer id) {
		return stateRepository.getOne(id);
	}
	
}
