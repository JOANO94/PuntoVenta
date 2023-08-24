package com.mx.proyecto.ServicesImplement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.ICursosRepository;
import com.mx.proyecto.Services.ICursos;
import com.mx.proyecto.Util.Util;

@Service
public class CursosServiceImpl implements ICursos{
	
	@Autowired
	private ICursosRepository iCursosRepository;

	@Override
	public ResponseDto getCursos() {
		ResponseDto response = new ResponseDto();

		try {
			List<Cursos> lista = iCursosRepository.getCursos();
			//System.out.println("LISTA : "+lista.get(1).getMaestro());
			if (lista != null && lista.size() > 0  ) {
				response.setCode(0);
				response.setMessage("HAY " + lista.size() + " REGISTROS QUE MOSTRAR DE LA TABLA CURSOS.");
                response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("NO EXISTEN REGISTROS EN LA TABLA CURSOS.");
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
	public ResponseDto insertCursos(Cursos nuevoCurso) {
		
		String camposErroneos = "";
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			if(nuevoCurso.getNombreCurso() ==null || nuevoCurso.getNombreCurso().isEmpty()) {
				camposErroneos = camposErroneos + ("1. El campo nombreCurso no debe ser NULO ni VACÍO.");
			}
			if (nuevoCurso.getClave() == null || nuevoCurso.getClave().isEmpty()) {
				camposErroneos = camposErroneos + (" 2. El campo clave no debe ser NULO ni VACÍO.");
			}
			if (nuevoCurso.getSATCA() == null || nuevoCurso.getSATCA().isEmpty()) {
				camposErroneos = camposErroneos + (" 3. El campo SATCA no debe ser NULO ni VACIO.");
			}
			System.out.println(camposErroneos);
			if(camposErroneos.isEmpty()) {
				respuesta = iCursosRepository.insertCursos(nuevoCurso);
				if(respuesta == 1) {
					response.setCode(0);
					response.setMessage("¡SE INSERTÓ CORRECTAMENTE UN REGISTRO EN LA TABLA CURSOS!");
				}else {
					response.setCode(-1);
					response.setMessage("¡NO SE INSERTÓ CORRECTAMENTE EL REGISTRO!");
				}
			}else {
					response.setCode(-2);
					response.setMessage(camposErroneos);
					}
		}catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage("No existe registros.");

		}
		catch(IndexOutOfBoundsException aw) {
			System.out.println(aw.getMessage());
		    response.setCode(-3);
			response.setMessage(" No existe el elemento al cual quieres acceder.");
		}catch(Exception e) {
			response.setCode(-3);
			response.setMessage(camposErroneos);
		}
		
		return response;
	}

	@Override
	public ResponseDto updateCursos(Cursos curso) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			respuesta = iCursosRepository.updateCursos(curso);
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
	public ResponseDto deleteCursos(Cursos curso) {
		
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			respuesta = iCursosRepository.deleteCursos(curso);
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
	public ResponseDto insertCursosMasivo(Cursos[] nuevosCursos) {
		ResponseDto response = new ResponseDto();
		
		List<Cursos> listaCursos = new ArrayList<Cursos>();
		
		for(Cursos curso : nuevosCursos) {
			listaCursos.add(curso);
		}
		
		iCursosRepository.insertCursosMasivo(listaCursos);
		
		response.setMessage("¡SE INSERTARON CORRECTAMENTE LOS " + listaCursos.size() + " REGISTROS!");
		return response;
	}

	@Override
	public ResponseDto insertCursosMasivoByFile() {
		ResponseDto response = new ResponseDto();
           Util llamar = new Util();
           
           List<Cursos> listaCursos =  llamar.leerArchivoCurso();

           iCursosRepository.insertCursosMasivo(listaCursos);
   		
   		response.setMessage("¡SE INSERTARON CORRECTAMENTE LOS " + listaCursos.size() + " REGISTROS!");
		return response;
	}

}