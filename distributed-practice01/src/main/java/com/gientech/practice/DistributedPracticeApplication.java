package com.gientech.practice;

import java.util.Date;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gientech.practice.model.Coffee;
import com.gientech.practice.service.CoffeeService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.gientech.practice.mapper")
@EnableCaching(proxyTargetClass = true)
public class DistributedPracticeApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(DistributedPracticeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Long[] ids = new Long[] { 1L, 2L, 3L, 4L, 5L };
		PageInfo<Coffee> pageInfo = coffeeService.selectPage(ids, 2, 2);
		log.info("---pageInfo {}---", pageInfo);

		log.info("---读取缓存---");
		pageInfo = coffeeService.selectPage(ids, 2, 2);

		log.info("---读取缓存---");
		pageInfo = coffeeService.selectPage(ids, 2, 2);
		log.info("---读取缓存---");

		pageInfo = coffeeService.selectPage(ids, 2, 2);
		log.info("---读取缓存---");

		Coffee coffee = new Coffee();
		coffee.setName("测试添加");
		coffee.setPrice(Money.of(CurrencyUnit.of("CNY"), 20.0));
		coffee.setCreateTime(new Date());
		coffee.setUpdateTime(new Date());

		log.info("添加 {}", coffee);
		coffeeService.insert(coffee);
		log.info("添加后 {},id={}", coffee, coffee.getId());

		coffee.setPrice(Money.of(CurrencyUnit.of("CNY"), 30.0));
		coffee.setName("测试更新");
		coffee.setUpdateTime(new Date());
		log.info("更新 {}", coffee);
		coffeeService.update(coffee);
		log.info("更新后 {}", coffeeService.selectOneById(coffee.getId()));

		log.info("删除 {}", coffee);
		coffeeService.delete(coffee.getId());
		log.info("删除后 {}", coffeeService.selectOneById(coffee.getId()));
	}

}
