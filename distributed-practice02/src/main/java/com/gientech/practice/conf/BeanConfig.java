package com.gientech.practice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gientech.practice.service.CustomerService;
import com.gientech.practice.service.impl.CustomerServiceImpl;

@Configuration
public class BeanConfig {
    @Bean
    public CustomerService CustomerService(){
        return new CustomerServiceImpl();
    }
}
