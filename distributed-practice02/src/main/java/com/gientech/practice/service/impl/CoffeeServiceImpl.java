package com.gientech.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gientech.practice.mapper.CoffeeMapper;
import com.gientech.practice.model.Coffee;
import com.gientech.practice.service.CoffeeService;
import com.github.pagehelper.PageInfo;


@Service
public class CoffeeServiceImpl implements CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Transactional
    @Override
    public void insert(Coffee coffee){
        coffeeMapper.insertSelective(coffee);
    }

    @Transactional
    @Override
    public void update(Coffee coffee){
        coffeeMapper.updateByPrimaryKeySelective(coffee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        coffeeMapper.deleteByPrimaryKey(id);
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

    @Override
    public PageInfo selectPage(int pageNum, int pageSize){
        List<Coffee> list = coffeeMapper.findAllWithParamById(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo selectPage(Long[] ids, int pageNum, int pageSize){
        List<Coffee> list = coffeeMapper.selectByPrimaryKeys(ids, pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    public List<Coffee> selectByCondition(Coffee coffee) {
        return coffeeMapper.selectByCondition(coffee);
    }
}
