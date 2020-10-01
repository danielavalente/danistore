package com.store.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.store.domain.enums.PaymentState;

@Entity
@JsonTypeName("bankSlipPayment")
public class BankSlipPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern= "dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern= "dd/MM/yyyy")
	private Date payDate;
	
	public BankSlipPayment() {
		
	}

	public BankSlipPayment(Integer id, PaymentState state, Order order, Date duedate, Date payDate) {
		super(id, state, order);
		this.dueDate = duedate;
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	
	

}
