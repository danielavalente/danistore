package com.store.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	
	//Create instance of mailSender with information on application.properties
	@Autowired
	private MailSender mailSender;
	
	//MailSender capable of sending a MimeMessage(html)	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulating email sending...");
		mailSender.send(msg);
		LOG.info("Sent email");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating email sending...");
		javaMailSender.send(msg);
		LOG.info("Sent email");		
	}

}
