package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOChat;
import com.javaee.pryectoBack.datatypes.DTOMensaje;

@Remote
public interface ControladorChatRemote {
	List<DTOChat> obtenerHistorial(String idPersona);
	List<DTOMensaje> obtenerMensajes(int idChat);
	boolean eliminar(int idChat);
	boolean crear(String idPersona, String idPersona2, DTOChat dtoChat);
	boolean agregarMensaje(DTOMensaje dtoMensaje);
	boolean crearSala(List<String> idUsuarios, DTOChat dtoChat);
	boolean agregarUsuario(int idChat, String idPersona);
}
