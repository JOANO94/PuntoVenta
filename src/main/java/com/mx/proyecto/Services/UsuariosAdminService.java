package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;

public interface UsuariosAdminService {

	List<UsuariosAdmin> getUsuarios();

	ResponseDto insertUsuarioAdmin(UsuariosAdminDTO nuevoUsuario);

	ResponseDto eliminarUsuario(UsuariosAdminDTO idUser);

	ResponseDto actualizarUsuario(UsuariosAdmin datos);

}
