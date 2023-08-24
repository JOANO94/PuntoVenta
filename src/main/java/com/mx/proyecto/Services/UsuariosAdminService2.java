package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;

public interface UsuariosAdminService2 {

	ResponseDto getUsuarios2();

	ResponseDto insertUsuario(UsuariosAdminDTO nuevoUsuarioAdmin);

	ResponseDto eliminarUsuario(UsuariosAdminDTO idUser);

	ResponseDto actualizarUsuario(UsuariosAdminDTO datos);

	ResponseDto getUsuarioById(UsuariosAdminDTO usuario);

}
