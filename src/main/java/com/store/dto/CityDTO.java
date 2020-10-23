package com.store.dto;

import java.io.Serializable;

import com.store.domain.City;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CityDTO() {
		
	}

	public CityDTO(City city) {
		this.id = city.getId();
		this.name = city.getName();
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
	
}
