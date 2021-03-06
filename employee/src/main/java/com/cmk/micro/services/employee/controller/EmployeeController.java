package com.cmk.micro.services.employee.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cmk.micro.services.employee.dto.EmployeeDTO;
import com.cmk.micro.services.employee.service.EmployeeService;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping("api/v1/employees")
@RestController
@RefreshScope
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Value("${country}")
	private String country;
    
	@Operation(description = "Returns all the employees")
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> employees = employeeService.getEmployees();
		
		employees.forEach(employeeDTO -> {
			if(Objects.nonNull(employeeDTO)) {
			WebMvcLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).getEmployee(employeeDTO.getId()));
			employeeDTO.add(linkToSelf.withRel("self-link"));
			}
		});
		
		return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
	}

	@Operation(description = "Returns the employee based on the given id")
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long employeeId) {
		EmployeeDTO employee = employeeService.getEmployee(employeeId);
		
		WebMvcLinkBuilder linkToAll = linkTo(methodOn(this.getClass()).getEmployees());
		employee.add(linkToAll.withRel("all-employees"));

		WebMvcLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).getEmployee(employeeId));
		employee.add(linkToSelf.withRel("self-link"));
		
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<HttpHeaders> createEmployee(@Validated @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employees/" + employee.getId());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<HttpHeaders> updateEmployee(@Validated @RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
		
		employeeService.updateEmployee(employeeId, employeeDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employees/" + employeeId);
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<HttpHeaders> deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employees/" + employeeId);
		return new ResponseEntity(headers, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/dynamicCountry")
	public String getCountry() {
		return country;
	}
}
