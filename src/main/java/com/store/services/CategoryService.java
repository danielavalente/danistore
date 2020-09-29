package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.domain.Category;
import com.store.dto.CategoryDTO;
import com.store.repositories.CategoryRepository;
import com.store.resources.exceptions.DataIntegrityException;
import com.store.resources.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	// Find All
	public List<Category> findAll() {
		return repo.findAll();
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
	
	//Import objects - Pagination
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Conversion
		public Category fromDTO (CategoryDTO objDTO) {
			return new Category(objDTO.getId(), objDTO.getName());
		}

}
