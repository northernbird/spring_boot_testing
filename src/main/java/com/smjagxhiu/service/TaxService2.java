package com.smjagxhiu.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService2 {
	
	@Autowired
	private TaxBracketService taxBracketService ;
	
	public String getBracket(int income) {
		if (income < 1000)
			return "LOW";
		else if (income < 5000)
			return "MEDIUM";
		else return "HIGH";
	}
	
	public List<String> allBrackets(){
		return taxBracketService.all();
	}

}
