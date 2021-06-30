package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOPublicacionPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Tipo;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.model.reacciones;
import com.javaee.pryectoBack.model.tipos;
import com.javaee.pryectoBack.util.DbManager;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

@Singleton
public class ControladorPublicacionComentarioDA
		implements ControladorPublicacionComentarioDALocal, ControladorPublicacionComentarioDARemote {

	private PuntosUsuario puntoUsuario;

	public ControladorPublicacionComentarioDA() {
		puntoUsuario = new PuntosUsuario();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOPublicacionPerfilUsuario> obtenerPublicaciones(String idPersona, int offset, int size) {
		List<DTOPublicacionPerfilUsuario> res = new ArrayList<>();
		try {
			DbManager.open();
			List<UsuarioContacto> usuariosContactos = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = '" + idPersona + "' and usuariocontacto.estadoContactos = '" + estadosContactos.aceptada + "' order by usuariocontacto.fechaContactos");
			for(UsuarioContacto usuarioContacto : usuariosContactos) {
				PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, usuarioContacto.getContactoIdPersona());
				if (perfil != null) {
					Usuario usuario = DbManager.find(Usuario.class, perfil.getIdPersona());
					for (Publicacion publicacion : perfil.getPublicaciones()) {
						if (!publicacion.getTipo().getTipo().equals(tipos.mapa)) {
							DTOPublicacionPerfilUsuario dtoPublicacion = new DTOPublicacionPerfilUsuario(publicacion, usuario);
							res.add(dtoPublicacion);
						}
					}
				}
			}
			PerfilUsuario perfilUsuarioLogueado = DbManager.find(PerfilUsuario.class, idPersona);
			if (perfilUsuarioLogueado != null) {
				Usuario usuarioLogueado = DbManager.find(Usuario.class, perfilUsuarioLogueado.getIdPersona());
				for (Publicacion publicacion : perfilUsuarioLogueado.getPublicaciones()) {
					if (!publicacion.getTipo().getTipo().equals(tipos.mapa)) {
						DTOPublicacionPerfilUsuario dtoPublicacion = new DTOPublicacionPerfilUsuario(publicacion, usuarioLogueado);
						res.add(dtoPublicacion);
					}
				}
			}
			res = aplicarOffsetSeizePublicaciones(res, offset, size);
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	private List<DTOPublicacionPerfilUsuario> aplicarOffsetSeizePublicaciones(List<DTOPublicacionPerfilUsuario> dtoPublicaciones, int offset, int size) {
		List<DTOPublicacionPerfilUsuario> res = new ArrayList<>();
		int offsetAux = 0;
		for (DTOPublicacionPerfilUsuario dtoMul : dtoPublicaciones) {
			if (res.size() == size) {
				break;
			}
			if (offsetAux >= offset) {
				res.add(dtoMul);
			}
			offsetAux ++;
		}
		return res;
	}

	@Override
	public DTOPublicacionPerfilUsuario obtenerPublicacion(int idPublicacion) {
		DTOPublicacionPerfilUsuario dtoPublicacion = new DTOPublicacionPerfilUsuario();
		try {
			DbManager.open();
			Publicacion publicacion = DbManager.find(Publicacion.class, idPublicacion);
			if (publicacion != null) {
				List<DTOComentario> dtoComentarios = getComentarios(publicacion.getIdPublicacion());
				Usuario usuario = DbManager.find(Usuario.class, publicacion.getPerfil().getIdPersona());
				dtoPublicacion = new DTOPublicacionPerfilUsuario(publicacion, usuario);
				dtoPublicacion.setComentarioReacciones(dtoComentarios);
				dtoPublicacion = getCantidadReaccionPublicacion(dtoPublicacion);
				dtoPublicacion.setCantidadComentarios(getCantidadComentarios(dtoPublicacion.getIdPublicacion()));
			}
		} catch (Exception exception) {
			return dtoPublicacion;
		}
		return dtoPublicacion;
	}

	@Override
	public boolean modificarPublicacion(DTOPublicacion dtoPublicacion) {
		DbManager.open();
		Publicacion publicacion = DbManager.find(Publicacion.class, dtoPublicacion.getIdPublicacion());
		if (publicacion != null) {
			publicacion.setContenido(dtoPublicacion.getContenido());
			publicacion.setExtension(dtoPublicacion.getExtension());
			publicacion.setFecha(dtoPublicacion.getFecha());
			publicacion.setNombre(dtoPublicacion.getNombre());
			DbManager.merge(publicacion);
		}
		return true;
	}

	@Override
	public boolean bajaPublicacion(int idPublicacion) {
		DbManager.open();
		Publicacion publicacion = DbManager.find(Publicacion.class, idPublicacion);
		if (publicacion != null) {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			collection.deleteMany(eq("idPublicacion", idPublicacion));
			collection = mongoConnector.getCollection("ReaccionesPublicacion");
			collection.deleteMany(eq("idPublicacion", idPublicacion));
			Tipo tipo = DbManager.find(Tipo.class, publicacion.getIdPublicacion());
			DbManager.remove(publicacion);
			DbManager.remove(tipo);
		}
		return true;
	}

	@Override
	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ReaccionesPublicacion");
			Document reaccionPublicacion = dtoReaccion.getDocumentPublicacion();
			Document reaccion = collection.find(and(eq("idPublicacion", dtoReaccion.getIdPublicacion()), eq("idPersona", dtoReaccion.getIdPersona()))).first();
			if (reaccion != null) {
				collection.updateOne(and(eq("idPublicacion", dtoReaccion.getIdPublicacion()), eq("idPersona", dtoReaccion.getIdPersona())),
						Updates.set("reaccion", String.valueOf(dtoReaccion.getReaccion())));
			} else {
				collection.insertOne(reaccionPublicacion);
				DTOUsuario dtoUsuario = new DTOUsuario();
				dtoUsuario.setIdPersona(dtoReaccion.getIdPersona());
				puntoUsuario.getPuntosUsuario("ReaccionarPublicacion", dtoUsuario);
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
			Document reaccion = collection.find(and(eq("idComentario", dtoReaccion.getIdComentario()), eq("idPersona", dtoReaccion.getIdPersona()))).first();
			if (reaccion != null) {
				collection.updateOne(and(eq("idComentario", dtoReaccion.getIdComentario()), eq("idPersona", dtoReaccion.getIdPersona())),
						Updates.set("reaccion", String.valueOf(dtoReaccion.getReaccion())));
			} else {
				collection.insertOne(reaccionComentario);
				DTOUsuario dtoUsuario = new DTOUsuario();
				dtoUsuario.setIdPersona(dtoReaccion.getIdPersona());
				puntoUsuario.getPuntosUsuario("ReaccionarComentario", dtoUsuario);
			}
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public DTOPublicacion altaPublicacion(DTOPublicacion dtoPublicacion) {
		try {
			DbManager.open();
			DTOPublicacion dtoPubli = new DTOPublicacion();
			Publicacion publicacion = new Publicacion(dtoPublicacion);
			Usuario usuario = DbManager.find(Usuario.class, dtoPublicacion.getPerfil().getUsuario().getIdPersona());
			if (usuario != null) {
				publicacion.getPerfil().setUsuario(usuario);
				DbManager.persist(publicacion);
				Tipo tipo = new Tipo();
				tipo.setTipo(publicacion.getTipo().getTipo());
				tipo.setIdPublicacion(publicacion.getIdPublicacion());
				publicacion.setTipo(tipo);
				DbManager.merge(tipo);
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				puntoUsuario.getPuntosUsuario("AltaPublicacion", dtoUsuario);
				dtoPubli = new DTOPublicacion(publicacion);
			}
			return dtoPubli;
		} catch (Exception exception) {
			return new DTOPublicacion();
		}
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
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
			puntoUsuario.getPuntosUsuario("ComentarPublicacion", dtoUsuario);
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
			collection.updateOne(eq("_id", new ObjectId(dtoComentario.getIdComentario())),
					Updates.set("contenido", dtoComentario.getContenido()));
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

	@SuppressWarnings("unused")
	private Integer getCantidadReaccion(reacciones reaccion, Document comentario,
			MongoCollection<Document> collection) {
		Integer cantidad = 0;
		FindIterable<Document> reacciones = collection.find(and(
				eq("idComentario", String.valueOf(comentario.get("_id"))), eq("reaccion", String.valueOf(reaccion))));
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
	
	private DTOPublicacionPerfilUsuario getCantidadReaccionPublicacion(DTOPublicacionPerfilUsuario dtoPublicacion)
	{
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collection = mongoConnector.getCollection("ReaccionesPublicacion");
			dtoPublicacion.setCantidadDislikes(getCantidadReaccionPublicacion(reacciones.NoMeGusta, dtoPublicacion, collection));
			dtoPublicacion.setCantidadLikes(getCantidadReaccionPublicacion(reacciones.MeGusta, dtoPublicacion, collection));
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		return dtoPublicacion;
	}
	
	@SuppressWarnings("unused")
	private Integer getCantidadReaccionPublicacion(reacciones reaccion, DTOPublicacionPerfilUsuario publicacion,
			MongoCollection<Document> collection) {
		Integer cantidad = 0;
		FindIterable<Document> reacciones = collection.find(and(
				eq("idPublicacion", publicacion.getIdPublicacion()), eq("reaccion", String.valueOf(reaccion))));
		for (Document document : reacciones) {
			cantidad++;
		}
		return cantidad;
	}

	public Integer getCantidadComentarios(int idPublicacion) {
		Integer cantidad = 0;
		try {
			MongoDBConnector mongoConnector = new MongoDBConnector();
			MongoCollection<Document> collectionComentariosPublicacion = mongoConnector
					.getCollection("ComentariosPublicacion");
			MongoCollection<Document> reaccionesComentarios = mongoConnector.getCollection("ReaccionesComentario");
			FindIterable<Document> comentariosPadre = collectionComentariosPublicacion
					.find(and(eq("idPublicacion", idPublicacion), eq("idComentarioPadre", null)));
			for (Document comentario : comentariosPadre) {
				cantidad = cantidad + getCantidadArbolComentario(comentario, collectionComentariosPublicacion, reaccionesComentarios);
			}
			return cantidad;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return null;

		}
	}

	@SuppressWarnings("unused")
	private Integer getCantidadArbolComentario(Document comentarioPadre,
			MongoCollection<Document> collectionComentariosPublicacion,
			MongoCollection<Document> reaccionesComentarios) {
		Integer cantidad = 1;
		DTOComentario comentarioPadreDTO = new DTOComentario(comentarioPadre);
		FindIterable<Document> comentariosHijo = collectionComentariosPublicacion
				.find(eq("idComentarioPadre", comentarioPadreDTO.getIdComentario()));
		if (comentariosHijo != null) {
			for (Document comentario : comentariosHijo) {
				cantidad++;
			}
		}
		return cantidad;
	}
}
