package com.example.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.NotFoundException;
import com.example.model.City;
import com.example.service.CityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/1")
public class CitiesController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "View available cities", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/cities")
	public List<City> getCities() {
		List<City> cities = cityService.findAll();
		return cities;
	}

	@ApiOperation(value = "Add a city")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added list"),
			@ApiResponse(code = 400, message = "Wrong city id"),
	})
	@PostMapping("/city")
	public Map<String, Boolean> addCity(
			@ApiParam(value = "City object store in database table", required = true) @Valid @RequestBody City city) {
		Boolean result = cityService.add(city);
		Map<String, Boolean> response = new HashMap<>();
		response.put("added", result);
		return response;
	}

	@ApiOperation(value = "Get a city")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
	})
	@GetMapping("/city/{id}")
	public City getCity(
			@ApiParam(value = "City Id from which city object will delete", required = true, defaultValue = "0") @PathVariable(value
					= "id") Long cityId)
			throws NotFoundException {
		City city = cityService.get(cityId)
		                       .orElseThrow(() -> new NotFoundException("City not found for this id :: " + cityId));
		return city;
	}

	@ApiOperation(value = "Delete a city")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted list"),
			@ApiResponse(code = 400, message = "Wrong city id"),
	})
	@DeleteMapping("/city/{id}")
	public Map<String, Boolean> deleteCity(
			@ApiParam(value = "City Id from which city object will delete", required = true, defaultValue = "0") @PathVariable(value
					= "id") Long cityId)
			throws NotFoundException {
		City city = cityService.get(cityId)
		             .orElseThrow(() -> new NotFoundException("City not found for this id :: " + cityId));
		cityService.delete(city);
		Map <String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}