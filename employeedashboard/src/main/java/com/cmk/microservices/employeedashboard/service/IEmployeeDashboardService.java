package com.cmk.microservices.employeedashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cmk.microservices.employeedashboard.dto.EmployeeDashboardDTO;

@Service
public interface IEmployeeDashboardService {
	
	List<EmployeeDashboardDTO> getAllEmployees();

}
