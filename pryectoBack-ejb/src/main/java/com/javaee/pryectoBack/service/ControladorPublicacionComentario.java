package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorPublicacionComentarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;

@Stateless
@Remote(ControladorPublicacionComentarioRemote.class)
public class ControladorPublicacionComentario
		implements ControladorPublicacionComentarioLocal, ControladorPublicacionComentarioRemote {

	@EJB
	private ControladorPublicacionComentarioDALocal controladorPublicacionComentarioDA;

	@Override
	public boolean altaComentario(DTOComentario dtoComentario) {
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
	public List<DTOPublicacion> obtenerPublicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
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
	public DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion) {
		return controladorPublicacionComentarioDA.altaPublicacion(dtoPublicacion);
	}

}
