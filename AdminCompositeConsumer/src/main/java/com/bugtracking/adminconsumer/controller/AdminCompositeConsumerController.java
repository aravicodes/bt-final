package com.bugtracking.adminconsumer.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bugtracking.adminconsumer.dto.ProjectDto;
import com.bugtracking.adminconsumer.dto.BugDto;
import com.bugtracking.adminconsumer.dto.EmployeeDto;

import com.bugtracking.adminconsumer.enums.bugstatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class AdminCompositeConsumerController {
	RestTemplate rt = new RestTemplate();

	String endpointBug = "http://localhost:8091/bugs/";
	String endpointEmployee = "http://localhost:8092/employees/";
	String endpointProject = "http://localhost:8093/projects/";

	String createdMessage = "created successfully";
	String updatedMessage = "updated successfully";
	String deletedMessage = "deleted successfully";

	@ApiOperation("FETCH bug with particular ID (as ADMIN)")
	@GetMapping("/admin/bugs/{bugId}")
	public BugDto readBug(@PathVariable long bugId) {
		String endpointReadBugId = endpointBug + bugId;
		return rt.getForObject(endpointReadBugId, BugDto.class);
	}

	@GetMapping("/admin/bugs")
	@ApiOperation("FETCH all bugs (as ADMIN)")
	public List<BugDto> readAllBugs() {
		return Arrays.asList(rt.getForObject(endpointBug, BugDto[].class));
	}

	@ApiOperation("FETCH bugs by status(as ADMIN)")
	@GetMapping("/admin/bugs/bystatus/{bugStatus}")
	public List<BugDto> readBugsByStatus(@PathVariable bugstatus bugStatus) {
		String endpointReadBugStatus = endpointBug + "/bystatus/" + bugStatus;
		return Arrays.asList(rt.getForObject(endpointReadBugStatus, BugDto[].class));
	}

	@ApiOperation("UPDATE bug using particular ID (as ADMIN)")
	@PutMapping("/admin/bugs/{bugId}")
	public String updateBug(@PathVariable long bugId, @Valid @RequestBody BugDto b) {
		String endpointUpdateBugId = endpointBug + bugId;
		rt.put(endpointUpdateBugId, b);
		return updatedMessage;
	}

	@ApiOperation("DELETE bug with particular ID (as ADMIN)")
	@DeleteMapping("/admin/bugs/{bugId}")
	public String deleteBug(@PathVariable long bugId) {
		String endpointDeleteBugId = endpointBug + bugId;
		rt.delete(endpointDeleteBugId);
		return deletedMessage;
	}

	@ApiOperation("INSERT employee (as ADMIN)")
	@PostMapping("/admin/employees")
	public String createEmployee(@Valid @RequestBody EmployeeDto e) {
		rt.postForLocation(endpointEmployee, e);
		return createdMessage;
	}

	@ApiOperation("FETCH employee with particular ID (as ADMIN)")
	@GetMapping("/admin/employees/{empId}")
	public EmployeeDto readEmployee(@PathVariable long empId) {
		String endpointReadEmployeeId = endpointEmployee + empId;
		return rt.getForObject(endpointReadEmployeeId, EmployeeDto.class);
	}

	@ApiOperation("FETCH all employees (as ADMIN)")
	@GetMapping("/admin/employees")
	public List<EmployeeDto> readAllEmployees() {
		return Arrays.asList(rt.getForObject(endpointEmployee, EmployeeDto[].class));
	}

	@ApiOperation("UPDATE employee (as ADMIN)")
	@PutMapping("/admin/employees/{empId}")
	public String updateEmployee(@PathVariable("empId") long empId, @Valid @RequestBody EmployeeDto e) {
		String endpointUpdateEmployeeId = endpointEmployee + empId;
		rt.put(endpointUpdateEmployeeId, e);
		return updatedMessage;
	}

	@ApiOperation("DELETE employee with particular ID (as ADMIN)")
	@DeleteMapping("/admin/employees/{empId}")
	public String deleteEmployee(@PathVariable long empId) {
		String endpointDeleteEmployeeId = endpointEmployee + empId;
		rt.delete(endpointDeleteEmployeeId);
		return deletedMessage;
	}

	@ApiOperation("INSERT project (as ADMIN)")
	@PostMapping("/admin/projects")
	public String createProject(@Valid @RequestBody ProjectDto p) {
		rt.postForLocation(endpointProject, p);
		return createdMessage;
	}

	@ApiOperation("FETCH project with particular ID (as ADMIN)")
	@GetMapping("/admin/projects/{projId}")
	public ProjectDto readProject(@PathVariable long projId) {
		String endpointReadProjectId = endpointProject + projId;
		return rt.getForObject(endpointReadProjectId, ProjectDto.class);
	}

	@ApiOperation("FETCH all projects (as ADMIN)")
	@GetMapping("/admin/projects")
	public List<ProjectDto> readAllProjects() {
		return Arrays.asList(rt.getForObject(endpointProject, ProjectDto[].class));

	}

	@ApiOperation("UPDATE project with particular ID (as ADMIN)")
	@PutMapping("/admin/projects/{projId}")
	public String updateProject(@PathVariable("projId") long projId, @Valid @RequestBody ProjectDto p) {
		String endpointUpdateProjectId = endpointProject + projId;
		rt.put(endpointUpdateProjectId, p);
		return updatedMessage;
	}

	@ApiOperation("DELETE project with particular ID (as ADMIN)")
	@DeleteMapping("/admin/projects/{projId}")
	public String deleteProject(@PathVariable long projId) {
		String endpointDeleteProjectId = endpointProject + projId;
		rt.delete(endpointDeleteProjectId);
		return deletedMessage;
	}

}
