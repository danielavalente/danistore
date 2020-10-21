package com.store.services;

import org.springframework.mail.SimpleMailMessage;

import com.store.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);

}
