package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.model.Comentario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Tipo;

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
			Tipo tipo = new Tipo();
			tipo.setTipo(publicacion.getTipo().getTipo());
			tipo.setIdPublicacion(publicacion.getIdPublicacion());
			publicacion.setTipo(tipo);
			manager.merge(tipo);
			DTOPublicacion dtoPubli = new DTOPublicacion(publicacion);
			return dtoPubli;
		} catch (Exception exception) {
			return new DTOPublicacion();
		}
	}
	
	@Override
	public boolean altaComentario(String idPublicacion, DTOComentario dtoComentario) {
		try {
			//Queda crear la relacion Publicacion Comentario, de donde lo obtengo?
			Comentario comentario = new Comentario(dtoComentario);
			manager.persist(comentario);
			Publicacion publicacion = manager.find(Publicacion.class, idPublicacion);
			
			
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

}
