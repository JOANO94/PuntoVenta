package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.EmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;

public interface EmpleadosService {

	ResponseDto insertEmpleado(EmpleadosDTO nuevoEmpleado);

	ResponseDto deleteEmpleado(EmpleadosDTO idEmpleado);

	ResponseDto updateEmpleado(EmpleadosDTO nuevosDatos);

	ResponseDto getEmpleadosMasculinos();

	ResponseDto getEmpleadosFemenino35();

	ResponseDto getEmpleadoByRFC(EmpleadosDTO empleado);

}
