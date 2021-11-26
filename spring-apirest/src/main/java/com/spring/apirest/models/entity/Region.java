package com.spring.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "regiones",schema = "db_spring_api")
public class Region  implements Serializable{
	
	@Id
	@Column(name = "id_region")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private String nombre;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
