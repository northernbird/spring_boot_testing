package com.smjagxhiu.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaxBracketService {
	
	public List<String> all(){
		return Arrays.asList("LOW","MEDIUM","HIGH");
	}

}
