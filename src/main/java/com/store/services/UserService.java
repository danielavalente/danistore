package com.store.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.store.security.UserSS;

public class UserService {

	// Method that returns logged in user
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
