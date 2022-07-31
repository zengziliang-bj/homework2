package com.gientech.practice.service;

import com.gientech.practice.model.Customer;

public interface CustomerService {
    Customer selectOneById(Long id);
}
