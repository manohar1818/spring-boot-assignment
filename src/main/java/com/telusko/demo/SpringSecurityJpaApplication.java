package com.telusko.demo;

import com.telusko.demo.controller.HomeResource;
import com.telusko.demo.repository.UserRepository;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication
//@ComponentScan({"com.telusko.demo.controller.HomeResource"})
public class SpringSecurityJpaApplication {

	public static void main(String[] args) {
		new File(HomeResource.uploadDirectory).mkdir();
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}

}
