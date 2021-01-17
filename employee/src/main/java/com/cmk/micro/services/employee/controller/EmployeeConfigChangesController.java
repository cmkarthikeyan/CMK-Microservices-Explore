package com.cmk.micro.services.employee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("api/v1/employees")
public class EmployeeConfigChangesController {

	@Value("${country}")
	private String country;

	@GetMapping("/country")
	public String getCountry() {
		return country;
	}
}
