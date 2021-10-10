package com.br.compasso.uol.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compasso.uol.model.Client;
import com.br.compasso.uol.service.ClientService;
import com.br.compasso.uol.util.CustomErrorType;
import org.springframework.http.HttpHeaders;


@Controller
@RequestMapping("/v1")
public class ClientController {

	@Autowired
	ClientService _clientService;
	
	//GET por nome
		@RequestMapping(value = "/clientes", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<List<Client>> getClients(@RequestParam(value = "name", required = false)String name, 
				@RequestParam(value = "id_client", required = false) Long id_city){
			List<Client> clients = new ArrayList<>();
			
			if(id_city != null) {
				clients = _clientService.findByIdCity(id_city);
				if(clients.isEmpty()) {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
			}
			
			if(name != null) {
				Client client = _clientService.findByName(name);
			if(client == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			clients.add(client);
			}
			
			if(name == null && id_city == null) {
				clients = _clientService.findAllclients();
				if(clients.isEmpty()) {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
			}
				return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
			
		}
		
		// GET por id
		@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Client> getClientById(@PathVariable("id") Long idClient){
			Client client = _clientService.findById(idClient);
			if(idClient == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
		//POST
		@RequestMapping(value = "/clientes", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<?> createClients(@RequestBody Client client, UriComponentsBuilder uriComponentsBuilder){
			if(_clientService.findByName(client.getName()) != null) {
				return new ResponseEntity(new CustomErrorType("Unable to create. A client with name" + 
						client.getName() + " already exist."), HttpStatus.CONFLICT);
			}
			
			_clientService.saveClient(client);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					uriComponentsBuilder.path("v1/clientes/{id}")
					.buildAndExpand(client.getIdClient())
					.toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
		//UPDATE
		@RequestMapping(value = "/clientes/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
		public ResponseEntity<Client> updateClient(@PathVariable("id") Long idClient, @RequestBody Client client){
			
			Client currentClient = _clientService.findById(idClient);
			
			if(idClient == null) {
				return new ResponseEntity(new CustomErrorType("Unable to upate. Client with id " + idClient + "Not Found")
						, HttpStatus.CONFLICT);
			}
			
			currentClient.setName(client.getName());
			currentClient.setCity(client.getCity());
			_clientService.updateClient(currentClient);
			return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
		}
		
		//DELETE
		@RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE, headers = "Accept=Application/json")
		public ResponseEntity<?> deleteClient(@PathVariable("id") Long idClient){
			if(idClient == null || idClient <= 0) {
				return new ResponseEntity<>(new CustomErrorType("idClient is required"), HttpStatus.CONFLICT);
			}
			
			Client client= _clientService.findById(idClient);
			if(client == null) {
				return new ResponseEntity(new CustomErrorType("Unable to delete. client with id " + idClient + " not found."),
						HttpStatus.NOT_FOUND);
			}
			_clientService.deleteClientById(idClient);
			return new ResponseEntity<Client>(HttpStatus.OK);
		}
}
