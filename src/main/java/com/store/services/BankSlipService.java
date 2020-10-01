package com.store.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.store.domain.BankSlipPayment;

@Service
public class BankSlipService {
	
	// Define due date adding 7 days to instance date
	public void fillBankSlipDate(BankSlipPayment pay, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
	}

}
