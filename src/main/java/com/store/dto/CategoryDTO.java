package com.store.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.store.domain.Category;
import com.store.domain.Product;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "This field is mandatory")
	@Length(min = 5, max = 80, message = "Size must be between 5 and 80 characters")
	private String name;
	
	private List<Product> products = new ArrayList<>();
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.setProducts(obj.getProducts());
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
