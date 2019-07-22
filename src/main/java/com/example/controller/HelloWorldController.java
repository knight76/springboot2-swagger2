package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Easy API")
public class HelloWorldController {

	@ApiOperation(value = "hello world", httpMethod = "GET", notes = "Puts your name.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid input data."),
			@ApiResponse(code = 200, message = "Validation passed")
	})
	@RequestMapping(path = "/helloworld")
	public String hello(@ApiParam(value = "name", required = true, defaultValue = "Samuel") @Valid @RequestParam String name) {
		return String.format("Welcome, Hello World, " + name);
	}

}
