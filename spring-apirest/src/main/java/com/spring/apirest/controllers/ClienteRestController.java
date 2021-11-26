package com.spring.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.apirest.models.entity.Cliente;
import com.spring.apirest.models.entity.Region;
import com.spring.apirest.models.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/clientes")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public List<Cliente> listarTodos() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	@ApiOperation(value = "Devuelve la informacion de un clientes", notes = "Devuelve la informacion completa de un cliente",

			response = Cliente.class)
	public ResponseEntity<?> show(@ApiParam(value = "El id del cliente", required = true) @PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		Cliente clienteGuardado = null;
		Map<String, Object> response = new HashMap<>();

		try {
			clienteGuardado = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente con ID: " + clienteGuardado.getId() + " ha sido creado en la BBDD");
		response.put("cliente", clienteGuardado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteUpdate = clienteService.findById(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (clienteUpdate == null) {
			response.put("mensaje", "El cliente no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			if (cliente.getNombre() != "") {
				clienteUpdate.setNombre(cliente.getNombre());
			}
			if (cliente.getApellidos() != "") {
				clienteUpdate.setNombre(cliente.getNombre());
			}

			if (cliente.getEmail() != "") {
				clienteUpdate.setEmail(cliente.getEmail());
			}
			if (cliente.getCreatedAt() != null) {
				clienteUpdate.setCreatedAt(cliente.getCreatedAt());
			}
			if (cliente.getTelefono() != 0) {
				clienteUpdate.setTelefono(cliente.getTelefono());
			}

			if (cliente.getRegion() != null) {
				clienteUpdate.setRegion(cliente.getRegion());
			}
			
			clienteActualizado = clienteService.save(clienteUpdate);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente con ID: " + clienteActualizado.getId() + " ha sido modificado en la BBDD");
		response.put("cliente", clienteActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido borrado de la base de datos");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/clientes/uploads")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Cliente cliente = clienteService.findById(id);
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);

			} catch (IOException e) {
				response.put("mensaje", "Error al borrar el cliente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = cliente.getImagen();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();

				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}

			}

			cliente.setImagen(nombreArchivo);
			clienteService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen" + nombreFoto);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}

	@GetMapping("/clientes/regiones")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Cliente.class)
	public List<Region> listarTodasRegiones() {
		return clienteService.findAllRegions();
	}

}
