package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTODetalleEvento;
import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOEventoUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioEvento;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.EventoUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.DbManager;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {

	private PuntosUsuario puntoUsuario;
	
	public ControladorEventoDA()
	{
		puntoUsuario = new PuntosUsuario();
	}

	@Override
	public DTOEvento crearEvento(DTOEvento dtoEvento) {
		try {
			DbManager.open();
			Evento evento = new Evento(dtoEvento);
			DbManager.persist(evento);
			Usuario owner = DbManager.find(Usuario.class, dtoEvento.getIdPersona());
			EventoUsuario eventoUsuario = new EventoUsuario(owner.getIdPersona(), evento.getIdEvento(), estadosContactos.aceptada, owner.getIdPersona());
			DbManager.persist(eventoUsuario);
			evento.setUsuarioCreador(owner);
			DbManager.merge(owner);
			evento.setUsuarioCreador(owner);
			DbManager.merge(evento);
			dtoEvento.setIdEvento(evento.getIdEvento());
			dtoEvento.getUbicacion().setIdUbicacion(evento.getUbicacion().getIdUbicacion());
			dtoEvento.setIdPersona(owner.getIdPersona());
			DTOUsuario dtoUsuario = new DTOUsuario(owner);
			puntoUsuario.getPuntosUsuario("AltaEvento", dtoUsuario);
			return dtoEvento;
		} catch (Exception exception) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarEvento(int idEvento, String idPersona) {
		try {
			DbManager.open();
			Evento event = DbManager.find(Evento.class, idEvento);
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
					List<EventoUsuario> eventosUsuarios  = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + event.getIdEvento() + "'");
					//Dessasigna a Usuarios que asistiran al evento que se esta eliminando
					for (EventoUsuario eventoUsuario : eventosUsuarios) {
						DbManager.remove(eventoUsuario);
					}

					DbManager.remove(event);
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
			DbManager.open();
			Evento evento = DbManager.find(Evento.class, dtoEvento.getIdEvento());
			if (evento != null) {
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
				evento.setNombre(dtoEvento.getNombre());
				evento.setImagen(dtoEvento.getImagen());
				evento.setNombreImagen(dtoEvento.getNombreImagen());
				evento.setExtension(dtoEvento.getExtension());
				DbManager.merge(evento);
				dtoEventoRes = new DTOEvento(evento);
			}
		} catch (Exception exception) {
			return dtoEventoRes;
		}
		return dtoEventoRes;
	}

	@Override
	public boolean agregarUsuario(int idEvento, String idPersona, String idPersonaInvitador) {
		boolean res = false;
		try {
			DbManager.open();
			EventoUsuario eventoUsuario = new EventoUsuario(idPersona, idEvento, estadosContactos.pendiente, idPersonaInvitador);
			DbManager.persist(eventoUsuario);
			res = true;
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public boolean removerUsuario(int idEvento, String idPersona) {
		boolean res = false;
		try {
			DbManager.open();
			EventoUsuario eventoUsuario = DbManager.find(EventoUsuario.class, idPersona, idEvento);
			if (eventoUsuario != null) {
				DbManager.remove(eventoUsuario);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public boolean dejar(int idEvento, String idPersona) {
		boolean res = false;
		try {
			DbManager.open();
			EventoUsuario eventoUsuario = DbManager.find(EventoUsuario.class, idPersona, idEvento);
			if (eventoUsuario != null) {
				DbManager.remove(eventoUsuario);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEvento> obtenerEventos(String idPersona, int offset, int size) {
		List<DTOEvento> res = new ArrayList<>();
		try {
			DbManager.open();
			List<EventoUsuario> eventosUsuarios = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + idPersona + "' order by eventoUsuario.idEvento desc", offset, size);
			for(EventoUsuario eventoUsuario : eventosUsuarios) {
				Evento evento = DbManager.find(Evento.class, eventoUsuario.getIdEvento());
				DTOEvento dtoEvento = new DTOEvento(evento);
				boolean isOwner = evento.getUsuarioCreador().getIdPersona().equals(idPersona) ? true : false;
				dtoEvento.setOwner(isOwner);
				dtoEvento.setEstadoSolicitud(eventoUsuario.getEstadoContactos());
				res.add(dtoEvento);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOEvento> obtenerInvitacionesPendientes(String idPersona, int offset, int size) {
		List<DTOEvento> res = new ArrayList<>();
		try {
			DbManager.open();
			List<EventoUsuario> eventosUsuarios = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + idPersona + "' and eventoUsuario.estadoContactos = '" + estadosContactos.pendiente + "' order by eventoUsuario.idEvento desc", offset, size);
			for(EventoUsuario eventoUsuario : eventosUsuarios) {
				Evento evento = DbManager.find(Evento.class, eventoUsuario.getIdEvento());
				DTOEvento dtoEvento = new DTOEvento(evento);
				res.add(dtoEvento);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
	
	@Override
	public boolean responderIvitacion(DTOEventoUsuario dtoEventoUsuario) {
		boolean res = false;
		try {
			DbManager.open();
			EventoUsuario eventoUsuario = DbManager.find(EventoUsuario.class, dtoEventoUsuario.getIdPersona(), dtoEventoUsuario.getIdEvento());
			if (eventoUsuario != null) {
				eventoUsuario.setEstadoContactos(dtoEventoUsuario.getEstadoContactos());
				DbManager.merge(eventoUsuario);
				Usuario usuario = DbManager.find(Usuario.class, eventoUsuario.getIdPersonaInvitador());
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				puntoUsuario.getPuntosUsuario("InvitarUsuarioEvento", dtoUsuario);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DTODetalleEvento obtenerEventoById(int idEvento) {
		DTODetalleEvento dtoDetalleEvento = new DTODetalleEvento();
		try {
			DbManager.open();
			Evento evento = DbManager.find(Evento.class, idEvento);
			if (evento != null) {
				List<EventoUsuario> eventosUsuarios = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + idEvento + "'");
				List<DTOUsuarioEvento> dtoUsuariosEventos = new ArrayList<>();
				for(EventoUsuario eventoUsuario : eventosUsuarios) {
					Usuario usuario = DbManager.find(Usuario.class, eventoUsuario.getIdPersona());
					DTOUsuarioEvento dtoUsuarioEvento = new DTOUsuarioEvento(usuario, eventoUsuario.getEstadoContactos());
					boolean owner = evento.getUsuarioCreador().getIdPersona().equals(eventoUsuario.getIdPersona()) ? true : false;
					dtoUsuarioEvento.setOwner(owner);
					dtoUsuariosEventos.add(dtoUsuarioEvento);
				}
				dtoDetalleEvento = new DTODetalleEvento(evento, dtoUsuariosEventos);
			}
		} catch (Exception exception) {
			return dtoDetalleEvento;
		}
		return dtoDetalleEvento;
	}
}
