package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;

public interface IMaestros {
	ResponseDto getMaestros(); //ESTE MÉTODO OBTIENE ASPIRANTES
	ResponseDto insertMaestros(Maestros nuevoMaestro);
	ResponseDto insertMaestrosMasivo(Maestros[] nuevosMaestros);
	ResponseDto updateMaestros(Maestros maestro);
	ResponseDto deleteMaestros(Maestros maestro);
}
