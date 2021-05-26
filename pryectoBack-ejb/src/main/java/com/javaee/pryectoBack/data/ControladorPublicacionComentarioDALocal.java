package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Local
public interface ControladorPublicacionComentarioDALocal {
	List<DTOPublicacion> obtenerPublicaciones(String idPersona);
	boolean modificarPublicacion(DTOPublicacion dtoPublicacion);
	boolean reaccionPublicacion(DTOReaccion dtoReaccion);
	DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion);
}
