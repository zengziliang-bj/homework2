package com.gientech.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gientech.practice.mapper.CoffeeMapper;
import com.gientech.practice.model.Coffee;
import com.gientech.practice.service.ICoffeeService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
	
	@Autowired
	private CoffeeMapper coffeeMapper;

	@Transactional
	@Override
	public void insert(Coffee coffee) {
		try {
			coffeeMapper.insertSelective(coffee);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据添加失败");
		}
	}

	@Transactional
	@Override
	public void update(Coffee coffee) {
		try {
			coffeeMapper.updateByPrimaryKeySelective(coffee);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据更新失败");
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			coffeeMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据删除失败");
		}
	}

	@Override
	public Coffee selectOneById(Long id) {
		return coffeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Coffee> selectList(int pageNum, int pageSize) {
		List<Coffee> list = coffeeMapper.findAllWithParamById(pageNum, pageSize);
		return list;
	}

	@Override
	public PageInfo selectPage(int pageNum, int pageSize) {
		List<Coffee> list = coffeeMapper.findAllWithParamById(pageNum, pageSize);
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}

	@Override
	public PageInfo selectPage(Long[] ids, int pageNum, int pageSize) {
		List<Coffee> list = coffeeMapper.selectByPrimaryKeys(ids, pageNum, pageSize);
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}


	@Override
	public List<Coffee> testAppendSql(String sql) {
		return coffeeMapper.testAppendSql(sql);
	}

	@Override
	public List<Coffee> selectByCondition(Coffee coffee) {
		return coffeeMapper.selectByCondition(coffee);
	}
}
