package com.br.compasso.uol.controller.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.br.compasso.uol.model.City;

@Repository
@Transactional
public class CityDaoImpl extends AbstractSession implements CityDao{
	
	@Override
	public void saveCity(City city) {
		// TODO Auto-generated method stub
		getSession().persist(city);
		
	}

	@Override
	public void deleteCityByid(Long idCity) {
		// TODO Auto-generated method stub
		City city = findbyId(idCity);
		
		if(city != null) {
			getSession().delete(city);
		}
	}

	@Override
	public void updateCity(City city) {
		// TODO Auto-generated method stub
		getSession().update(city);
		
	}

	@Override
	public List<City> findAllCity() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from City").list();
	}

	@Override
	public City findbyId(Long idCity) {
		// TODO Auto-generated method stub
		return (City) getSession().get(City.class, idCity);
	}

	@Override
	public City findByName(String name) {
		// TODO Auto-generated method stub
		return (City) getSession().createQuery(
				"from City where name = :name")
				.setParameter("name", name).uniqueResult();
	}

}
