package com.smjagxhiu.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

public class TaxService4 {
	
	@Autowired
	private TaxRateService taxRateService;
	
	public BigDecimal calculateTax(BigDecimal income) {
		BigDecimal taxRate = taxRateService.getTaxRate(income);
		BigDecimal tax = taxRate.multiply(income);
		logForAudit(income,tax);
		return tax;
	}

	public void logForAudit(BigDecimal income, BigDecimal tax) {
		// make network calls to log
	}

}
