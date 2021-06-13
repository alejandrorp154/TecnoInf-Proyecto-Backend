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
	public boolean bajaComentario(int idComentario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarComentario(DTOComentario dtoComentario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reaccionarComentario(int idComentario, DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion) {
		return controladorPublicacionComentarioDA.altaPublicacion(dtoPublicacion);
	}

}
