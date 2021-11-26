package com.spring.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.apirest.models.entity.Cliente;
import com.spring.apirest.models.entity.Region;

public interface ClienteDAO extends CrudRepository<Cliente, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegions();

}
