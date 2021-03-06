package com.spring.apirest.models.service;

import java.util.List;

import com.spring.apirest.models.entity.Cliente;
import com.spring.apirest.models.entity.Region;

public interface ClienteService {
	
	public List<Cliente> findAll();

	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Region> findAllRegions();
}
