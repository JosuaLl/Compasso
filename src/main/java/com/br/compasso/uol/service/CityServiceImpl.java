package com.br.compasso.uol.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.compasso.uol.controller.dao.CityDao;
import com.br.compasso.uol.model.City;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDao _cityDao;

	@Override
	public void saveCity(City city) {
		// TODO Auto-generated method stub
		_cityDao.saveCity(city);
		
	}

	@Override
	public void deleteCityById(Long idCity) {
		// TODO Auto-generated method stub
		_cityDao.deleteCityByid(idCity);
		
	}

	@Override
	public void updateCity(City city) {
		// TODO Auto-generated method stub
		_cityDao.updateCity(city);
		
	}

	@Override
	public List<City> findAllCity() {
		// TODO Auto-generated method stub
		return _cityDao.findAllCity();
	}

	@Override
	public City findById(Long idCity) {
		// TODO Auto-generated method stub
		return _cityDao.findbyId(idCity);
	}

	@Override
	public City findByName(String name) {
		// TODO Auto-generated method stub
		return _cityDao.findByName(name);
	}

}
