package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Services.EmpleadosService;
import com.mx.proyecto.Dto.EmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Entity.Empleados;
import com.mx.proyecto.Repository.EmpleadosDAO;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

	@Autowired
	private EmpleadosDAO empleadosDAO;

	@Override
	public ResponseDto insertEmpleado(EmpleadosDTO nuevoEmpleado) {
		ResponseDto response = new ResponseDto();

		try {

				Empleados empleadoDatos = empleadosDAO.getEmpleadosByRFC(nuevoEmpleado.getRfc());

				if (empleadoDatos == null) {
					Empleados datosNuevoEmpleado = new Empleados();

					datosNuevoEmpleado.setIdEmpleado(empleadosDAO.obtValSecEmpleado());
					datosNuevoEmpleado.setNombreCompleto(nuevoEmpleado.getNombreCompleto());
					datosNuevoEmpleado.setRfc(nuevoEmpleado.getRfc());
					datosNuevoEmpleado.setCurp(nuevoEmpleado.getCurp());
					datosNuevoEmpleado.setEdad(nuevoEmpleado.getEdad());
					datosNuevoEmpleado.setSexo(nuevoEmpleado.getSexo());
					datosNuevoEmpleado.setDireccion(nuevoEmpleado.getDireccion());
					datosNuevoEmpleado.setNss(nuevoEmpleado.getNss());
					datosNuevoEmpleado.setTelefono(nuevoEmpleado.getTelefono());
					datosNuevoEmpleado.setActivo(nuevoEmpleado.getActivo());

					empleadosDAO.create(datosNuevoEmpleado);
					response.setCode(200);
					response.setMessage("¡SE INSERTÓ UN NUEVO REGISTRO DE EMPLEADO!");

				} else {
					response.setCode(500);
					response.setMessage("¡EL EMPLEADO YA EXISTE EN LA BASE DE DATOS!");
				}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO insertEmpleado");
		}

		return response;
	}

	@Override
	public ResponseDto deleteEmpleado(EmpleadosDTO empleado) {
		ResponseDto response = new ResponseDto();

		try {
			Empleados empleadoActivo = empleadosDAO.getEmpleadosByRFCActivo(empleado.getRfc());

			if (empleadoActivo == null) {
				empleadosDAO.delete(empleado.getIdEmpleado());
				response.setCode(200);
				response.setMessage("¡EL REGISTRO DEL EMPLEADO SE ELIMINÓ CORRECTAMENTE!");
			} else {
				response.setCode(500);
				response.setMessage("¡IMPOSIBLE ELIMINAR EMPLEADO, SIGUE LABORANDO (ACTIVO)...!");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO updateEmpleado.");
		}
		return response;
	}

	@Override
	public ResponseDto updateEmpleado(EmpleadosDTO nuevosDatos) {
		ResponseDto response = new ResponseDto();

		try {
			Empleados empleadoActivo = empleadosDAO.getEmpleadosByRFCActivo(nuevosDatos.getRfc());

			if (empleadoActivo != null) {

				Empleados datosActualizados = new Empleados();

				datosActualizados.setIdEmpleado(nuevosDatos.getIdEmpleado());
				datosActualizados.setNombreCompleto(nuevosDatos.getNombreCompleto());
				datosActualizados.setRfc(nuevosDatos.getRfc());
				datosActualizados.setCurp(nuevosDatos.getCurp());
				datosActualizados.setEdad(nuevosDatos.getEdad());
				datosActualizados.setSexo(nuevosDatos.getSexo());
				datosActualizados.setDireccion(nuevosDatos.getDireccion());
				datosActualizados.setNss(nuevosDatos.getNss());
				datosActualizados.setTelefono(nuevosDatos.getTelefono());
				datosActualizados.setActivo(nuevosDatos.getActivo());

				empleadosDAO.update(datosActualizados);

				response.setCode(200);
				response.setMessage("¡EL REGISTRO DEL EMPLEADO SE ACTUALIZÓ CORRECTAMENTE!");
			} else {
				response.setCode(500);
				response.setMessage("¡EL EMPLEADO ESTÁ DADO DE BAJA, NO PUEDE ACTUALIZAR SU INFORMACIÓN!");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO updateEmpleado.");
		}
		return response;
	}

	@Override
	public ResponseDto getEmpleadosMasculinos() {
		ResponseDto response = new ResponseDto();

		try {
			List<Empleados> listaEmpleadosMasculinos = empleadosDAO.getEmpleadosMasculinos();

			if (!(listaEmpleadosMasculinos.isEmpty()) && listaEmpleadosMasculinos != null) {
				response.setCode(200);
				response.setMessage("EXISTEN " + listaEmpleadosMasculinos.size()
						+ " REGISTRO(S) DE SEXO MASCULINO EN LA BASE DE DATOS");
				response.setContent(listaEmpleadosMasculinos);
			} else {
				response.setCode(400);
				response.setMessage("¡NO EXISTEN REGISTROS EN LA BASE DE DATOS!");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					"OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO getEmpleadosMasculino.");
		}

		return response;
	}

	@Override
	public ResponseDto getEmpleadosFemenino35() {
		ResponseDto response = new ResponseDto();

		try {
			List<Empleados> listaEmpleadosF35 = empleadosDAO.getEmpleadosF35();

			if (!listaEmpleadosF35.isEmpty()) {
				response.setCode(200);
				response.setMessage("EXISTEN " + listaEmpleadosF35.size()
						+ " REGISTRO(S) DE SEXO FEMENINO Y 35 AÑOS DE EDAD EN LA BASE DE DATOS");
				response.setContent(listaEmpleadosF35);
			} else {
				response.setCode(400);
				response.setMessage("¡NO EXISTEN REGISTROS EN LA BASE DE DATOS!");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					"OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO getEmpleadosFemenino35.");
		}

		return response;
	}

	@Override
	public ResponseDto getEmpleadoByRFC(EmpleadosDTO empleado) {
		ResponseDto response = new ResponseDto();

		try {
			Empleados empleadoDatos = empleadosDAO.getEmpleadosByRFC(empleado.getRfc());

			if (empleadoDatos != null) {
				response.setCode(200);
				response.setMessage("EMPLEADO ENCONTRADO:");
				response.setContent(empleadoDatos);
			} else {
				response.setCode(400);
				response.setMessage("¡EL EMPLEADO NO EXISTE EN LA BASE DE DATOS!");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("OCURRIÓ UN ERROR EN LA CLASE EmpleadosServiceImpl EN EL MÉTODO getUsuarioByRFC.");
		}
		return response;
	}

}
