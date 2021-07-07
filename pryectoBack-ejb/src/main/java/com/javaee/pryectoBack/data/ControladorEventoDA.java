package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.datatypes.DTODetalleEvento;
import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOEventoUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioEvento;
import com.javaee.pryectoBack.model.Configuracion;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.EventoUsuario;
import com.javaee.pryectoBack.model.EventoUsuarioId;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.EnviarNotificacion;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.javaee.pryectoBack.util.PuntosUsuario;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@Stateless
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	private PuntosUsuario puntoUsuario;
	private EnviarNotificacion enviarNotificacion;
	
	public ControladorEventoDA()
	{
		puntoUsuario = new PuntosUsuario();
		enviarNotificacion = new EnviarNotificacion();
	}

	@Override
	public DTOEvento crearEvento(DTOEvento dtoEvento) {
		try {
			Evento evento = new Evento(dtoEvento);
			manager.persist(evento);
			Usuario owner = manager.find(Usuario.class, dtoEvento.getIdPersona());
			EventoUsuario eventoUsuario = new EventoUsuario(owner.getIdPersona(), evento.getIdEvento(), estadosContactos.aceptada, owner.getIdPersona());
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
				manager.merge(evento);
				dtoEventoRes = new DTOEvento(evento);
				notificarModificacionEvento(evento.getIdEvento());
			}
		} catch (Exception exception) {
			return dtoEventoRes;
		}
		return dtoEventoRes;
	}

	private void notificarModificacionEvento(int idEvento) {
		TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + idEvento + "'", EventoUsuario.class);
		List<EventoUsuario> eventosUsuarios = query.getResultList();
		for (EventoUsuario eventoUsuario : eventosUsuarios) {
			enviarNotificacionEvento(idEvento, eventoUsuario.getIdPersona(), eventoUsuario.getIdPersonaInvitador(), "Se ha modificado el evento ", "Ha habido modificaciones en un evento", "modificacionEvento");
		}
	}

	@Override
	public boolean agregarUsuario(int idEvento, String idPersona, String idPersonaInvitador) {
		boolean res = false;
		try {
			EventoUsuario eventoUsuario = new EventoUsuario(idPersona, idEvento, estadosContactos.pendiente, idPersonaInvitador);
			manager.persist(eventoUsuario);
			res = true;
			enviarNotificacionEvento(idEvento, idPersona, idPersonaInvitador, "Ha sido invitado al evento ", "Ha recibido una invitacion evento", "agregarUsuario");
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	private void enviarNotificacionEvento(int idEvento, String idPersona, String idPersonaInvitador, String mensajeInicio, String titulo, String tipoNotificacion) {
		List<DTOConfiguracion> dtoConfiguraciones = getByIdPersona(idPersona);
		String nicknamePersonaInvitador = getNicknameById(idPersonaInvitador);
		String emailPersonaInvitada = getEmailById(idPersona);
		String nombreEvento = getNombreEvento(idEvento);
		String mensaje = mensajeInicio + nombreEvento + " por el usuario " + nicknamePersonaInvitador + ".";
		for (DTOConfiguracion dtoConfig : dtoConfiguraciones) {
			switch (tipoNotificacion) {
			case "agregarUsuario":
				if (dtoConfig.isInvitacionUsuario()) {
					enviar(dtoConfig, mensaje, titulo, emailPersonaInvitada, idPersona);
				}
			case "modificacionEvento":
				if (dtoConfig.isModificacionEvento()) {
					enviar(dtoConfig, mensaje, titulo, emailPersonaInvitada, idPersona);
				}
			case "removerUsuario":
				if (dtoConfig.isAltaContacto()) {
					enviar(dtoConfig, mensaje, titulo, emailPersonaInvitada, idPersona);
				}
			default:
				continue;
		}
		}
	}
	
	private void enviar(DTOConfiguracion dtoConfig, String mensaje, String titulo, String emailPersonaInvitada, String idPersona) {
		if (dtoConfig.isEmailNotification()) {
			enviarNotificacion.enviarEmailNotificacion(mensaje, emailPersonaInvitada, titulo);
		} else {
			enviarNotificacion.enviarPushNotificacion(mensaje, idPersona, titulo);
		}
	}

	private String getNombreEvento(int idEvento) {
		String nombre = null;
		try {
			Evento evento = manager.find(Evento.class, idEvento);
			if (evento != null) {
				nombre = evento.getNombre();
			}
		} catch (Exception exception) {
			return nombre;
		}
		return nombre;
	}

	private String getNicknameById(String idPersona) {
		String nickname = null;
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				nickname = usuario.getNickname();
			}
		} catch (Exception exception) {
			return nickname;
		}
		return nickname;
	}

	private String getEmailById(String idPersona) {
		String email = null;
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				email = usuario.getEmail();
			}
		} catch (Exception exception) {
			return email;
		}
		return email;
	}

	public List<DTOConfiguracion> getByIdPersona(String idPersona) {
		List<DTOConfiguracion> res = new ArrayList<DTOConfiguracion>();
		try {
			TypedQuery<Configuracion> query = manager.createQuery("SELECT configuracion FROM Configuracion configuracion where configuracion.idPersona = '" + idPersona + "'", Configuracion.class);
			List<Configuracion> configuraciones =  query.getResultList();
			if (configuraciones.size() > 0) {
				for (Configuracion configuracion : configuraciones) {
					DTOConfiguracion dtoConfiguracion = new DTOConfiguracion(configuracion);
					res.add(dtoConfiguracion);
				}	
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	
	@Override
	public boolean removerUsuario(int idEvento, String idPersona) {
		boolean res = false;
		try {
			EventoUsuario eventoUsuario = manager.find(EventoUsuario.class, new EventoUsuarioId(idPersona, idEvento));
			if (eventoUsuario != null) {
				String idPersonaInvitador = eventoUsuario.getIdPersonaInvitador();
				manager.remove(eventoUsuario);
				res = true;
				enviarNotificacionEvento(idEvento, idPersona, idPersonaInvitador, "Ha sido removido del evento ", "Ha sido removido de un evento", "removerUsuario");
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
			EventoUsuario eventoUsuario = manager.find(EventoUsuario.class, new EventoUsuarioId(idPersona, idEvento));
			if (eventoUsuario != null) {
				manager.remove(eventoUsuario);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public List<DTOEvento> obtenerEventos(String idPersona, int offset, int size) {
		List<DTOEvento> res = new ArrayList<>();
		try {
			TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + idPersona + "' order by eventoUsuario.idEvento desc", EventoUsuario.class);
			List<EventoUsuario> eventosUsuarios = query.setFirstResult(offset).setMaxResults(size).getResultList();
			for(EventoUsuario eventoUsuario : eventosUsuarios) {
				Evento evento = manager.find(Evento.class, eventoUsuario.getIdEvento());
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

	@Override
	public List<DTOEvento> obtenerInvitacionesPendientes(String idPersona, int offset, int size) {
		List<DTOEvento> res = new ArrayList<>();
		try {
			TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + idPersona + "' and eventoUsuario.estadoContactos = '" + estadosContactos.pendiente + "' order by eventoUsuario.idEvento desc", EventoUsuario.class);
			List<EventoUsuario> eventosUsuarios = query.setFirstResult(offset).setMaxResults(size).getResultList();
			for(EventoUsuario eventoUsuario : eventosUsuarios) {
				Evento evento = manager.find(Evento.class, eventoUsuario.getIdEvento());
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
			EventoUsuario eventoUsuario = manager.find(EventoUsuario.class, new EventoUsuarioId(dtoEventoUsuario.getIdPersona(), dtoEventoUsuario.getIdEvento()));
			if (eventoUsuario != null) {
				eventoUsuario.setEstadoContactos(dtoEventoUsuario.getEstadoContactos());
				manager.merge(eventoUsuario);
				Usuario usuario = manager.find(Usuario.class, eventoUsuario.getIdPersonaInvitador());
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				puntoUsuario.getPuntosUsuario("InvitarUsuarioEvento", dtoUsuario, manager);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public DTODetalleEvento obtenerEventoById(int idEvento) {
		DTODetalleEvento dtoDetalleEvento = new DTODetalleEvento();
		try {
			Evento evento = manager.find(Evento.class, idEvento);
			if (evento != null) {
				TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + idEvento + "'", EventoUsuario.class);
				List<EventoUsuario> eventosUsuarios = query.getResultList();
				List<DTOUsuarioEvento> dtoUsuariosEventos = new ArrayList<>();
				for(EventoUsuario eventoUsuario : eventosUsuarios) {
					Usuario usuario = manager.find(Usuario.class, eventoUsuario.getIdPersona());
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
