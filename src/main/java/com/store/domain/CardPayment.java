package com.store.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.store.domain.enums.PaymentState;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer cardInstallmentsNumber;

	public CardPayment() {
		
	}

	public CardPayment(Integer id, PaymentState state, Order order, Integer number) {
		super(id, state, order);
		this.cardInstallmentsNumber = number;
	}

	public Integer getCardInstallmentsNumber() {
		return cardInstallmentsNumber;
	}

	public void setCardInstallmentsNumber(Integer cardInstallmentsNumber) {
		this.cardInstallmentsNumber = cardInstallmentsNumber;
	}
	
	
	
}
