package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;

@Repository
public class UsuariosAdminDAOImpl2 extends GenericDAO<UsuariosAdmin, Long> implements UsuariosAdminDAO2 {

	@Override
	@Transactional()
	public List<UsuariosAdmin> obtenerUsuarios() {
		
		final Session session = sessionFactory.getCurrentSession(); 
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); 
		
		return (List<UsuariosAdmin>) criteria.list();
	}

	@Override
	@Transactional()
	public Long obtValorSecTabla() {
		String sqlObtSecuencia = "SELECT SEC_USUARIOS_ADMIN.NEXTVAL AS SECUENCIAUSER FROM DUAL";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlObtSecuencia);
		List result =  query.list();
		return ((BigDecimal) result.get(0)).longValue();
	}

}
