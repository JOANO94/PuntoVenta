package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Maestros;

public class MaestrosListMapper<T> implements RowMapper<Maestros> {

	@Override
	public Maestros mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Maestros maestro = new Maestros();
		
		maestro.setIdMaestro(rs.getInt("ID_MAESTRO"));
		maestro.setNombreMaestro(rs.getString("NOMBRE_MAESTRO"));
		maestro.setDireccion(rs.getString("DIRECCION"));
		maestro.setTelefono(rs.getString("TELEFONO"));
		maestro.setEmail(rs.getString("EMAIL"));
		
		return maestro;
	}

}