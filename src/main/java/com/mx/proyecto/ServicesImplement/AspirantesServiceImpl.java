package com.mx.proyecto.ServicesImplement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.IAspirantesRepository;
import com.mx.proyecto.Services.IAspirantes;

@Service
public class AspirantesServiceImpl implements IAspirantes {
	
	@Autowired
	private IAspirantesRepository iAspirantesRepository;
	
	@Override
	public ResponseDto getAspirantes() {
		ResponseDto response = new ResponseDto();

		try {
			List<Aspirantes> lista = iAspirantesRepository.getAspirantes();
			//System.out.println("LISTA : "+lista.get(1).getMaestro());
			if (lista != null && lista.size() > 0  ) {
				response.setCode(0);
				response.setMessage("HAY " + lista.size() + " REGISTROS QUE MOSTRAR DE LA TABLA ASPIRANTES.");
                response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("NO EXISTEN REGISTROS EN LA TABLA ASPIRANTES");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage("No existen registros.");

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
	public ResponseDto insertAspirantes(Aspirantes nuevoAspirante) {
		System.out.println(nuevoAspirante.getFechaInscripcion());
		String respuestaMensaje = "";
		ResponseDto response = new ResponseDto();
		Integer id = 0;
		Integer hayCurso = 0;
		Integer hayMaestro = 0;
		int respuesta = 0;
		try {
			
			if(nuevoAspirante.getNombreAlumno() == null || nuevoAspirante.getNombreAlumno().equals("")) {
				respuestaMensaje = respuestaMensaje + " 1.El campo nombreAlumno no debe ser NULO ni VACÍO.";
				//System.out.println("Valida NOMBRE");
			}
			if(!(nuevoAspirante.getEdad() > 0 && nuevoAspirante.getEdad() <= 100)) {
				respuestaMensaje = respuestaMensaje + " 2.La edad debe ser a dos posiciones y ser mayor de edad.";
				//System.out.println("Valida EDAD");
			}
			if(nuevoAspirante.getFechaInscripcion() == null || nuevoAspirante.getFechaInscripcion().equals("")) {
				respuestaMensaje = respuestaMensaje + " 3.El campo fechaInscripcion no debe ser NULO ni VACIO en formato AÑO-MES-DIA.";
				//System.out.println("Valida FEC_INSCRIPCION");
			}
			if(!(nuevoAspirante.getCursoId() > 0 && nuevoAspirante.getCursoId() < 99999)) {
				respuestaMensaje = respuestaMensaje + " 4.El campo cursoId no debe ser 0, NULO ni VACÍO  y ser a cinco posiciones.";
				
				//System.out.println("Valida CURSOID");
			}
			id = nuevoAspirante.getCursoId();
			hayCurso = iAspirantesRepository.cantidadCurso(id);
			
			if(hayCurso == 0) {
				respuestaMensaje = respuestaMensaje + " 4.1 EL CURSO NO EXISTE EN LA TABLA CURSOS." + " LOS CURSOS DISPONIBLES SON: " + 
													iAspirantesRepository.getCursos();
			}
			if(!(nuevoAspirante.getMaestroId() > 0 && nuevoAspirante.getMaestroId() < 99999)) {
				respuestaMensaje = respuestaMensaje + " 5.El campo maestroId no debe ser 0, NULO ni VACÍO y ser a cinco posiciones.";
				//System.out.println("Valida MAESTROID");
			}
			id = nuevoAspirante.getMaestroId();
			hayMaestro = iAspirantesRepository.cantidadMaestro(id);
			
			if(hayMaestro == 0) {
				respuestaMensaje = respuestaMensaje + " 5.1 EL MAESTRO NO EXISTE EN LA TABLA MAESTROS." + " LOS MAESTROS DISPONIBLES SON: " + 
													iAspirantesRepository.getMaestros();
			}
			System.out.println(respuestaMensaje);
			if(respuestaMensaje.equals("")) {
				respuesta = iAspirantesRepository.insertAspirantes(nuevoAspirante);
				if (respuesta == 1) {
					response.setCode(0);
					response.setMessage("¡SE INSERTÓ CORRECTAMENTE UN REGISTRO EN LA TABLA ASPIRANTES!");
				} else {
					response.setCode(-1);
					response.setMessage("¡NO SE INSERTÓ CORRECTAMENTE EL REGISTRO!");
				}
			}else {
				response.setCode(-2);
				response.setMessage(respuestaMensaje);
			}
			
			
		}
		/*catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
		    response.setCode(-2);
			response.setMessage(" No existe registros.");

		}
		catch(IndexOutOfBoundsException aw) {
			System.out.println(aw.getMessage());
		    response.setCode(-3);
			response.setMessage("No existe el elemento al cual quieres acceder.");
		}*/
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedió un error, por favor verifique.");
		}
		
		return response;
	}

	@Override
	public ResponseDto updateAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			respuesta = iAspirantesRepository.updateAspirantes(aspirante);
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
	public ResponseDto deleteAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		try {
			respuesta = iAspirantesRepository.deleteAspirantes(aspirante);
			if(respuesta == 1) {
				response.setCode(0);
				response.setMessage("¡EL REGISTRO SE ELIMINÓ CORRECTAMENTE!");
				
			}
			else {
				response.setCode(-1);
				response.setMessage("¡EL REGISTRO NO SE ELIMINÓ CORRECTAMENTE!");		
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
	public ResponseDto insertAspirantesMasivo(Aspirantes[] nuevosAspirantes) {
		ResponseDto response = new ResponseDto();
		
		List<Aspirantes> listaAspirantes = new ArrayList<Aspirantes>();
		
		for(Aspirantes aspirante : nuevosAspirantes) {
			listaAspirantes.add(aspirante);
		}
		
		iAspirantesRepository.insertAspirantesMasivo(listaAspirantes);
		
		response.setMessage("¡SE INSERTARON CORRECTAMENTE LOS " + listaAspirantes.size() + " REGISTROS!");
		return response;
	}

	@Override
	public Aspirantes getAspiranteById(Aspirantes aspirante) {
		return null;
	}

	@Override
	public ResponseDto existeCurso(Aspirantes aspirante) {
		// TODO Auto-generated method stub
		return null;
	}


}
