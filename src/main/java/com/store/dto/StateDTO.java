package com.store.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.store.domain.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "This field is mandatory")
	@Length(min = 5, max = 80, message = "Size must be between 5 and 80 characters")
	private String name;
	
	public StateDTO() {
		
	}

	public StateDTO(State state) {
		this.id = state.getId();
		this.name = state.getName();
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
