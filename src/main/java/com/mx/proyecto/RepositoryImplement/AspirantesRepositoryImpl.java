package com.mx.proyecto.RepositoryImplement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Mapper.AspirantesListMapper;
import com.mx.proyecto.Repository.IAspirantesRepository;

@Repository
public class AspirantesRepositoryImpl implements IAspirantesRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Aspirantes> getAspirantes() {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.query("SELECT A.*, C.ASIGNATURA, M.NOMBRE_MAESTRO FROM ASPIRANTES A "
				+ "INNER JOIN CURSOS C ON A.CURSOID = C.ID_CURSO "
				+ "INNER JOIN MAESTROS M ON A.MAESTROID = M.ID_MAESTRO", new AspirantesListMapper<Aspirantes>());
	}
	
	@Override
	public Integer cantidadCurso(Integer id) {
		jdbcTemplate.setDataSource(getDataSource());
	    String sql = "SELECT COUNT(*) FROM CURSOS WHERE ID_CURSO = ?";
	    return this.jdbcTemplate.queryForObject(sql, Integer.class, id);
	}
	@Override
	public Integer cantidadMaestro(Integer id) {
		jdbcTemplate.setDataSource(getDataSource());
	    String sql = "SELECT COUNT(*) FROM MAESTROS WHERE ID_MAESTRO = ?";
	    return this.jdbcTemplate.queryForObject(sql, Integer.class, id);
	}
	@Override
	public List<Integer> getCursos() {
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.queryForList("SELECT ID_CURSO FROM CURSOS", Integer.class);
	}
	@Override
	public List<Integer> getMaestros() {
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.queryForList("SELECT ID_MAESTRO FROM MAESTROS", Integer.class);
	}
	
	@Override
	public Integer insertAspirantes(Aspirantes nuevoAspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("INSERT INTO ASPIRANTES (NOMBRE, EDAD, FECHAINSCRIPCION, CURSOID, MAESTROID)"
								  + "VALUES (?,?,?,?,?)", nuevoAspirante.getNombreAlumno(), nuevoAspirante.getEdad(), nuevoAspirante.getFechaInscripcion(),
								   nuevoAspirante.getCursoId(), nuevoAspirante.getMaestroId());
	}
	
	/*@Override
	public Aspirantes existeCurso(Aspirantes aspirante){
		jdbcTemplate.setDataSource(getDataSource());
		
		Aspirantes existeCurso = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM CURSOS WHERE ID_CURSO = ?", 
				Aspirantes.class, aspirante.getCursoId());
		
		return existeCurso != null && existeCurso > 0;
	}*/

	@Override
	public Integer updateAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE ASPIRANTES SET NOMBRE = ?, EDAD = ?, FECHAINSCRIPCION = ?, CURSOID = ?, MAESTROID = ?"
								  + "WHERE ID_ALUMNO = ?"
								  , new Object[] {aspirante.getNombreAlumno(), aspirante.getEdad(), aspirante.getFechaInscripcion(),
										  aspirante.getCursoId(), aspirante.getMaestroId(), aspirante.getIdAlumno()});
	}

	@Override
	public Integer deleteAspirantes(Aspirantes aspirante) {	
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("DELETE FROM ASPIRANTES WHERE ID_ALUMNO = ?", aspirante.getIdAlumno());
	}
	
	@Override
	public int[][] insertAspirantesMasivo(List<Aspirantes> nuevosAspirantes){
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		
		try {
			updateCounts = jdbcTemplate.batchUpdate("INSERT INTO ASPIRANTES (NOMBRE, EDAD, FECHAINSCRIPCION, CURSOID, MAESTROID) VALUES (?,?,SYSDATE,?,?)",
												  nuevosAspirantes,
												  100,
												  new ParameterizedPreparedStatementSetter<Aspirantes>() {
													  @Override
													  public void setValues(PreparedStatement ps, Aspirantes argument) throws SQLException{
														  ps.setString(1, argument.getNombreAlumno());
														  ps.setInt(2, argument.getEdad());
														  ps.setInt(3, argument.getCursoId());
														  ps.setInt(4, argument.getMaestroId());
													  }
													  
												  });
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPCIÓN POR DATO DUPLICADO: " + DKE.getMessage());
		}
		
		return updateCounts;
	}
	

	@Override
	public Aspirantes getAspiranteById(Aspirantes aspirante) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
