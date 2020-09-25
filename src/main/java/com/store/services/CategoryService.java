package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.store.domain.Category;
import com.store.repositories.CategoryRepository;
import com.store.resources.exceptions.DataIntegrityException;
import com.store.resources.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	// Find All
	public List<Category> findAll() {
		List<Category> list = repo.findAll();
		return list;
	}

	// Find By Id
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	// Save
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// Update
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	// Delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"It's not possible to delete a category that has products.");
		}
	}

}
