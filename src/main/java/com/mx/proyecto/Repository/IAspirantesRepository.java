package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Aspirantes;

public interface IAspirantesRepository {
	
	List<Aspirantes> getAspirantes();
	Integer insertAspirantes(Aspirantes nuevoAspirante);
	Integer updateAspirantes(Aspirantes aspirante);
	Integer deleteAspirantes(Aspirantes aspirante);
	Aspirantes getAspiranteById(Aspirantes aspirante);
	int[][] insertAspirantesMasivo(List<Aspirantes> nuevosAspirantes);
	List<Integer> getCursos();
	Integer cantidadCurso(Integer id);
	List<Integer> getMaestros();
	Integer cantidadMaestro(Integer id);
}
