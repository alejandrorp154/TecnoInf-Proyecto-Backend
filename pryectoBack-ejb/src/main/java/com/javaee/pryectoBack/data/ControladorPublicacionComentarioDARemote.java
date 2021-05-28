package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Remote
public interface ControladorPublicacionComentarioDARemote {
	List<DTOPublicacion> obtenerPublicaciones(String idPersona);
	boolean modificarPublicacion(DTOPublicacion dtoPublicacion);
	boolean reaccionPublicacion(DTOReaccion dtoReaccion);
	DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion);
	public boolean altaComentario(String idPublicacion, DTOComentario dtoComentario);
}
