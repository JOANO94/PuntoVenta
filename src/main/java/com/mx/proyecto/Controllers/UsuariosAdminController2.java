package com.mx.proyecto.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Services.UsuariosAdminService2;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;


@Controller
@RequestMapping("usuarioAdmin2")
public class UsuariosAdminController2 {
	
	@Autowired
	public UsuariosAdminService2 usuariosAdminService2;
	
	// CRUD = registrar, actualizar consulta y eliminar
	// JSON => un conjunto de atributos - valor -> nombre: isaul
	// lista de usuarios -> select * from tabla;
	
		@ResponseBody
		@RequestMapping(value="/obtenerTodos", method= RequestMethod.GET, produces = "application/json")
		public ResponseDto getUsuariosAdmin2() {
			
			return usuariosAdminService2.getUsuarios2();
		}
		
		
		@ResponseBody
		@RequestMapping(value="/insertUsuario", method= RequestMethod.POST, produces = "application/json")
		ResponseEntity <ResponseDto> insertUsuario(@RequestBody UsuariosAdminDTO nuevoUsuarioAdmin){
			final HttpHeaders httpHeaders = new HttpHeaders();
					
			ResponseDto response = usuariosAdminService2.insertUsuario(nuevoUsuarioAdmin);
					
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
					
			return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
		}
		
		// Eliminar registro
		@ResponseBody
		@RequestMapping(value="/eliminarUsuario", method = RequestMethod.POST, produces = "application/json")
		public ResponseEntity <ResponseDto> eliminarUsuario(@RequestBody UsuariosAdminDTO idUser){
			final HttpHeaders httpHeaders = new HttpHeaders();

			ResponseDto respuesta = usuariosAdminService2.eliminarUsuario(idUser);
						
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
						
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
		}
		

		// Actualizar registro
		@ResponseBody
	    @RequestMapping(value="/actualizarDatos", method = RequestMethod.POST, produces = "application/json")
		ResponseEntity < ResponseDto > actualizarDatosUsuario(@RequestBody UsuariosAdminDTO datos){
			final HttpHeaders httpHeaders = new HttpHeaders();

			ResponseDto respuesta = usuariosAdminService2.actualizarUsuario(datos);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
	    }
		
		@ResponseBody
		@RequestMapping(value="/obtenerUsuario", method= RequestMethod.GET, produces = "application/json")
		public ResponseDto getUsuarioAdmin2(@RequestBody UsuariosAdminDTO usuario) {
			
			return usuariosAdminService2.getUsuarioById(usuario);
		}
	

} // Fin de la clase
