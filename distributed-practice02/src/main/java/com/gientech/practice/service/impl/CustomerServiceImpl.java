package com.gientech.practice.service.impl;


import java.util.Date;

import com.gientech.practice.model.Customer;
import com.gientech.practice.service.CustomerService;


public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer selectOneById(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(this.getClass().getName());
        customer.setMember(Thread.currentThread().getName());
        customer.setCreateTime(new Date());
        return customer;
    }
}
