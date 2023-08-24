package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Cursos;

public class CursosListMapper<T> implements RowMapper<Cursos>{

	public Cursos mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cursos curso = new Cursos();
		
		curso.setIdCursos(rs.getInt("ID_CURSO"));
		curso.setNombreCurso(rs.getString("ASIGNATURA"));
		curso.setClave(rs.getString("CLAVE"));
		curso.setSATCA(rs.getString("SATCA"));
		
		return curso;
	}

}
