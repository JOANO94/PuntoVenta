package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Entity.Empleados;

public interface EmpleadosDAO extends DAO<Empleados, Long> {

	List<Empleados> getEmpleadosMasculinos();

	List<Empleados> getEmpleadosF35();

	Empleados getEmpleadosByRFC(String rfc);

	Empleados getEmpleadosByRFCActivo(String rfc);

	Long obtValSecEmpleado();
}
