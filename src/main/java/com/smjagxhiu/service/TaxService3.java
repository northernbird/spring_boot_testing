package com.smjagxhiu.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService3 {
	
	@Autowired
	private TaxRateService taxRateService;
	
	public BigDecimal calculateTax(BigDecimal income) {
		BigDecimal taxRate = taxRateService.getTaxRate(income);
		BigDecimal tax = taxRate.multiply(income);
		return tax;
	}

}
