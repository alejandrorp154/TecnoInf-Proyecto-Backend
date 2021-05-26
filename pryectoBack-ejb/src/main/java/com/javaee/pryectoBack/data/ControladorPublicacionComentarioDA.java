package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.model.Publicacion;

@Singleton
public class ControladorPublicacionComentarioDA
		implements ControladorPublicacionComentarioDALocal, ControladorPublicacionComentarioDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public ControladorPublicacionComentarioDA()
	{
	}
	
	@Override
	public List<DTOPublicacion> obtenerPublicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificarPublicacion(DTOPublicacion dtoPublicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion) {
		try {
			Publicacion publicacion = new Publicacion(dtoPublicacion);
			manager.persist(publicacion);
			DTOPublicacion dtoPubli = new DTOPublicacion(publicacion);
			return dtoPubli;
		} catch (Exception exception) {
			return new DTOPublicacion();
		}
	}

}
