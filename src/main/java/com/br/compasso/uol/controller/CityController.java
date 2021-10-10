package com.br.compasso.uol.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compasso.uol.model.City;
import com.br.compasso.uol.service.CityService;
import com.br.compasso.uol.util.CustomErrorType;
import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("v1")
public class CityController {
	
	@Autowired CityService _cityService;
	
	//GET todas cidades
		@RequestMapping(value = "/cidades", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<List<City>> getCities(@RequestParam(value = "name", required = false) String name){
			List<City> cities= new ArrayList<>();
			
			if(name == null) {
				cities = _cityService.findAllCity();
				if(cities.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
		}else {
			City city = _cityService.findByName(name);
			if(city == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			cities.add(city);
			return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
		}
	}
		
		//GET por ID
		@RequestMapping(value = "/cidades{id}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<City> getCityById(@PathVariable("id") Long idCity){
			City city = _cityService.findById(idCity);
			if(idCity == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<City>(city, HttpStatus.OK);
		}
		
		//POST
		@RequestMapping(value = "/cidades", method = RequestMethod.POST, headers = "Accept=application(json")
		public ResponseEntity<?> createCities(@RequestBody City city, UriComponentsBuilder uriComponentsBuilder){
			if(city.getName().equals(null) || city.getName().isEmpty()) {
				return new ResponseEntity<>(new CustomErrorType("City  name is required"), HttpStatus.CONFLICT);
			}
			if(_cityService.findByName(city.getName()) != null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			_cityService.saveCity(city);
			City city2= _cityService.findByName(city.getName());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					uriComponentsBuilder.path("/v1/cidades/{id}")
					.buildAndExpand(city2.getIdCity())
					.toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			
		}
		
		//UPDATE
		@RequestMapping(value = "/cidades/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
		public ResponseEntity<City> updateCity(@PathVariable("id") Long idCity, @RequestBody City city){
			if(idCity == null || idCity <= 0) {
				return new ResponseEntity(new CustomErrorType("idCity is requiered"), HttpStatus.CONFLICT);
			}
			City currentCity = _cityService.findById(idCity);
			if(currentCity == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			currentCity.setName(city.getName());
			currentCity.setClients(city.getClients());
			_cityService.updateCity(currentCity);
			return new ResponseEntity<City>(currentCity, HttpStatus.OK);
		}
		
		//DELETE
		@RequestMapping(value = "/cidades/{id}", method = RequestMethod.DELETE,  headers = "Accept=application/json")
		public ResponseEntity<?> deleteCity(@PathVariable("id") Long idCity){
			City city= _cityService.findById(idCity);
			if(idCity == null) {
				return new ResponseEntity(new CustomErrorType("idCity is requiered" + idCity + " Not Found"),
						HttpStatus.NOT_FOUND);
			}
			
		
			if(idCity == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			_cityService.deleteCityById(idCity);
			return new ResponseEntity<City>(HttpStatus.OK);
		}

}
