package com.smjagxhiu.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


@Service
public class TaxRateService {

	public BigDecimal getTaxRate(BigDecimal income) {
		if (income.doubleValue() < 1000)
			return new BigDecimal(0.05);
		else if (income.doubleValue() < 2000)
			return new BigDecimal(0.1);
		else
			return new BigDecimal(0.15);
	}

}
