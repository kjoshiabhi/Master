package com.cashman.services;

import java.util.Collection;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashman.model.Amount;
import com.cashman.model.CashMan;
import com.cashman.model.ServiceResponse;

@RequestMapping(value = "/api/cash")
public interface CashManService {

	@GetMapping(value = "/getCash")
	@ResponseBody
	ServiceResponse<Collection<CashMan>> getTotalAmount();

	@PostMapping(value = "/addCash")
	@ResponseBody
	ServiceResponse<CashMan> addCash(@RequestBody CashMan cash);
	
	@GetMapping(value = "/getCurrentAmount")
	@ResponseBody
	ServiceResponse<Amount> getCurrentAmount();
	
	
	@PostMapping(value = "/withdrawAMount/{amount}")
	@ResponseBody
	ServiceResponse<String> withdrawAMount(@PathVariable int amount) throws MessagingException;
	

}
