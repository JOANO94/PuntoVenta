package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;

public interface ICursos {
	
	ResponseDto getCursos(); //ESTE M�TODO OBTIENE ASPIRANTES
	ResponseDto insertCursos(Cursos nuevoCurso);
	ResponseDto insertCursosMasivo(Cursos[] nuevosCursos);
	ResponseDto insertCursosMasivoByFile();
	ResponseDto updateCursos(Cursos curso);
	ResponseDto deleteCursos(Cursos curso);
}
