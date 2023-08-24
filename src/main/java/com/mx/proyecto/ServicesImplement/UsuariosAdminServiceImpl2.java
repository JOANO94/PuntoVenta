package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.Services.UsuariosAdminService2;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;

@Service
public class UsuariosAdminServiceImpl2 implements UsuariosAdminService2 {
	
	@Autowired
	private UsuariosAdminDAO2 usuariosAdminDAO2;

	@Override
	public ResponseDto getUsuarios2() {
		ResponseDto response = new ResponseDto();
		
		try {
			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtenerUsuarios();
			if(!(listaUsuarios.isEmpty()) && listaUsuarios != null) {
				response.setCode(200);
				response.setMessage("EXISTEN " + listaUsuarios.size() + " REGISTRO(S) EN LA TABLA");
				response.setContent(listaUsuarios);
			}else {
				response.setCode(500);
				response.setMessage("¡NO EXISTEN REGISTROS EN LA TABLA!");
			}
			
		}catch(Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE X EN EL MÉTODO getUsuarios2.");
		}
		
		return response;
	}

	@Override
	public ResponseDto insertUsuario(UsuariosAdminDTO nuevoUsuarioAdmin) {
		ResponseDto response = new ResponseDto();
		
		try {
			if(nuevoUsuarioAdmin != null) {
				if(nuevoUsuarioAdmin.getNombreCompleto() != null && !nuevoUsuarioAdmin.getNombreCompleto().isEmpty() &&
					nuevoUsuarioAdmin.getEdad() != 0 &&
					nuevoUsuarioAdmin.getDireccion() != null && !nuevoUsuarioAdmin.getDireccion().isEmpty()) {
					
					UsuariosAdmin datos = new UsuariosAdmin();
					
					datos.setIdUser(usuariosAdminDAO2.obtValorSecTabla());
					datos.setNombreCompleto(nuevoUsuarioAdmin.getNombreCompleto());
					datos.setEdad(nuevoUsuarioAdmin.getEdad());
					datos.setDireccion(nuevoUsuarioAdmin.getDireccion());
					datos.setEstado(nuevoUsuarioAdmin.getEstado());
					datos.setRol(nuevoUsuarioAdmin.getRol());
					
					usuariosAdminDAO2.create(datos);
					
					response.setCode(200);
					response.setMessage("¡SE INSERTÓ EXITOSAMENTE UN NUEVO REGISTRO!");
				}else {
					response.setCode(500);
					response.setMessage("LOS DATOS OBLIGATORIOS VIENEN VACÍOS - (Nombre Completo, Edad, Dirección)");
				}
				
			}else {
				response.setCode(500);
				response.setMessage("LOS CAMPOS VIENEN VACÍOS.");
			}
			
		}catch(Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE X EN EL MÉTODO insertUsuario.");
		}
		
		return response;
	}

	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		
		try {
			if(idUser.getIdUser() != 0) {
				usuariosAdminDAO2.delete(idUser.getIdUser());
			
				response.setCode(200);
				response.setMessage("¡EL REGISTRO SE ELIMINÓ CORRECTAMENTE!");	
			}else {
				response.setCode(400);
				response.setMessage("¡EL PK VIENE CON CERO!");
			}
				
		}catch(Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE X EN EL MÉTODO eliminarUsuario.");
		}
		
		return response;
	}

	@Override
	public ResponseDto actualizarUsuario(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();
		
		try {
			UsuariosAdmin datosActulizados = new UsuariosAdmin();
			
			datosActulizados.setIdUser(datos.getIdUser());
			datosActulizados.setNombreCompleto(datos.getNombreCompleto());
			datosActulizados.setEdad(datos.getEdad());
			datosActulizados.setDireccion(datos.getDireccion());
			datosActulizados.setEstado(datos.getEstado());
			datosActulizados.setRol(datos.getRol());
			
			usuariosAdminDAO2.update(datosActulizados);
			response.setCode(200);
			response.setMessage("¡EL REGISTRO SE ACTUALIZÓ CORRECTAMENTE!");	
			
		}catch(Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE X EN EL MÉTODO actualizarUsuario.");
		}
		
		return response;
	}

	@Override
	public ResponseDto getUsuarioById(UsuariosAdminDTO usuario) {
		ResponseDto response = new ResponseDto();
		
		try {
			UsuariosAdmin usuarioDatos = usuariosAdminDAO2.read(usuario.getIdUser());
			
			if(usuarioDatos != null) {
				response.setCode(200);
				response.setMessage("USUARIO ENCONTRADO:");
				response.setContent(usuarioDatos);
			}else {
				response.setCode(400);
				response.setMessage("¡EL USUARIO NO EXISTE!");
			}
		}catch(Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE X EN EL MÉTODO getUsuarioById.");
		}
		return response;
	}
}
