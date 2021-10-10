package com.br.compasso.uol.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Client {
	
	@Id
	@Column(name = "id_client")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "date_birth")
	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "city_lives")
	private String cityLives;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_city")
	private City city;
	
	public Client(String name, String sex, Date dateBirth, Integer age, String cityLives) {
		super();
		this.name = name;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.age = age;
		this.cityLives = cityLives;
	}
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCityLives() {
		return cityLives;
	}

	public void setCityLives(String cityLives) {
		this.cityLives = cityLives;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


}
