package com.mx.proyecto.Util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mx.proyecto.Dto.Cursos;

public class Util {

	
	public List<Cursos> leerArchivoCurso(){
		List<Cursos> cursos = new ArrayList<>();
		
		Cursos curso = null;
		BufferedReader buffer = null;
//		OBJETO QUE ALMACENA LOS ERRORES PRESENTADOS EN EL LAYOUT
		try {
			// Obtenci�n de archivo
			//      home/work/dist/trabajo/archivosCursos/CURSOS.txt
			FileInputStream fileInput = new FileInputStream("C:\\Users\\juan_\\Documents\\CURSOS.txt");
			// Apertura de archivo
			DataInputStream dataInput = new DataInputStream(fileInput);
			buffer = new BufferedReader(new InputStreamReader(dataInput)); 
			
			String linea = "";
			int lineas = 0;
			
			// Bucle de lectura de archivo data stage
			while ((linea = buffer.readLine()) != null) {
				curso = new Cursos();
				
				System.out.println(linea);
				String separador = Pattern.quote("|");
				String[] parts = linea.split(separador);
				lineas++;
			
					
					curso.setNombreCurso(parts[0]);
					curso.setClave(parts[1]);
					curso.setSATCA(parts[2]);					

				cursos.add(curso);

			}
			// Cierre de archivo data stage
			dataInput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo especificado: "+e.getMessage());
		}
		catch (IOException e) {
			System.out.println("No se encontró el archivo especificado: "+e.getMessage());
		}
		
		
		
		
		return cursos;
	}
	
	
}
