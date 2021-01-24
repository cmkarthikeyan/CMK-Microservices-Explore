package com.cmk.microservices.employeedashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmk.microservices.employeedashboard.dto.EmployeeDTO;
import com.cmk.microservices.employeedashboard.dto.EmployeeDashboardDTO;

import lombok.extern.slf4j.Slf4j;

@Component("restTemplateClient")
@Slf4j
public class EmployeeDashboardServiceRestImpl implements IEmployeeDashboardService {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<EmployeeDashboardDTO> getAllEmployees() {
		
		
		List<EmployeeDashboardDTO> employeeDashboardList = new ArrayList<>();
		EmployeeDashboardDTO employeeDashboard = EmployeeDashboardDTO.builder().designation("Manager").build();
		
		 List<EmployeeDTO> employees = restTemplate.getForEntity("http://employee-service/api/v1/employees/", List.class).getBody();
		 employeeDashboard.setEmployees(employees);
		 log.info("using Rest template");
		 employeeDashboardList.add(employeeDashboard);
		
		return employeeDashboardList;
	}

}
