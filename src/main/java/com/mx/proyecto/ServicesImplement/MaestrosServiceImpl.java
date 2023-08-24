package com.mx.proyecto.ServicesImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.IMaestrosRepository;
import com.mx.proyecto.Services.IMaestros;

@Service
public class MaestrosServiceImpl implements IMaestros {
	
	@Autowired
	private IMaestrosRepository iMaestrosRepository;
	@Override
	public ResponseDto getMaestros() {
		ResponseDto response = new ResponseDto();

		try {
			List<Maestros> lista = iMaestrosRepository.getMaestros();
			//System.out.println("LISTA : "+lista.get(1).getMaestro());
			if (lista != null && lista.size() > 0  ) {
				response.setCode(0);
				response.setMessage("HAY " + lista.size() + " REGISTROS QUE MOSTRAR DE LA TABLA MAESTROS.");
                response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("NO EXISTEN REGISTROS EN LA TABLA MAESTROS.");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage("No existe registros.");

		}
		catch(IndexOutOfBoundsException aw) {
			System.out.println(aw.getMessage());
		    response.setCode(-3);
			response.setMessage(" No existe el elemento al cual quieres acceder.");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage(" Sucedió un error, por favor verifique.");
		}
		
		return response;
	}

	@Override
	public ResponseDto insertMaestros(Maestros nuevoMaestro) {
		
		String camposErroneos = "";
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			if(nuevoMaestro.getNombreMaestro() == null || nuevoMaestro.getNombreMaestro().isEmpty()) {
				camposErroneos = camposErroneos + ("1. El campo nombreMaestro no debe ser NULO ni VACÍO.");
			}
			if (nuevoMaestro.getDireccion() == null || nuevoMaestro.getDireccion().isEmpty()) {
				camposErroneos = camposErroneos + (" 2. El campo direccion no debe ser NULO ni VACÍO.");
			}
			if (nuevoMaestro.getTelefono() == null || nuevoMaestro.getTelefono().length() > 10) {
				camposErroneos = camposErroneos + (" 3. El campo telefono no debe ser 0, NULO NI VACÍO y ser a 10 posiciones.");
			}
			if(nuevoMaestro.getEmail() == null || nuevoMaestro.getEmail().isEmpty() ) {
				camposErroneos = camposErroneos + (" 4. El campo email no debe ser NULO ni VACIO.");
			}
			System.out.println(camposErroneos);
			if(camposErroneos.isEmpty()) {
				respuesta = iMaestrosRepository.insertMaestros(nuevoMaestro);
				if(respuesta == 1) {
					response.setCode(0);
					response.setMessage("¡SE INSERTÓ CORRECTAMENTE UN REGISTRO EN LA TABLA MAESTROS!");
				}else {
					response.setCode(-1);
					response.setMessage("¡NO SE INSERTÓ CORRECTAMENTE EL REGISTRO!");
				}
			}else {
					response.setCode(-2);
					response.setMessage(camposErroneos);
					}
		}catch(Exception e) {
			response.setCode(-3);
			response.setMessage(camposErroneos);
		}
		
		return response;
	}

	@Override
	public ResponseDto updateMaestros(Maestros maestro) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			respuesta = iMaestrosRepository.updateMaestros(maestro);
			if(respuesta == 1) {
				response.setCode(0);
				response.setMessage("¡EL REGISTRO SE ACTUALIZÓ CORRECTAMENTE!");
			}
			else {
				response.setCode(-1);
				response.setMessage("¡EL REGISTRO NO SE ACTUALIZÓ CORRECTAMENTE!");		
			}
		} 
		
		catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage("No existe registro.");

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedió un error, por favor verifique.");
		}
		
		return response;
	}
	@Override
	public ResponseDto deleteMaestros(Maestros maestro) {
		
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			respuesta = iMaestrosRepository.deleteMaestros(maestro);
			if(respuesta == 1) {
				response.setCode(0);
				response.setMessage("¡EL REGISTRO SE ELIMINÓ CORRECTAMENTE!");
			}else {
				response.setCode(-1);
				response.setMessage("¡EL REGISTRO NO SE ELIMINÓ CORRECTAMENTE!");
			}
			
		}catch(NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage("No existe registro.");
			
		}
		
		return response;
	}
	
	@Override
	public ResponseDto insertMaestrosMasivo(Maestros[] nuevosMaestros) {
		ResponseDto response = new ResponseDto();
		
		List<Maestros> listaMaestros = new ArrayList<Maestros>();
		
		for(Maestros maestro : nuevosMaestros) {
			listaMaestros.add(maestro);
		}
		
		iMaestrosRepository.insertMaestrosMasivo(listaMaestros);
		
		response.setMessage("¡SE INSERTARON CORRECTAMENTE LOS " + listaMaestros.size() + " REGISTROS!");
		return response;
	}

}
