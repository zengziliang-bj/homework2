package com.gientech.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gientech.practice.mapper.CoffeeMapper;
import com.gientech.practice.model.Coffee;
import com.gientech.practice.service.CoffeeService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeServiceImpl implements CoffeeService {
	
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Transactional
    @Override
    public void insert(Coffee coffee){
        try {
			coffeeMapper.insertSelective(coffee);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据添加失败");
		}
    }

    @Transactional
    @Override
    public void update(Coffee coffee){
        try {
			coffeeMapper.updateByPrimaryKeySelective(coffee);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据更新添加失败");
		}
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
			coffeeMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据删除失败");
		}
    }

    @Override
    public Coffee selectOneById(Long id){
        return coffeeMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Coffee> selectList(int pageNum, int pageSize){
        List<Coffee> list = coffeeMapper.findAllWithParamById(pageNum,pageSize);
        return list;
    }

    @Cacheable
    @Override
    public PageInfo selectPage(int pageNum, int pageSize){
        List<Coffee> list = coffeeMapper.findAllWithParamById(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Cacheable
    @Override
    public PageInfo selectPage(Long[] ids, int pageNum, int pageSize){
        List<Coffee> list = coffeeMapper.selectByPrimaryKeys(ids, pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Coffee> testAppendSql(String sql){
        return coffeeMapper.testAppendSql(sql);
    }
}
