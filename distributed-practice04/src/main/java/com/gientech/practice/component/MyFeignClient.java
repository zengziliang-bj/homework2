package com.gientech.practice.component;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gientech.practice.model.Coffee;


@Component
@FeignClient(url = "http://localhost:8080", name = "coffee")
public interface MyFeignClient {
    @GetMapping("/coffee/one/{id}")
    Coffee findById(@PathVariable("id") Long id);
}
