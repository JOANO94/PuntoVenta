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

	@Override
	public boolean validarCURP(String curp) {
		String expRegCurp = "^[A-Z]{4}\\d{6}[HM]{1}(AS|BC|BS|CC|CH|CL|CM|CS|DF|DG|GR|GT|HG|JC|MC|MN|MS|NE|NL|NT|OC|PL|"
				+ "QR|QT|SL|SP|SR|TC|TL|TS|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]{1}\\d{1}$";

		if (curp.matches(expRegCurp)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validarRFC(String rfc) {
		String expRegRfc = "^(([A-ZÑ&]{4})([0-9]{2})([0][13578]|[1][02])(([0][1-9]|[12][\\d])|[3][01])([A-Z0-9]{3}))|"
				+ "(([A-ZÑ&]{4})([0-9]{2})([0][13456789]|[1][012])(([0][1-9]|[12][\\d])|[3][0])([A-Z0-9]{3}))|"
				+ "(([A-ZÑ&]{4})([02468][048]|[13579][26])[0][2]([0][1-9]|[12][\\d])([A-Z0-9]{3}))|"
				+ "(([A-ZÑ&]{4})([0-9]{2})[0][2]([0][1-9]|[1][0-9]|[2][0-8])([A-Z0-9]{3}))$";

		if (rfc.matches(expRegRfc)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validarNSS(String nss) {
		String expRegNum = "^[0-9]{10}$";

		if (nss.matches(expRegNum)) {
			return true;
		} else {
			return false;
		}
	}

}
