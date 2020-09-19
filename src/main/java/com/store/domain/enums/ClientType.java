package com.store.domain.enums;

public enum ClientType {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (ClientType client : ClientType.values()) {
			if (cod.equals(client.getCod())) {
				return client;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
		
}
