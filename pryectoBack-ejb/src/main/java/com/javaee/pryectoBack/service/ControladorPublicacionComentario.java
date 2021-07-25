package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorPublicacionComentarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOCantidadReaccionComentario;
import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOPublicacionPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Stateless
@Remote(ControladorPublicacionComentarioRemote.class)
public class ControladorPublicacionComentario
		implements ControladorPublicacionComentarioLocal, ControladorPublicacionComentarioRemote {

	@EJB
	private ControladorPublicacionComentarioDALocal controladorPublicacionComentarioDA;

	@Override
	public DTOComentario altaComentario(DTOComentario dtoComentario) {
		return controladorPublicacionComentarioDA.altaComentario(dtoComentario);
	}

	@Override
	public boolean bajaComentario(String idComentario) {
		return controladorPublicacionComentarioDA.bajaComentario(idComentario);
	}

	@Override
	public boolean modificarComentario(DTOComentario dtoComentario) {
		return controladorPublicacionComentarioDA.modificarComentario(dtoComentario);
	}

	@Override
	public boolean reaccionarComentario(DTOReaccion dtoReaccion) {
		return controladorPublicacionComentarioDA.reaccionarComentario(dtoReaccion);
	}

	@Override
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		return controladorPublicacionComentarioDA.reaccionPublicacion(dtoReaccion);
	}

	@Override
	public List<DTOPublicacionPerfilUsuario> obtenerPublicaciones(String idPersona, int offset, int size) {
		return controladorPublicacionComentarioDA.obtenerPublicaciones(idPersona, offset, size);
	}

	@Override
	public DTOPublicacionPerfilUsuario obtenerPublicacion(int idPublicacion) {
		return controladorPublicacionComentarioDA.obtenerPublicacion(idPublicacion);
	}

	@Override
	public boolean modificarPublicacion(DTOPublicacion dtoPublicacion) {
		return controladorPublicacionComentarioDA.modificarPublicacion(dtoPublicacion);
	}

	@Override
	public boolean bajaPublicacion(int idPublicacion) {
		return controladorPublicacionComentarioDA.bajaPublicacion(idPublicacion);
	}

	@Override
	public DTOPublicacionPerfilUsuario altaPublicacion(DTOPublicacion dtoPublicacion) {
		return controladorPublicacionComentarioDA.altaPublicacion(dtoPublicacion);
	}
	
	@Override
	public List<DTOComentario> getComentarios(int idPublicacion) {
		return controladorPublicacionComentarioDA.getComentarios(idPublicacion);
	}

	@Override
	public DTOCantidadReaccionComentario getCantidadReaccionComentario(int idPublicacion) {
		return controladorPublicacionComentarioDA.getCantidadReaccionComentario(idPublicacion);
	}
}
