package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Remote
public interface ControladorPublicacionComentarioRemote {
	DTOComentario altaComentario(DTOComentario dtoComentario);
	boolean bajaComentario(String idComentario);
	boolean modificarComentario(DTOComentario dtoComentario);
	boolean reaccionarComentario(DTOReaccion dtoReaccion);
	List<DTOPublicacion> obtenerPublicaciones(String idPersona);
	boolean modificarPublicacion(DTOPublicacion dtoPublicacion);
	boolean reaccionPublicacion(DTOReaccion dtoReaccion);
	DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion);
	boolean bajaPublicacion(int idPublicacion);
	List<DTOComentario> getComentarios(int idPublicacion);
}