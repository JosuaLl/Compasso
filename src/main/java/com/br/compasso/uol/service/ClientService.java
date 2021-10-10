package com.br.compasso.uol.service;

import java.util.List;

import com.br.compasso.uol.model.Client;

public interface ClientService {
	
void saveClient(Client client);
	
	void deleteClientById(Long idClient);
	
	void updateClient(Client client);
	
	List<Client> findAllclients();
	
	Client findById(Long idClient);
	
	Client findByName(String name);
	
	List<Client> findByIdCity(Long idCity);

}
