package com.gientech.practice.service;

import com.gientech.practice.model.CoffeeOrder;

public interface CoffeeOrderService {
	
    CoffeeOrder selectOneById(Long id);
}
