package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class) in Junit4
@ExtendWith(SpringExtension.class)
class TaxServiceTest {
	
	private TaxService service;
	
	@BeforeEach
	public void init() {
		service = new TaxService();
	}
	
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
	
	@Test
	public void testAssertJFunctions() {
		assertThat("mic check, 1 2 3, mic check")
			.startsWith("mic check")
			.endsWith("mic check")
			.contains("1","2","3");
		
		final ArrayList<String> people = 
				Lists.newArrayList("tom","mary","elisa");
		assertThat(people)
			.doesNotContainNull()
			.containsAnyOf("mary","tom","tim")
			.doesNotContain("kiwi");	
	}
	
	@Test
	public void testExceptions() {
		Throwable thrown = catchThrowable(() -> 
			service.div(0) );
		assertThat(thrown)
		  .isInstanceOf(ArithmeticException.class)
		  .hasMessageContaining("/ by zero")
		  .withFailMessage("ArithmeticException is not thrown");
	}

}
