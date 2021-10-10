package com.br.compasso.uol.controller.dao;

import java.util.List;

import com.br.compasso.uol.model.Client;

public class ClientDaoImpl extends AbstractSession implements ClientDao{
	
	@Override
	public void saveClient(Client client) {
		getSession().persist(client);
		
	}

	@Override
	public void deleteClientById(Long idClient) {
		Client client = findById(idClient);
		if(client != null) {
			getSession().delete(client);
		}
	}

	@Override
	public void updateClient(Client client) {
		getSession().update(client);
		
	}

	@Override
	public List<Client> findAllClientes() {
		return getSession().createQuery("from Client").list();
	}

	@Override
	public Client findById(Long idClient) {
		// TODO Auto-generated method stub
		return (Client) getSession().get(Client.class, idClient);
	}
	
	@Override
	public Client findByName(String name) {
		// TODO Auto-generated method stub
		return (Client)getSession().createQuery(
				"from Client where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public List<Client> findByIdCity(Long idCity) {
		// TODO Auto-generated method stub
		return (List<Client>) getSession().createQuery(
				"from Client c join c.city t where t.idCity = :idCity")
				.setParameter("idCity", idCity).list();
	}

}
