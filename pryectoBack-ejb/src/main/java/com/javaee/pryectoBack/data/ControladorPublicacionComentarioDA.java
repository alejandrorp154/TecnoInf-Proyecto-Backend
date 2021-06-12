package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Tipo;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.MongoCollection;

@Singleton
public class ControladorPublicacionComentarioDA
		implements ControladorPublicacionComentarioDALocal, ControladorPublicacionComentarioDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	private PuntosUsuario puntoUsuario;

	public ControladorPublicacionComentarioDA() {
		puntoUsuario = new PuntosUsuario();
	}

	@Override
	public List<DTOPublicacion> obtenerPublicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificarPublicacion(DTOPublicacion dtoPublicacion) {
		Publicacion publicacion = manager.find(Publicacion.class, dtoPublicacion.getIdPublicacion());
		if (publicacion != null) {
			publicacion.setContenido(dtoPublicacion.getContenido());
			publicacion.setExtension(dtoPublicacion.getExtension());
			publicacion.setFecha(dtoPublicacion.getFecha());
			if (publicacion.getTipo().getTipo() == dtoPublicacion.getTipo().getTipo()) {
				manager.remove(publicacion.getTipo());
				Tipo tipoNuevo  = new Tipo(dtoPublicacion.getTipo());
				publicacion.setTipo(tipoNuevo);
				manager.merge(tipoNuevo);
			}			
			publicacion.setNombre(dtoPublicacion.getNombre());
			manager.merge(publicacion);
		}		
		return true;
	}
	
	@Override
	public boolean bajaPublicacion(int idPublicacion) {
		Publicacion publicacion = manager.find(Publicacion.class, idPublicacion);
		if (publicacion != null) {
			manager.remove(publicacion.getTipo());
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");
			collection.deleteMany(eq("idPublicacion",idPublicacion));
			collection = mongoConnector.getCollection("ReaccionesPublicacion");
			collection.deleteMany(eq("idPublicacion",idPublicacion));
			manager.remove(publicacion);			
		}		
		return true;
	}

	@Override
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion) {
		try {
			DTOPublicacion dtoPubli = new DTOPublicacion();
			Publicacion publicacion = new Publicacion(dtoPublicacion);
			Usuario usuario = manager.find(Usuario.class, dtoPublicacion.getPerfil().getUsuario().getIdPersona());
			if (usuario != null) {
				publicacion.getPerfil().setUsuario(usuario);
				manager.persist(publicacion);
				Tipo tipo = new Tipo();
				tipo.setTipo(publicacion.getTipo().getTipo());
				tipo.setIdPublicacion(publicacion.getIdPublicacion());
				publicacion.setTipo(tipo);
				manager.merge(tipo);
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				puntoUsuario.getPuntosUsuario("AltaPublicacion", dtoUsuario, manager);
				dtoPubli = new DTOPublicacion(publicacion);
			}
			return dtoPubli;
		} catch (Exception exception) {
			return new DTOPublicacion();
		}
	}

	@Override
	public boolean altaComentario(DTOComentario dtoComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");
			Document comentario = dtoComentario.getDocument();
			if (dtoComentario.getIdComentarioPadre() != "" && dtoComentario.getIdComentarioPadre() != null) {
				Bson updateOperation = push("comentarioHijo", comentario);
				Document old = collection.findOneAndUpdate(eq("_id", new ObjectId(dtoComentario.getIdComentarioPadre())), updateOperation);
				System.out.println(old.toJson());
			} else {
				collection.insertOne(comentario);			
			}			
			// Falta agregar que le de puntos al usuario
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

}
