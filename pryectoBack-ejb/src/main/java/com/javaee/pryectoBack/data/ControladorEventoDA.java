package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.EventoUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	private PuntosUsuario puntoUsuario;
	
	public ControladorEventoDA()
	{
		puntoUsuario = new PuntosUsuario();
	}

	@Override
	public DTOEvento crearEvento(DTOEvento dtoEvento) {
		try {
			Evento evento = new Evento(dtoEvento);
			manager.persist(evento);
			Usuario owner = manager.find(Usuario.class, dtoEvento.getIdPersona());
			EventoUsuario eventoUsuario = new EventoUsuario(owner.getIdPersona(), evento.getIdEvento(), estadosContactos.aceptada);
			manager.persist(eventoUsuario);
			evento.setUsuarioCreador(owner);
			manager.merge(owner);
			evento.setUsuarioCreador(owner);
			manager.merge(evento);
			dtoEvento.setIdEvento(evento.getIdEvento());
			dtoEvento.getUbicacion().setIdUbicacion(evento.getUbicacion().getIdUbicacion());
			dtoEvento.setIdPersona(owner.getIdPersona());
			DTOUsuario dtoUsuario = new DTOUsuario(owner);
			puntoUsuario.getPuntosUsuario("AltaEvento", dtoUsuario, manager);
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
					TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + event.getIdEvento() + "'", EventoUsuario.class);
					List<EventoUsuario> eventosUsuarios = query.getResultList();
					//Dessasigna a Usuarios que asistiran al evento que se esta eliminando
					for (EventoUsuario eventoUsuario : eventosUsuarios) {
						manager.remove(eventoUsuario);
					}

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
			if (evento != null) {
				if (evento.getUbicacion() != null && evento.getUbicacion().getIdUbicacion() != 0 
						&& dtoEvento.getUbicacion() != null && dtoEvento.getUbicacion().getIdUbicacion() != 0) {
					Ubicacion ubicacion = manager.find(Ubicacion.class, dtoEvento.getUbicacion().getIdUbicacion());
					manager.remove(ubicacion);
				}
				evento.getUbicacion().setDescripcion(dtoEvento.getUbicacion().getDescripcion());
				evento.getUbicacion().setFecha(dtoEvento.getUbicacion().getFecha());
				evento.getUbicacion().setPais(dtoEvento.getUbicacion().getPais());
				evento.getUbicacion().setLatitud(dtoEvento.getUbicacion().getLatitud());
				evento.getUbicacion().setLongitud(dtoEvento.getUbicacion().getLongitud());
				evento.setUbicacion(new Ubicacion(dtoEvento.getUbicacion()));
				evento.setDescripcion(dtoEvento.getDescripcion());
				evento.setFechaInicio(dtoEvento.getFechaInicio());
				evento.setFechaFin(dtoEvento.getFechaFin());
				evento.setEstado(dtoEvento.getEstado());
				evento.getUbicacion().setEvento(evento);
				manager.merge(evento);
				dtoEventoRes = new DTOEvento(evento);
			}
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
			TypedQuery<Evento> query = manager.createQuery("SELECT evento FROM Evento evento order by evento.idEvento", Evento.class);
			List<Evento> eventos = query.setFirstResult(offset).setMaxResults(size).getResultList();
			for(Evento evento : eventos) {
				if (evento.getUsuarioCreador().getIdPersona().equals(idPersona)) {
					DTOEvento dtoEvento = new DTOEvento(evento);
					res.add(dtoEvento);
				}
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

}
