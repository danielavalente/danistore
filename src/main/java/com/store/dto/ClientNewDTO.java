package com.store.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.store.services.validations.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "This field is mandatory")
	@Length(min = 5, max = 120, message = "Size must be between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message = "This field is mandatory")
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "This field is mandatory")
	private String cpfOrCnpj;
	
	private Integer type;
	
	@NotEmpty(message = "This field is mandatory")
	private String street;
	
	@NotEmpty(message = "This field is mandatory")
	private String number;
	
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message = "This field is mandatory")
	private String zipcode;
	
	@NotEmpty(message = "This field is mandatory")
	private String phone1;
	
	private String phone2;
	private String phone3;
	
	private Integer CityId;
	
	public ClientNewDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return CityId;
	}

	public void setCityId(Integer cityId) {
		CityId = cityId;
	}

}
