package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;

public interface IAspirantes {
	
	ResponseDto getAspirantes(); //ESTE MÉTODO OBTIENE ASPIRANTES
	ResponseDto insertAspirantes(Aspirantes nuevoAspirante);
	ResponseDto existeCurso(Aspirantes aspirante);
	ResponseDto insertAspirantesMasivo(Aspirantes[] nuevosAspirantes);
	ResponseDto updateAspirantes(Aspirantes aspirante);
	ResponseDto deleteAspirantes(Aspirantes aspirante);
	Aspirantes getAspiranteById(Aspirantes aspirante);
	
}
