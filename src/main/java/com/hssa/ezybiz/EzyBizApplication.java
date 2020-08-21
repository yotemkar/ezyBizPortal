package com.hssa.ezybiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class EzyBizApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzyBizApplication.class, args);
	}

}
