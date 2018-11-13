package com.topjet;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableFeignClients
@EnableTransactionManagement
@SpringCloudApplication
public class  AdminQuartzApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AdminQuartzApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);

		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	// 配置Feign日志
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
}
