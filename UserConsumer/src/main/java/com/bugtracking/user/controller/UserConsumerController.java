package com.bugtracking.user.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bugtracking.user.dto.BugDto;
import com.bugtracking.user.enums.bugstatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class UserConsumerController {

	RestTemplate rt = new RestTemplate();

	String endpointBug = "http://localhost:8091/bugs/";

	@ApiOperation("INSERT bug (as USER-EMPLOYEE)")
	@PostMapping("/employees/bugs")
	public String createBug(@Valid @RequestBody BugDto b) {
		rt.postForLocation(endpointBug, b);
		return "created successfully";
	}

	@ApiOperation("FETCH bugs by status (as USER-EMPLOYEE)")
	@GetMapping("/employees/bugs/bystatus/{bugStatus}")
	public List<BugDto> readBugsByStatus(@PathVariable bugstatus bugStatus) {
		String endpointBugsByStatus = endpointBug + "bystatus/" + bugStatus;
		return Arrays.asList(rt.getForObject(endpointBugsByStatus, BugDto[].class));
	}

}
