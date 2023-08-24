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

import com.mx.proyecto.Services.IMaestros;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;

@Controller
@RequestMapping("maestro")
public class MaestroController {
	@Autowired
	private IMaestros iMaestros;

	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS, Y NO UNA VISTA
    @RequestMapping(value="/getMaestros", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity < ResponseDto > getMaestros(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response  = new ResponseDto();
				response = iMaestros.getMaestros();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);	
    }
	@ResponseBody
	@RequestMapping(value = "/insertMaestros", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertMaestros (@RequestBody Maestros nuevoMaestro){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
					response = iMaestros.insertMaestros(nuevoMaestro);
					
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);	
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateMaestros", method = RequestMethod.PUT, produces = "application/json")
	ResponseEntity<ResponseDto> updateMaestros (@RequestBody Maestros nuevoMaestro){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
					response = iMaestros.updateMaestros(nuevoMaestro);
					
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);	
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteMaestros", method = RequestMethod.DELETE, produces = "application/json")
	ResponseEntity<ResponseDto> deleteMaestros (@RequestBody Maestros maestro){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
					response = iMaestros.deleteMaestros(maestro);
					
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);	
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertMaestrosMasivo", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertMaestrosMasivo (@RequestBody Maestros[] nuevosMaestros){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
					response = iMaestros.insertMaestrosMasivo(nuevosMaestros);
					
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
		
	}
}
	
