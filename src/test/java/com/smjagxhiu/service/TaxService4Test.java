package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TaxService4.class} )
class TaxService4Test {

	@SpyBean
	private TaxService4 service; // we decide which method to mock (logForAudit method)
	
	@MockBean
	private TaxRateService taxRateService;
	
	@Test
	public void tax() {
		BigDecimal income = new BigDecimal(500);  ;
		when(taxRateService.getTaxRate(income)).thenReturn(new BigDecimal(0.05));
		doNothing().when(service).logForAudit(any(),any()); //mock (this is why taxservice is spybean)
		
		BigDecimal tax = service.calculateTax(income); // real method called
		assertThat(tax.round(new MathContext(2))).isEqualTo(new BigDecimal(25));
		verify(taxRateService).getTaxRate(income);
	}
}
