package com.javaee.pryectoBack.data;

import java.util.ArrayList;
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
import com.javaee.pryectoBack.model.reacciones;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
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

	private Integer obtenerCantidadPadre(MongoCollection<Document> collection, String idPadre) {
		Document comentarioPadre = collection.find(eq("_id", new ObjectId(idPadre))).first();
		Integer count = 0;
		List<Document> comentariosHijo = (List) comentarioPadre.get("comentarioHijo");
		if (comentariosHijo.size() > 0) {
			for (Document document : comentariosHijo) {
				count++;
			}
		}
		return count;
	}

	@Override
	public DTOComentario altaComentario(DTOComentario dtoComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			Document comentario = dtoComentario.getDocument();
			collection.insertOne(comentario);
			dtoComentario.setIdComentario(String.valueOf(comentario.getObjectId("_id")));
			DTOUsuario dtoUsuario = new DTOUsuario();
			dtoUsuario.setIdPersona(dtoComentario.getIdPersona());
			puntoUsuario.getPuntosUsuario("ComentarPublicacion", dtoUsuario, manager);
			return dtoComentario;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
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
			System.out.println(exception.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarComentario(DTOComentario dtoComentario) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			Bson updateOperation = push("contenido", dtoComentario.getContenido());
			Document old = collection.findOneAndUpdate(eq("_id", new ObjectId(dtoComentario.getIdComentario())),
					updateOperation);
			return true;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private DTOComentario getArbolComentario(Document comentarioPadre,
			MongoCollection<Document> collectionComentariosPublicacion,
			MongoCollection<Document> reaccionesComentarios) {
		DTOComentario comentarioPadreDTO = new DTOComentario(comentarioPadre);
		FindIterable<Document> comentariosHijo = collectionComentariosPublicacion
				.find(eq("idComentarioPadre", comentarioPadreDTO.getIdComentario()));
		comentarioPadreDTO
				.setCantidadLikes(getCantidadReaccion(reacciones.MeGusta, comentarioPadre, reaccionesComentarios));
		comentarioPadreDTO
				.setCantidadDislikes(getCantidadReaccion(reacciones.NoMeGusta, comentarioPadre, reaccionesComentarios));
		if (comentariosHijo != null) {
			for (Document comentario : comentariosHijo) {
				DTOComentario hijo = new DTOComentario(comentario);
				hijo.setCantidadLikes(getCantidadReaccion(reacciones.MeGusta, comentario, reaccionesComentarios));
				hijo.setCantidadDislikes(getCantidadReaccion(reacciones.NoMeGusta, comentario, reaccionesComentarios));
				comentarioPadreDTO.getComentariosHijos().add(hijo);
			}
		}
		return comentarioPadreDTO;
	}

	private Integer getCantidadReaccion(reacciones reaccion, Document comentario,
			MongoCollection<Document> collection) {
		Integer cantidad = 0;
		FindIterable<Document> reacciones = collection
				.find(and(eq("idComentario", String.valueOf(comentario.get("_id"))), eq("reaccion", String.valueOf(reaccion))));
		for (Document document : reacciones) {
			cantidad++;
		}
		return cantidad;
	}

	@Override
	public List<DTOComentario> getComentarios(int idPublicacion) {
		try {
			List<DTOComentario> result = new ArrayList<DTOComentario>();
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collectionComentariosPublicacion = mongoConnector
					.getCollection("ComentariosPublicacion");
			MongoCollection<Document> reaccionesComentarios = mongoConnector.getCollection("ReaccionesComentario");
			FindIterable<Document> comentariosPadre = collectionComentariosPublicacion
					.find(and(eq("idPublicacion", idPublicacion), eq("idComentarioPadre", null)));
			for (Document comentario : comentariosPadre) {
				result.add(getArbolComentario(comentario, collectionComentariosPublicacion, reaccionesComentarios));
			}
			return result;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return null;

		}
	}
}
