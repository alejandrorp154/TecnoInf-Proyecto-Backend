package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.datatypes.DTOChat;
import com.javaee.pryectoBack.datatypes.DTOMensaje;
import com.javaee.pryectoBack.util.ControladorChatFirebase;

@Stateless
@Remote(ControladorChatRemote.class)
public class ControladorChat implements ControladorChatLocal, ControladorChatRemote {

	private ControladorChatFirebase controladorChat;
	
	@Override
	public List<DTOChat> obtenerHistorial(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOMensaje> obtenerMensajes(int idChat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(int idChat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crear(String idPersona, String idPersona2, DTOChat dtoChat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarMensaje(DTOMensaje dtoMensaje) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearSala(List<String> idUsuarios, DTOChat dtoChat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarUsuario(int idChat, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}
}
