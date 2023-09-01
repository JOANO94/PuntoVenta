package com.mx.proyecto.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value = "LoginService")// LoginService -> se manda a llamar desde el index.jsp,
//	se usa para redireccionar al archivo Login.jsp
	public String Login() {
		
		return "Login";// Login -> Es el archivo Login.jsp que se encuentra en la ruta WEB-INF/views/Login.jsp
	}
	
	
	@RequestMapping(value ="/Inicio")// -> Inicio se manda a llamar desde SecurityConfig 
	public String IniciarPagina(){
		
		return "Inicio"; // Inicio -> Es el archivo Inicio.jsp que se encuentar en la ruta WEB-INF/views/Inicio.jsp
    }

}