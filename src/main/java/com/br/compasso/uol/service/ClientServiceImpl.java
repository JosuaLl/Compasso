package com.br.compasso.uol.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.compasso.uol.controller.dao.ClientDao;
import com.br.compasso.uol.model.Client;

@Service("clienteService")
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientDao _clienteDao;

	@Override
	public void saveClient(Client client) {
		// TODO Auto-generated method stub
		_clienteDao.saveClient(client);
		
	}

	@Override
	public void deleteClientById(Long idClient) {
		// TODO Auto-generated method stub
		_clienteDao.deleteClientById(idClient);
		
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		_clienteDao.updateClient(client);
		
	}

	@Override
	public List<Client> findAllclients() {
		// TODO Auto-generated method stub
		return _clienteDao.findAllClientes();
	}

	@Override
	public Client findById(Long idClient) {
		// TODO Auto-generated method stub
		return _clienteDao.findById(idClient);
	}

	@Override
	public Client findByName(String name) {
		// TODO Auto-generated method stub
		return _clienteDao.findByName(name);
	}

	@Override
	public List<Client> findByIdCity(Long idCity) {
		// TODO Auto-generated method stub
		return _clienteDao.findByIdCity(idCity);
	}

}
