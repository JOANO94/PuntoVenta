package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.proyecto.Entity.Empleados;
import com.mx.proyecto.Repository.EmpleadosDAO;

@SuppressWarnings("serial")
@Repository
public class EmpleadosDAOImpl extends GenericDAO<Empleados, Long> implements EmpleadosDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public EmpleadosDAOImpl() {
	}

	public EmpleadosDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional()
	public List<Empleados> getEmpleadosMasculinos() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Empleados.class);

		criteria.add(Restrictions.eq("sexo", "M"));

		return (List<Empleados>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional()
	public List<Empleados> getEmpleadosF35() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Empleados.class);

		criteria.add(Restrictions.eq("sexo", "F"));
		criteria.add(Restrictions.eq("edad", new Long(35)));

		return (List<Empleados>) criteria.list();
	}

	@Override
	@Transactional()
	public Empleados getEmpleadosByRFC(String rfc) {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Empleados.class);

		criteria.add(Restrictions.eq("rfc", rfc));

		return (Empleados) criteria.uniqueResult();
	}

	@Override
	@Transactional()
	public Empleados getEmpleadosByRFCActivo(String rfc) {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Empleados.class);

		criteria.add(Restrictions.eq("rfc", rfc));
		criteria.add(Restrictions.eq("activo", new Long(1)));

		return (Empleados) criteria.uniqueResult();
	}

	@Override
	@Transactional()
	public Long obtValSecEmpleado() {
		String sqlObtSecuencia = "SELECT SEC_ID_EMPLEADOS.NEXTVAL FROM DUAL";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlObtSecuencia);
		List<?> result = query.list();
		return ((BigDecimal) result.get(0)).longValue();
	}

}
