package com.cmk.microservices.employeedashboard.service.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cmk.microservices.employeedashboard.dto.EmployeeDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="employee-service")
public interface IEmployeeDashboardFeignclientService {
	
	@CircuitBreaker(name="employee-feign-service-error", fallbackMethod = "getDefaultEmployeeDashboard")
	@GetMapping("/api/v1/employees/")
	List<EmployeeDTO> getAllEmployees();
	
	default public List<EmployeeDTO> getDefaultEmployeeDashboard(Throwable t) {
		List<EmployeeDTO> employees = new ArrayList<>();
		employees.add(EmployeeDTO.builder().age(1).name("default").id(1l).build());
		 return employees;
	}

}
