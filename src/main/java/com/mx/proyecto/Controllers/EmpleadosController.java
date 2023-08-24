package com.mx.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Dto.EmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.EmpleadosService;

@Controller
@RequestMapping("empleados")
public class EmpleadosController {

	@Autowired
	public EmpleadosService empleadosService;

	@ResponseBody
	@RequestMapping(value = "/registrarEmpleado", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> registrarEmpleado(@RequestBody EmpleadosDTO nuevoEmpleado) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = empleadosService.insertEmpleado(nuevoEmpleado);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/eliminarEmpleado", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ResponseDto> eliminarEmpleado(@RequestBody EmpleadosDTO idEmpleado) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto respuesta = empleadosService.deleteEmpleado(idEmpleado);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/actualizarDatos", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> actualizarDatosUsuario(@RequestBody EmpleadosDTO nuevosDatos) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto respuesta = empleadosService.updateEmpleado(nuevosDatos);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/obtenerMasculino", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getEmpleadosMasculinos() {

		return empleadosService.getEmpleadosMasculinos();
	}

	@ResponseBody
	@RequestMapping(value = "/obtenerFemenino35", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getEmpleadosFemenino35() {

		return empleadosService.getEmpleadosFemenino35();
	}

	@ResponseBody
	@RequestMapping(value = "/obtenerEmpleadoRFC", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getUsuarioAdmin2(@RequestBody EmpleadosDTO empleado) {

		return empleadosService.getEmpleadoByRFC(empleado);
	}

}
