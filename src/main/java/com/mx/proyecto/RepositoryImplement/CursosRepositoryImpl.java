package com.mx.proyecto.RepositoryImplement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Mapper.CursosListMapper;
import com.mx.proyecto.Repository.ICursosRepository;

@Repository
public class CursosRepositoryImpl implements ICursosRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Cursos> getCursos() {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.query("SELECT * FROM CURSOS WHERE HABILITADO = 'Y'", new CursosListMapper<Cursos>());
	}

	@Override
	public Integer insertCursos(Cursos nuevoCurso) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("INSERT INTO CURSOS (ASIGNATURA, CLAVE, SATCA) VALUES (?,?,?)",
									nuevoCurso.getNombreCurso(), nuevoCurso.getClave(), nuevoCurso.getSATCA());
	}

	@Override
	public Integer updateCursos(Cursos curso) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("UPDATE CURSOS SET ASIGNATURA = ?, CLAVE = ?, SATCA = ? WHERE ID_CURSO = ?",
									new Object[] {curso.getNombreCurso(), curso.getClave(), curso.getSATCA(), curso.getIdCursos()});
	}

	@Override
	public Integer deleteCursos(Cursos curso) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("UPDATE CURSOS SET HABILITADO = 'N' WHERE ID_CURSO = ?", curso.getIdCursos());
	}
	
	@Override
	public int[][] insertCursosMasivo(List<Cursos> nuevosCursos) {
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		
		try {
			updateCounts = jdbcTemplate.batchUpdate("INSERT INTO CURSOS (ASIGNATURA, CLAVE, SATCA) VALUES (?,?,?)",
												  nuevosCursos,
												  100,
												  new ParameterizedPreparedStatementSetter<Cursos>() {
													  @Override
													  public void setValues(PreparedStatement ps, Cursos argument) throws SQLException{
														  ps.setString(1, argument.getNombreCurso());
														  ps.setString(2, argument.getClave());
														  ps.setString(3, argument.getSATCA());
													  }
													  
												  });
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPCIÓN POR DATO DUPLICADO: " + DKE.getMessage());
		}
		
		return updateCounts;
	}
	
	@Override
	public Cursos getCursoById(Cursos curso) {
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
