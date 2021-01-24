package com.cmk.microservices.employeedashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmk.microservices.employeedashboard.dto.EmployeeDashboardDTO;
import com.cmk.microservices.employeedashboard.service.IEmployeeDashboardService;

@RestController
@RequestMapping("api/v1/employeedashboard")
public class EmployeeDashboardController {

	@Autowired
	@Qualifier("restTemplateClient")
	IEmployeeDashboardService employeeDashboardRestService;

	@Autowired
	@Qualifier("feignClientService")
	IEmployeeDashboardService employeeDashboardFeignService;

	@GetMapping("/")
	public ResponseEntity<List<EmployeeDashboardDTO>> getEmployees(@RequestParam("type") String type) {
		List<EmployeeDashboardDTO> employeeDashboardList = new ArrayList<>();
		if ("feign".equalsIgnoreCase(type)) {
			employeeDashboardList = employeeDashboardFeignService.getAllEmployees();
		} else {
			employeeDashboardList = employeeDashboardRestService.getAllEmployees();
		}
		return new ResponseEntity<List<EmployeeDashboardDTO>>(employeeDashboardList, HttpStatus.OK);
	}

}
