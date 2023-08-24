package com.mx.proyecto.RepositoryImplement;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Mapper.MaestrosListMapper;
import com.mx.proyecto.Repository.IMaestrosRepository;

@Repository
public class MaestrosRepositoryImpl implements IMaestrosRepository {

	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Maestros> getMaestros() {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.query("SELECT * FROM MAESTROS WHERE HABILITADO = 'Y'", new MaestrosListMapper<Maestros>());
	}
	
	@Override
	public Integer insertMaestros(Maestros nuevoMaestro) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("INSERT INTO MAESTROS (NOMBRE_MAESTRO, DIRECCION, TELEFONO, EMAIL) VALUES (?,?,?,?)",
									nuevoMaestro.getNombreMaestro(), nuevoMaestro.getDireccion(), nuevoMaestro.getTelefono(), nuevoMaestro.getEmail());
	}

	@Override
	public Integer updateMaestros(Maestros maestro) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("UPDATE MAESTROS SET NOMBRE_MAESTRO = ?, DIRECCION = ?, TELEFONO = ?, EMAIL = ? WHERE ID_MAESTRO = ?",
									new Object[] {maestro.getNombreMaestro(), maestro.getDireccion(), maestro.getTelefono(), maestro.getEmail(),
											maestro.getIdMaestro()});
	}

	@Override
	public Integer deleteMaestros(Maestros maestro) {
		
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("UPDATE MAESTROS SET HABILITADO = 'N' WHERE ID_MAESTRO = ?", maestro.getIdMaestro());
	}
	
	@Override
	public int[][] insertMaestrosMasivo(List<Maestros> nuevosMaestros) {
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		
		try {
			updateCounts = jdbcTemplate.batchUpdate("INSERT INTO MAESTROS (NOMBRE_MAESTRO, DIRECCION, TELEFONO, EMAIL) VALUES (?,?,?,?)",
												  nuevosMaestros,
												  100,
												  new ParameterizedPreparedStatementSetter<Maestros>() {
													  @Override
													  public void setValues(PreparedStatement ps, Maestros argument) throws SQLException{
														  ps.setString(1, argument.getNombreMaestro());
														  ps.setString(2, argument.getDireccion());
														  ps.setString(3, argument.getTelefono());
														  ps.setString(4, argument.getEmail());
													  }
													  
												  });
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPCIÓN POR DATO DUPLICADO: " + DKE.getMessage());
		}
		
		return updateCounts;
	}

	@Override
	public Maestros getMaestroById(Maestros maestro) {
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
