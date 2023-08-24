package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Repository.UsuariosAdminDAO;
import com.mx.proyecto.Services.UsuariosAdminService;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;

@Service
public class UsuariosAdminServiceImpl implements UsuariosAdminService {
	
	@Autowired
	private UsuariosAdminDAO usuariosAdminDAO;

	
	@Override
	public List<UsuariosAdmin> getUsuarios() { // desarrollo del metodo
		List<UsuariosAdmin> listUsuarios = usuariosAdminDAO.obtenerDatosGral();
		
		return listUsuarios;
	}


	@Override
	public ResponseDto insertUsuarioAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto response = new ResponseDto();
/* Los datos que vienen desde postman vienen en el objeto -> nuevoUsuario
		nuevoUsuario =	{
			"idUser" : "1",
			"nombreCompleto" : "x",
			"edad" : "18",
			"direccion" : "calle x",
			}
	
	REGLA PARA TRABAJAR CON HIBERNATE SE USA UNA ENTIDAD PARA TRASPASAR LA INFORMACION		
		INSERT INTO TABLA(CAMPOS) values(?,?,?,?,?) --> JDBC
		Crear objeto de tipo entidad y pasar la informacion
			
		es crear un objeto -> datos, como tiene el  = new UsuariosAdmin() es como inicializar algo nuevo (un obj vacio)
*/	
		UsuariosAdmin datos = new UsuariosAdmin(); 
		datos.setIdUser(nuevoUsuario.getIdUser());
		datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
		datos.setEdad(nuevoUsuario.getEdad());
		datos.setDireccion(nuevoUsuario.getDireccion());
		datos.setEstado(nuevoUsuario.getEstado());
		datos.setRol(nuevoUsuario.getRol());
			
/*		datos =	{
			"idUser" : "1",
			"nombreCompleto" : "x",
			"edad" : "18",
			"direccion" : "calle x",
		}
*/		
		Integer respuesta = usuariosAdminDAO.insertarDatosHibernate(datos);
		
		if(respuesta == 1) {
			response.setMessage("¡SE INSERTÓ CORRECTAMENTE EL REGISTRO!");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("¡NO SE INSERTÓ CORRECTAMENTE!");
			response.setCode(500);
		}
		return response;
	}


	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		
		UsuariosAdmin datoEliminar = new UsuariosAdmin();
		datoEliminar.setIdUser(idUser.getIdUser());
		
		Integer respuestaDel = usuariosAdminDAO.eliminarUser(datoEliminar);
		if(respuestaDel == 1) {
			response.setMessage("¡SE ELIMINÓ CORRECTAMENTE EL REGISTRO!");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("¡NO SE PUDO ELIMINAR CORRECTAMENTE!");
			response.setCode(500);
		}
		
		return response;
	}


	@Override
	public ResponseDto actualizarUsuario(UsuariosAdmin datos) {
		ResponseDto response = new ResponseDto();
		
		Integer respuestaUpdate = usuariosAdminDAO.actualizaInfo(datos);
		
		if(respuestaUpdate == 1) {
			response.setMessage("¡EL REGISTRO SE ACTUALIZÓ CORRECTAMENTE!");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("¡NO SE PUEDO ACTUALIZAR EL REGISTRO!");
			response.setCode(500);
		}
		
		return response;
	}
}
