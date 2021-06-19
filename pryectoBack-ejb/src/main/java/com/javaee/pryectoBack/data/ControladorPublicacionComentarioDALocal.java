package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Local
public interface ControladorPublicacionComentarioDALocal {
	List<DTOPublicacion> obtenerPublicaciones(String idPersona);
	boolean modificarPublicacion(DTOPublicacion dtoPublicacion);
	boolean reaccionPublicacion(DTOReaccion dtoReaccion);
	boolean reaccionarComentario(DTOReaccion dtoReaccion);
	DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion);
	public DTOComentario altaComentario(DTOComentario dtoComentario);
	boolean bajaPublicacion(int idPublicacion);
	boolean bajaComentario(String idComentario);
	boolean modificarComentario(DTOComentario dtoComentario);	
	List<DTOComentario> getComentarios(int idPublicacion);
}
