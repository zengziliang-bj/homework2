package com.gientech.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gientech.practice.model.Coffee;
import com.gientech.practice.service.ICoffeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DistributedPracticeController {
	
	@Autowired
	private ICoffeeService coffeeService;

	@GetMapping(value = "/coffee/one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Coffee getCoffeeById(@PathVariable Long id) {
		
		Coffee coffee = coffeeService.selectOneById(id);
		log.info("Coffee {}:", coffee);
		return coffee;
	}

	@GetMapping(value = "/coffee/search", produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public List<Coffee> searchCoffee(@RequestParam String name) {
		
		log.info("---name:{}");
		
		Coffee coffee = new Coffee();
		coffee.setName(name);
		return coffeeService.selectByCondition(coffee);
	}
}
