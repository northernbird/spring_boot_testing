package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TaxService3.class} )
class TaxService3Test {
	
	@Autowired
	private TaxService3 service;
	
	@MockBean
	private TaxRateService taxRateService;
	
	@Test
	public void tax() {
		BigDecimal income = new BigDecimal(500);  ;
		Mockito.when(taxRateService.getTaxRate(income)).thenReturn(new BigDecimal(0.05));
		
		BigDecimal tax = service.calculateTax(income);
		assertThat(tax.round(new MathContext(2))).isEqualTo(new BigDecimal(25));
		Mockito.verify(taxRateService).getTaxRate(income);
	}

}
