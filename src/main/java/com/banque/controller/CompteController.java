package com.banque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/compte")
public class CompteController {
	
	public int test(){
		 System.out.println("==== in greeting ====");
		return 5;
	}
}
