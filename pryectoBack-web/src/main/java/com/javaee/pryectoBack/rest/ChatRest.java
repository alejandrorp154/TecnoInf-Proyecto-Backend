package com.javaee.pryectoBack.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOChat;
import com.javaee.pryectoBack.datatypes.DTOMensaje;
import com.javaee.pryectoBack.service.ControladorChatLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/chat")
@RequestScoped
@Api(value = "/chat", description = "chat!")
public class ChatRest {

	@EJB
	private ControladorChatLocal controladorChat;
	
	public List<DTOChat> obtenerHistorial(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOMensaje> obtenerMensajes(int idChat) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean eliminar(int idChat) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean crear(String idPersona, String idPersona2, DTOChat dtoChat) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean agregarMensaje(DTOMensaje dtoMensaje) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean crearSala(List<String> idUsuarios, DTOChat dtoChat) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean agregarUsuario(int idChat, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}
}
