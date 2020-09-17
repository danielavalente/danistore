package com.store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.domain.Category;
import com.store.repositories.CategoryRepository;

@Service
public class ServiceCategory {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null); 
	}
	
	public List<Category> findAll() {
		List<Category> list = repo.findAll();
		return list;
		
	}
}
