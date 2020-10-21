package com.store.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.store.domain.Client;
import com.store.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Order obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPass);

}
