package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@SpringBootTest // tell Spring to create applicationContext
@ContextConfiguration(classes = {TaxService2.class,
		TaxBracketService.class} )
class TaxService2Test {


	
	@Autowired
	private TaxService2 service;
	
	@Autowired
	private TaxBracketService serviceBracket;
	
	@Test
	public void brackets() {
		final String taxBracket = service.getBracket(500);
		assertThat(taxBracket).isEqualTo("LOW");
	}

	@Test
	public void allBrackets() {
		final List<String> allBrackets = service.allBrackets();
		assertThat(allBrackets).isNotEmpty().withFailMessage("Brackets should noe be emtpy");
		assertThat(allBrackets).contains("LOW","MEDIUM","HIGH").withFailMessage("All Brackets are not there");
	}

}
