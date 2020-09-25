package com.store.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.domain.Client;
import com.store.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

	@Autowired
	private ClientService service;

	// GET METHODS -----------------------
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer id) {
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// POST METHODS -----------------------

	// PUT METHODS -----------------------

	// DELETE METHODS -----------------------

}
