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

import com.mongodb.client.FindIterable;
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
				Tipo tipoNuevo = new Tipo(dtoPublicacion.getTipo());
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
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			collection.deleteMany(eq("idPublicacion", idPublicacion));
			collection = mongoConnector.getCollection("ReaccionesPublicacion");
			collection.deleteMany(eq("idPublicacion", idPublicacion));
			manager.remove(publicacion);
		}
		return true;
	}

	@Override
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ReaccionesPublicacion");
			Document reaccionPublicacion = dtoReaccion.getDocumentPublicacion();
			Document reaccion = collection.find(eq("idPublicacion", dtoReaccion.getIdPublicacion())).first();
			if (reaccion != null) {
				Bson updateOperation = push("reaccion", String.valueOf(dtoReaccion.getReaccion()));
				Document update = collection.findOneAndUpdate(eq("idPublicacion", dtoReaccion.getIdPublicacion()),
						updateOperation);
			} else {
				collection.insertOne(reaccionPublicacion);
				DTOUsuario dtoUsuario = new DTOUsuario();
				dtoUsuario.setIdPersona(dtoReaccion.getIdPersona());
				puntoUsuario.getPuntosUsuario("ReaccionarPublicacion", dtoUsuario, manager);
			}
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean reaccionarComentario(DTOReaccion dtoReaccion) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ReaccionesComentario");
			Document reaccionComentario = dtoReaccion.getDocumentComentario();
			Document reaccion = collection.find(eq("idComentario", dtoReaccion.getIdComentario())).first();
			if (reaccion != null) {
				Bson updateOperation = push("reaccion", String.valueOf(dtoReaccion.getReaccion()));
				Document update = collection.findOneAndUpdate(eq("idComentario", dtoReaccion.getIdComentario()),
						updateOperation);
			} else {
				collection.insertOne(reaccionComentario);
				DTOUsuario dtoUsuario = new DTOUsuario();
				dtoUsuario.setIdPersona(dtoReaccion.getIdPersona());
				puntoUsuario.getPuntosUsuario("ReaccionarComentario", dtoUsuario, manager);
			}
			return true;
		} catch (Exception exception) {
			return false;
		}
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
	public DTOComentario altaComentario(DTOComentario dtoComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			Document comentario = dtoComentario.getDocument();
			if (dtoComentario.getIdComentarioPadre() != "" && dtoComentario.getIdComentarioPadre() != null) {
				Bson updateOperation = push("comentarioHijo", comentario);
				Document old = collection.findOneAndUpdate(
						eq("_id", new ObjectId(dtoComentario.getIdComentarioPadre())), updateOperation);
				dtoComentario.setIdComentario(String.valueOf(comentario.getObjectId("_id")));
			} else {
				collection.insertOne(comentario);
				dtoComentario.setIdComentario(String.valueOf(comentario.getObjectId("_id")));				
			}
			DTOUsuario dtoUsuario = new DTOUsuario();
			dtoUsuario.setIdPersona(dtoComentario.getIdPersona());
			puntoUsuario.getPuntosUsuario("ComentarPublicacion", dtoUsuario, manager);
			return dtoComentario;
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public boolean bajaComentario(String idComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			collection.deleteMany(eq("_id", new ObjectId(idComentario)));
			collection = mongoConnector.getCollection("ReaccionesComentario");
			collection.deleteMany(eq("idComentario", new ObjectId(idComentario)));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean modificarComentario(DTOComentario dtoComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");
			Bson updateOperation = push("contenido", dtoComentario.getContenido());
			Document old = collection.findOneAndUpdate(
					eq("_id", new ObjectId(dtoComentario.getIdComentario())), updateOperation);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
}
