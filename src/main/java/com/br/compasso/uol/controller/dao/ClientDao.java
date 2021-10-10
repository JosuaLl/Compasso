package com.br.compasso.uol.controller.dao;

import java.util.List;

import com.br.compasso.uol.model.Client;

public interface ClientDao {
	
void saveClient(Client client);
	
	void deleteClientById(Long idClient);
	
	void updateClient(Client client);
	
	List<Client> findAllClientes();
	
	Client findById(Long idClient);
	
	Client findByName(String name);
	
	List<Client> findByIdCity(Long idCity);

}
