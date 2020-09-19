package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.domain.Category;
import com.store.repositories.CategoryRepository;
import com.store.services.exceptions.ObjectNotFoundException;


@Service
public class ServiceCategory {
	
	@Autowired
	private CategoryRepository repo;
	
	//Exceptions treatment
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName())); 
	}
	
	public List<Category> findAll() {
		List<Category> list = repo.findAll();
		return list;
		
	}
}
