package com.cmk.microservices.employeedashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmk.microservices.employeedashboard.dto.EmployeeDTO;
import com.cmk.microservices.employeedashboard.dto.EmployeeDashboardDTO;
import com.cmk.microservices.employeedashboard.service.feign.IEmployeeDashboardFeignclientService;

import lombok.extern.slf4j.Slf4j;

@Component("feignClientService")
@Slf4j
public class EmployeeDashboardServiceFeignImpl implements IEmployeeDashboardService{
	
	@Autowired
	IEmployeeDashboardFeignclientService feignclientService;

	@Override
	public List<EmployeeDashboardDTO> getAllEmployees() {
		List<EmployeeDashboardDTO> employeeDashboardList = new ArrayList<>();
		EmployeeDashboardDTO employeeDashboard = EmployeeDashboardDTO.builder().designation("Manager").build();
		
		 List<EmployeeDTO> employees = feignclientService.getAllEmployees();
		 employeeDashboard.setEmployees(employees);
		 log.info("Using Feign Client");
		 
		 employeeDashboardList.add(employeeDashboard);
		
		return employeeDashboardList;
	}

}
