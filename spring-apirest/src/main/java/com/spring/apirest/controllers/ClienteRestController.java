package com.spring.apirest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.apirest.models.entity.Cliente;
import com.spring.apirest.models.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> listarTodos() {
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id, HttpServletResponse response) {
		if(clienteService.findById(id) == null) {
			response.setStatus( HttpServletResponse.SC_NOT_FOUND);
		}else {
			response.setStatus(HttpServletResponse.SC_FOUND);
		}
		return clienteService.findById(id);
	}

	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		 Cliente clienteUpdate = clienteService.findById(id);
		 
		 clienteUpdate.setNombre(cliente.getNombre());
		 clienteUpdate.setApellidos(cliente.getApellidos());
		 clienteUpdate.setEmail(cliente.getEmail());
		 
		 return clienteService.save(clienteUpdate);
	}
	
	@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		 clienteService.delete(id);
	}
}
