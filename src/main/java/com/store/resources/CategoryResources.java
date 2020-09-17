package com.store.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.domain.Category;
import com.store.services.ServiceCategory;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {
	
	@Autowired
	private ServiceCategory service;

//	@RequestMapping(method = RequestMethod.GET)
//	public List<Category> list() {
//
//		Category cat1 = new Category(1, "tech");
//		Category cat2 = new Category(2, "home");
//
//		List<Category> list = new ArrayList<>();
//		list.add(cat1);
//		list.add(cat2);
//
//		return list;
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Category obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

}
