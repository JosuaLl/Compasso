package com.br.compasso.uol.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ch.qos.logback.core.net.server.Client;

@Entity
@Table(name = "city")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_city")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCity;
	
	@Column(name = "name_state")
	private String nameState;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_city")
	private Set<Client> clients;
	

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String nameState, String name) {
		super();
		this.nameState = nameState;
		this.name = name;
	}

	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public String getNameState() {
		return nameState;
	}

	public void setNameState(String nameState) {
		this.nameState = nameState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	

}
