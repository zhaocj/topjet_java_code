package com.topjet.cloud;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
public class TopjetCServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopjetCServiceOrderApplication.class, args);
	}

	// 配置Feign日志
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
}
