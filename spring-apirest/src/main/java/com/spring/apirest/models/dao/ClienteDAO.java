package com.spring.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.apirest.models.entity.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Long> {
	

}
