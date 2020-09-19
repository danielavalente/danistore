package com.store.domain.enums;

public enum PaymentState {
	
	PENDING (1, "Pending"),
	PAID (2, "Paid"),
	CANCELED (3, "Canceled");
	
	private int cod;
	private String description;
	
	private PaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentState toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (PaymentState payment : PaymentState.values()) {
			if (cod.equals(payment.getCod())) {
				return payment;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}

}
