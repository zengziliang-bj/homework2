package com.gientech.practice.service;

import java.util.List;

import com.gientech.practice.model.Coffee;
import com.github.pagehelper.PageInfo;

public interface CoffeeService {
	
	void insert(Coffee coffee);

	void update(Coffee coffee);

	void delete(Long id);

	Coffee selectOneById(Long id);

	List<Coffee> selectList(int pageNum, int pageSize);

	PageInfo selectPage(int pageNum, int pageSize);

	PageInfo selectPage(Long[] ids, int pageNum, int pageSize);

	List<Coffee> selectByCondition(Coffee coffee);
}
