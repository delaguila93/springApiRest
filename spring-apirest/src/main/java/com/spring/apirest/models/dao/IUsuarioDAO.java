package com.spring.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.apirest.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername (String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.username:=username")
	public Usuario findByUsername2 (@Param("username")String username);
}
