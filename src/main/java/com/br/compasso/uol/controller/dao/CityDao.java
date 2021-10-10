package com.br.compasso.uol.controller.dao;

import java.util.List;

import com.br.compasso.uol.model.City;

public interface CityDao {
	
void saveCity(City city);
	
	void deleteCityByid(Long idCity);
	
	void updateCity(City city);
	
	List<City> findAllCity();
	
	City findbyId(Long idCity);
	
	City findByName(String name);

}
