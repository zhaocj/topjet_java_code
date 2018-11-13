package com.topjet;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableTransactionManagement
@SpringCloudApplication
@ServletComponentScan
public class  OperationsystemApplication  {

	public static void main(String[] args) {
		SpringApplication.run(OperationsystemApplication.class, args);
	}

	// 配置Feign日志
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

}
