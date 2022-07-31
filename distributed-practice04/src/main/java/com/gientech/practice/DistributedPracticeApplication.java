package com.gientech.practice;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gientech.practice.component.MyFeignClient;
import com.gientech.practice.component.MyHttpClient;
import com.gientech.practice.component.MyOkHttpClient;
import com.gientech.practice.model.Coffee;

import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class DistributedPracticeApplication implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient webClient;

	@Autowired
	private MyFeignClient myFeignClient;

	@Autowired
	private MyHttpClient myHttpClient;

	@Autowired
	private MyOkHttpClient myOkHttpClient;

	public static void main(String[] args) {
		SpringApplication.run(DistributedPracticeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// 1.通过RestTemplate
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/one/{id}").build(1);
		ResponseEntity<Coffee> responseEntity = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", responseEntity.getStatusCode(),
				responseEntity.getHeaders().toString());
		log.info("Coffee 1: {}", responseEntity.getBody());

		// 2.通过Feign/OpenFeign
		Coffee coffee2 = myFeignClient.findById(2L);
		log.info("Coffee 2: {}", coffee2);

		// 3.通过HttpClient
		String url = "http://localhost:8080/coffee/one/3";
		String res = myHttpClient.doGet(url);
		ObjectMapper objectMapper = new ObjectMapper();
		Coffee coffee3 = objectMapper.readValue(res, Coffee.class);
		log.info("Coffee 3: {}", coffee3);

		// 4.通过okhttp
		String url4 = "http://localhost:8080/coffee/one/4";
		String res4 = myOkHttpClient.doGet(url4);
		Coffee coffee4 = objectMapper.readValue(res4, Coffee.class);
		log.info("Coffee 4: {}", coffee4);

		// 5.通过WebClient
		CountDownLatch cdl = new CountDownLatch(2);

		webClient.get().uri("/coffee/one/{id}", 5).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(Coffee.class).doOnError(t -> log.error("Error: ", t)).doFinally(s -> cdl.countDown())
				.subscribeOn(Schedulers.single()).subscribe(c -> log.info("Coffee 5: {}", c));

		cdl.await(3000, TimeUnit.MILLISECONDS);
	}

}
