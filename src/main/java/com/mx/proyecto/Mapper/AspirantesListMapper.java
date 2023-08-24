package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mx.proyecto.Dto.Aspirantes;

public class AspirantesListMapper<T> implements RowMapper<Aspirantes>{
	
	public Aspirantes mapRow(ResultSet rs, int rowNum) throws SQLException{
		Aspirantes objeto = new Aspirantes();
		
		objeto.setIdAlumno(rs.getInt("ID_ALUMNO"));
		objeto.setNombreAlumno(rs.getString("NOMBRE"));
		objeto.setEdad(rs.getInt("EDAD"));
		objeto.setFechaInscripcion(rs.getDate("FECHAINSCRIPCION"));
		objeto.setMaestroId(rs.getInt("MAESTROID"));
		objeto.setCursoId(rs.getInt("CURSOID"));
		objeto.setMaestro(rs.getString("NOMBRE_MAESTRO"));
		objeto.setCurso(rs.getString("ASIGNATURA"));
		
		return objeto;
	}
}
