package com.cmk.microservices.empployeeservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class Empployeeservicev2Application {

	public static void main(String[] args) {
		SpringApplication.run(Empployeeservicev2Application.class, args);
	}
	
	@GetMapping("api/v2/employees/")
	public String getEmployees(){
		return "Using V2 employee service";
	}

}
