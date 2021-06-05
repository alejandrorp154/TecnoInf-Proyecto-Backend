package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Override
	public DTOEvento crearEvento(DTOEvento dtoEvento) {
		try {
			Evento evento = new Evento(dtoEvento);
			manager.persist(evento);
			Usuario owner = manager.find(Usuario.class, dtoEvento.getIdPersona());
			owner.getEventos().add(evento);
			owner.getCreadorDeEventos().add(evento);
			evento.setUsuarioCrador(owner);
			manager.merge(owner);	
			dtoEvento.setIdEvento(evento.getIdEvento());
			//Falta Agregar logica de puntos
			return dtoEvento;
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public boolean eliminarEvento(int idEvento, String idPersona) {

		Evento event = manager.find(Evento.class, idEvento);

		if (event != null) {
			Usuario ownerEvent = event.getUsuarioCrador();

			if ( ownerEvent!= null && ownerEvent.getIdPersona().equals(idPersona)) {
				List<Publicacion> pubs = event.getPublicaciones();
				if (!pubs.isEmpty()) {
					for (Publicacion publicacion : pubs) {

						MongoDBConnector mongoConnector = new MongoDBConnector();
						MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");

						String docu = String.valueOf(publicacion.getIdPublicacion());
						collection.deleteOne(eq("idPublicacion", new ObjectId(docu)));

						//publicacion.getPerfil().quitarPublicacion(publicacion);
						publicacion.getPerfil().getPublicaciones().remove(publicacion);
						/**
						 *
						 * Me parece que esta operacion tiraría error
						 * por que no existiría la publicacion, ya se borró
						 * en la entidad PerfilUsuario
						 *
						 *	manager.remove(publicacion);
						 */
					}
				}
				/**
				 *		TALVEZ NO ES NECESARIO ELIMINAR EN TODOS ESTOS LUGARES?
				 *		SE BORRA CON CON EL ULTIMO manager.remove(event) en linea 82;
				 */
				ownerEvent.getCreadorDeEventos().remove(event);
				ownerEvent.getEventos().remove(event);

				manager.remove(event);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modificar(DTOEvento dtoEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removerUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dejar(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarChatEvento(int idEvento, int idChat) {
		// TODO Auto-generated method stub
		return false;
	}

}
