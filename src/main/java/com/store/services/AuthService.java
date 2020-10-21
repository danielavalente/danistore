package com.store.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.domain.Client;
import com.store.repositories.ClientRepository;
import com.store.resources.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email not found");
		}

		String newPass = newPassword();
		client.setPassword(bCryptPasswordEncoder.encode(newPass));

		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // Create digit
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // Create upper case letter
			return (char) (rand.nextInt(26) + 65);
		} else { // Create lower case letter 
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
