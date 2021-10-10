package com.br.compasso.uol.service;

import java.util.List;

import com.br.compasso.uol.model.City;

public interface CityService {
	
void saveCity(City city);
	
	void deleteCityById(Long idCity);
	
	void updateCity(City city);
	
	List<City>findAllCity();
	
	City findById(Long idCity);
	
	City findByName(String name);

}
