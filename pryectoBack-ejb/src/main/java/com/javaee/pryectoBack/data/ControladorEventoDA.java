package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

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
			evento.setUsuarioCreador(owner);
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
		try {
			Evento event = manager.find(Evento.class, idEvento);
			if (event != null) {
				Usuario ownerEvent = event.getUsuarioCreador();

				if ( ownerEvent != null && ownerEvent.getIdPersona().equals(idPersona)) {
					List<Publicacion> pubs = event.getPublicaciones();
					if (!pubs.isEmpty()) {
						for (Publicacion publicacion : pubs) {

							MongoDBConnector mongoConnector = new MongoDBConnector();
							MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");

							String docu = String.valueOf(publicacion.getIdPublicacion());
							collection.deleteOne(eq("idPublicacion", new ObjectId(docu)));

							//publicacion.getPerfil().quitarPublicacion(publicacion);
							publicacion.getPerfil().getPublicaciones().remove(publicacion);
						}
					}
					ownerEvent.getCreadorDeEventos().remove(event);
					ownerEvent.getEventos().remove(event);

					manager.remove(event);
					return true;
				}
			}
		} catch (Exception exception) {
			return false;
		}
		return false;
	}

	@Override
	public DTOEvento modificar(DTOEvento dtoEvento) {
		DTOEvento dtoEventoRes = new DTOEvento();
		try {
			Evento evento = manager.find(Evento.class, dtoEvento.getIdEvento());
			evento.setUbicacion(new Ubicacion(dtoEvento.getUbicacion()));
			evento.setDescripcion(dtoEvento.getDescripcion());
			evento.setFechaInicio(dtoEvento.getFechaInicio());
			evento.setFechaFin(dtoEvento.getFechaFin());
			evento.setEstado(dtoEvento.getEstado());
			manager.merge(evento);
			dtoEventoRes = new DTOEvento(evento);
		} catch (Exception exception) {
			return dtoEventoRes;
		}
		return dtoEventoRes;
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

	@Override
	public List<DTOEvento> obtenerEventos(String idPersona, int offset, int size) {
		List<DTOEvento> res = new ArrayList<>();
		try {
			// luego de agregar la logica de invitar a evento y aceptar invitacion hay que cambiar esta logica un poco
			TypedQuery<Evento> query = manager.createQuery("SELECT evento FROM Evento evento where evento.idPersona = :idPersona order by evento.idEvento", Evento.class);
			List<Evento> eventos = query.setParameter("idPersona", idPersona).setFirstResult(offset).setMaxResults(size).getResultList();
			for(Evento evento : eventos) {
				DTOEvento dtoEvento = new DTOEvento(evento);
				res.add(dtoEvento);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

}
