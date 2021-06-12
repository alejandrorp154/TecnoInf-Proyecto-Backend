package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Remote
public interface ControladorPublicacionComentarioRemote {
	boolean altaComentario(DTOComentario dtoComentario);
	boolean bajaComentario(int idComentario);
	boolean modificarComentario(DTOComentario dtoComentario);
	boolean reaccionarComentario(int idComentario, DTOReaccion dtoReaccion);
	List<DTOPublicacion> obtenerPublicaciones(String idPersona);
	boolean modificarPublicacion(DTOPublicacion dtoPublicacion);
	boolean reaccionPublicacion(DTOReaccion dtoReaccion);
	DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion);
	boolean bajaPublicacion(int idPublicacion);
}