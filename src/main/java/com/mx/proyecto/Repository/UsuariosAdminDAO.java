package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Entity.UsuariosAdmin;

public interface UsuariosAdminDAO {

	List<UsuariosAdmin> obtenerDatosGral();

	Integer insertarDatosHibernate(UsuariosAdmin datos);

	Integer eliminarUser(UsuariosAdmin datoEliminar);

	Integer actualizaInfo(UsuariosAdmin datos);

}
