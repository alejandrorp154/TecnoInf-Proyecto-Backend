package com.javaee.pryectoBack.util;

import java.util.List;

import com.javaee.pryectoBack.datatypes.DTOChat;
import com.javaee.pryectoBack.datatypes.DTOMensaje;

public class ControladorChatFirebase {

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
