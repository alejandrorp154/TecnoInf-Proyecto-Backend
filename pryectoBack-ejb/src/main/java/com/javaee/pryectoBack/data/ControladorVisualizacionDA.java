package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOEstadistica;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.Multimedia;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.DbManager;

@Singleton
public class ControladorVisualizacionDA implements ControladorVisualizacionDALocal, ControladorVisualizacionDARemote {

	public ControladorVisualizacionDA()
	{
	}
	
	@Override
	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		List<DTOMultimedia> dtosMultimedia = new ArrayList<>();
		try {
			DbManager.open();
			PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, idPersona);
			List<Multimedia> multimedias = perfil.getGalerias();
			for(Multimedia multimedia : multimedias) {
				DTOMultimedia dtoMultimedia = new DTOMultimedia(multimedia);
				dtosMultimedia.add(dtoMultimedia);
			}
			dtosMultimedia = aplicarOffsetSeizeMultimedia(dtosMultimedia, offset, size);
		} catch (Exception exception) {
			return dtosMultimedia;
		}
		return dtosMultimedia;
	}

	private List<DTOMultimedia> aplicarOffsetSeizeMultimedia(List<DTOMultimedia> dtoMultimedia, int offset, int size) {
		List<DTOMultimedia> res = new ArrayList<>();
		int offsetAux = 0;
		for (DTOMultimedia dtoMul : dtoMultimedia) {
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
	public DTOPerfilUsuario visualizarPerfil(String idPersona) {
		DTOPerfilUsuario dtoPerfil = new DTOPerfilUsuario();
		DbManager.open();
		Persona usuario = DbManager.find(Usuario.class, idPersona);
		if (usuario != null) {
			PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, idPersona);
			if (perfil != null)
			{
				dtoPerfil = new DTOPerfilUsuario(perfil);
			}
		}
		return dtoPerfil;
	}

	@Override
	public List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size) {
		List<DTOUsuario> dtoUsuario = new ArrayList<>();
		try {
			List<DTOUsuario> dtoUsuario1 = this.buscarAmigosDeAmigos(idPersona);
			for (DTOUsuario dtoUsu : dtoUsuario1) {
				dtoUsuario.add(dtoUsu);
			}

			List<DTOUsuario> dtoUsuario2 = this.buscarAmigosSegunIntereses(dtoUsuario, idPersona);
			for (DTOUsuario dtoUsu : dtoUsuario2) {
				dtoUsuario.add(dtoUsu);
			}

			
			List<DTOUsuario> dtoUsuario3 = this.buscarAmigosSegunUbicacion(dtoUsuario, idPersona);
			for (DTOUsuario dtoUsu : dtoUsuario3) {
				dtoUsuario.add(dtoUsu);
			}
			
			List<DTOUsuario> dtoUsuario4 = this.buscarAmigosSegunPais(dtoUsuario, idPersona);
			for (DTOUsuario dtoUsu : dtoUsuario4) {
				dtoUsuario.add(dtoUsu);
			}
			List<DTOUsuario> resDtoUsuarios = aplicarOffsetSeize(dtoUsuario, offset, size);
			return resDtoUsuarios;
		} catch (Exception exception) {
			return dtoUsuario;
		}
	}

	private List<DTOUsuario> aplicarOffsetSeize(List<DTOUsuario> dtoUsuario, int offset, int size) {
		List<DTOUsuario> res = new ArrayList<>();
		int offsetAux = 0;
		for (DTOUsuario dtoUsu : dtoUsuario) {
			if (res.size() == size) {
				break;
			}
			if (offsetAux >= offset) {
				res.add(dtoUsu);
			}
			offsetAux ++;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		List<DTOUsuario> dtoUsuarios = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			List<UsuarioContacto> usuariosContactos = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = '" + idPersona + "' and usuariocontacto.estadoContactos = '" + estadosContactos.aceptada + "' order by usuariocontacto.fechaContactos", offset, size);
			for(UsuarioContacto usuarioContacto : usuariosContactos) {
				Usuario usuario = DbManager.find(Usuario.class, usuarioContacto.getContactoIdPersona());
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				dtoUsuarios.add(dtoUsuario);
			}
		} catch (Exception exception) {
			return dtoUsuarios;
		}
		return dtoUsuarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> obtenerUsuarios(int offset, int size) {
		List<DTOUsuario> dtoUsuarios = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			List<Usuario> usuarios = DbManager.createQuery("SELECT usuario FROM Usuario usuario order by usuario.idPersona", offset, size);
			for(Usuario usuario : usuarios) {
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				dtoUsuarios.add(dtoUsuario);
			}
		} catch (Exception exception) {
			return dtoUsuarios;
		}
		return dtoUsuarios;
	}

	@Override
	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		DTOUsuarioMedalla res = new DTOUsuarioMedalla();
		try {
			DbManager.open();
			Usuario usuario = DbManager.find(Usuario.class, idPersona);
			if (usuario != null) {
				res = new DTOUsuarioMedalla(usuario);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> buscarAmigosSegunUbicacion(List<DTOUsuario> dtoUsuarios, String idPersona) {
		List<DTOUsuario> res = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			// Obtengo todas las ubicaciones del usuario logeuado
			List<Ubicacion> ubicacionesUsuarioLogueado = DbManager.createQuery("SELECT ubicacion FROM Ubicacion ubicacion where usuario_idpersona = :idPersona", "idPersona", idPersona);
			// Obtengo todas las ubicaciones de todos los usuarios menos el usuario logeuado
			List<Ubicacion> ubicacionesUsuarioMenosLogueado = DbManager.createQuery("SELECT ubicacion FROM Ubicacion ubicacion where usuario_idpersona != :idPersona", "idPersona", idPersona);
			// Obtengo todos los amigos del usuario logueado para luego verificar que las posibleas sugerencias no sea un amigo
			List<UsuarioContacto> usuariosContactosTotal = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona", "idPersona", idPersona);
			for (Ubicacion ubicacionUsuLoegueado : ubicacionesUsuarioLogueado) {
				for (Ubicacion ubicacionUsuarioMenosLogueado : ubicacionesUsuarioMenosLogueado) {
					if (ubicacionUsuLoegueado.getDistancia(ubicacionUsuLoegueado, ubicacionUsuarioMenosLogueado) < 100) {
						if (!usuariosContactosTotal.stream().anyMatch(o -> o.getContactoIdPersona().equals(ubicacionUsuarioMenosLogueado.getUsuario().getIdPersona()))) {
							// verifico que la sugerencia de amigo de se encuentra ya en la lista de sugerencia de amigos
							if (!dtoUsuarios.stream().anyMatch(o -> o.getIdPersona().equals(ubicacionUsuarioMenosLogueado.getUsuario().getIdPersona()))) {
								Usuario usuarioSugerencia = DbManager.find(Usuario.class, ubicacionUsuarioMenosLogueado.getUsuario().getIdPersona());
								DTOUsuario dtoSugerencia = new DTOUsuario(usuarioSugerencia);
								res.add(dtoSugerencia);
							}
						}
					}
				}
			}
			
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> buscarAmigosDeAmigos(String idPersona) {
		List<DTOUsuario> res = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			// Obtengo los ultimos 5 amigos del usuario logueado
			List<UsuarioContacto> usuariosContactos = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona order by usuariocontacto.fechaContactos desc", "idPersona", idPersona, 5);
			// Obtengo todos los amigos del usuario logueado para luego verificar que las posibleas sugerencias no sea un amigo 
			List<UsuarioContacto> usuariosContactosTotal = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona", "idPersona", idPersona);
			for (UsuarioContacto usuContacto : usuariosContactos) {
				List<UsuarioContacto> usuariosContactosDeAmigo = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona order by usuariocontacto.fechaContactos desc", "idPersona", usuContacto.getContactoIdPersona());
				int count = 0;
				// recorro la lista de amigos de un amigo de un usuario logueado
				for (UsuarioContacto usuContactoDeAmigo : usuariosContactosDeAmigo) {
					boolean encontro = false;
					// recorro la lista de amigos del usuario logueado para asegurarnos que el amigo sugerido de su amigo no sea ya un amigo
					for (UsuarioContacto usuContactosTotal : usuariosContactosTotal) {
						if (usuContactosTotal.getContactoIdPersona().equals(usuContactoDeAmigo.getContactoIdPersona())) {
							encontro = true;
							break;
						}
						else if ((usuContactosTotal.getIdPersona().equals(usuContactoDeAmigo.getContactoIdPersona())) ) {
							encontro = true;
							break;
						}
					}
					if (!encontro) {
						Usuario usuarioSugerencia = DbManager.find(Usuario.class, usuContactoDeAmigo.getContactoIdPersona());
						DTOUsuario dtoUsuario = new DTOUsuario(usuarioSugerencia);
						if (!res.stream().anyMatch(o -> o.getIdPersona().equals(dtoUsuario.getIdPersona()))) {
							res.add(dtoUsuario);
							count ++;
						}
					}
					if (count == 5) {
						break;
					}
				}
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> buscarAmigosSegunIntereses(List<DTOUsuario> dtoUsuarios, String idPersona) {
		List<DTOUsuario> res = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			List<Interes> intereses = DbManager.createQuery("SELECT interes FROM Interes interes");
			for (Interes interes : intereses) {
				List<PerfilUsuario> perfiles = interes.getPerfiles();
				// si entra en el if, tengo una lista de todos los perfiles de usuarios que tienen el mismo interes que el usuario logueado
				if (perfiles.stream().anyMatch(o -> o.getIdPersona().equals(idPersona))) {
					// Obtengo todos los amigos del usuario logueado para luego verificar que las posibleas sugerencias no sea un amigo
					List<UsuarioContacto> usuariosContactosTotal = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona", "idPersona", idPersona);
					for (PerfilUsuario perfil : perfiles) {
						if (!usuariosContactosTotal.stream().anyMatch(o -> o.getContactoIdPersona().equals(perfil.getIdPersona())) && !perfil.getIdPersona().equals(idPersona)) {
							// verifico que la sugerencia de amigo de se encuentra ya en la lista de sugerencia de amigos
							if (!dtoUsuarios.stream().anyMatch(o -> o.getIdPersona().equals(perfil.getIdPersona()))) {
								Usuario usuarioSugerencia = DbManager.find(Usuario.class, perfil.getIdPersona());
								DTOUsuario dtoSugerencia = new DTOUsuario(usuarioSugerencia);
								res.add(dtoSugerencia);
							}
						}
					}
				}
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private List<DTOUsuario> buscarAmigosSegunPais(List<DTOUsuario> dtoUsuarios, String idPersona) {
		List<DTOUsuario> res = new ArrayList<DTOUsuario>();
		try {
			DbManager.open();
			Usuario usuarioLogueado = DbManager.find(Usuario.class, idPersona);
			if (usuarioLogueado != null && usuarioLogueado.getPais() != null) {
				// Obtengo todos los usuarios del mismo pais que el usuario logueado
				List<Usuario> usuarios = DbManager.createQuery("SELECT usuario FROM Usuario usuario where usuario.pais = '" + usuarioLogueado.getPais() + "'");
				for (Usuario usuario : usuarios) {
					// Obtengo todos los amigos del usuario logueado para luego verificar que las posibleas sugerencias no sea un amigo
					List<UsuarioContacto> usuariosContactosTotal = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona", "idPersona", idPersona);
					if (!usuariosContactosTotal.stream().anyMatch(o -> o.getContactoIdPersona().equals(usuario.getIdPersona())) && !usuario.getIdPersona().equals(idPersona)) {
						// verifico que la sugerencia de amigo de se encuentra ya en la lista de sugerencia de amigos
						if (!dtoUsuarios.stream().anyMatch(o -> o.getIdPersona().equals(usuario.getIdPersona()))) {
							DTOUsuario dtoSugerencia = new DTOUsuario(usuario);
							res.add(dtoSugerencia);
						}
					}
				}
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<DTOEstadistica> seleccionarTipoEstadistica(String tipoEstadistica) {
		DbManager.open();
		List<Usuario> usuarios = null;
		List<DTOEstadistica> estadisticas = new ArrayList<DTOEstadistica>();
		DTOEstadistica estadistica = null;
		switch (tipoEstadistica) {
		case "CantidadUsuariosTotal":
			Long count = DbManager.createQueryCount("SELECT Count(*) FROM Persona WHERE establoqueado = false");
			estadistica = new DTOEstadistica();
			estadistica.setCantidadUsuariosRegistrados(count);
			estadisticas.add(estadistica);
			return estadisticas;
		case "UsuariosPorMedalla":
			usuarios = DbManager.createQuery("SELECT u FROM Usuario u");
			for (Usuario usuario : usuarios) {
				DTOEstadistica dtoEstadistica = new DTOEstadistica();
				dtoEstadistica.setNombreUsuario(usuario.getNickname());
				dtoEstadistica.setCantidadPuntos(usuario.getMedalla().getCantidadPuntos());
				dtoEstadistica.setNombreMedalla(String.valueOf(usuario.getMedalla().getRango()));				
				estadisticas.add(dtoEstadistica);
			}
			return estadisticas;
		case "CantidadVisitasPorUsuario":
			usuarios = DbManager.createQuery("SELECT u FROM Usuario u");
			for (Usuario usuario : usuarios) {
				List<Ubicacion> ubicaciones = usuario.getUbicaciones();
				Map<String, List<Ubicacion>> ubicacionPorPais = new HashMap<String, List<Ubicacion>>();
				for (Ubicacion ubicacion : ubicaciones) {
					if (ubicacionPorPais.get(ubicacion.getPais()) == null) {
						List<Ubicacion> listadoUbicaciones = new ArrayList<Ubicacion>();
						listadoUbicaciones.add(ubicacion);
						ubicacionPorPais.put(ubicacion.getPais(), listadoUbicaciones);
					} else {
						ubicacionPorPais.get(ubicacion.getPais()).add(ubicacion);
					}
				}
				estadistica = new DTOEstadistica();
				estadistica.setCantidadVisitas(ubicacionPorPais.keySet().size());
				estadistica.setNombreUsuario(usuario.getNickname());
				estadisticas.add(estadistica);
			}
			return estadisticas;
		case "CantidadUsuariosPorPais":
			usuarios = DbManager.createQuery("SELECT u FROM Usuario u" );
			Map<String, List<Usuario>> usuariosPorPais = new HashMap<String, List<Usuario>>();
			for (Usuario usuario : usuarios) {
				if (usuariosPorPais.get(usuario.getPais()) == null) {
					List<Usuario> usuariosPais = new ArrayList<Usuario>();
					usuariosPais.add(usuario);
					usuariosPorPais.put(usuario.getPais(), usuariosPais);
				} else {
					usuariosPorPais.get(usuario.getPais()).add(usuario);
				}
			}
			for (String pais : usuariosPorPais.keySet()) {
				estadistica = new DTOEstadistica(Long.valueOf(usuariosPorPais.get(pais).size()), pais);
				estadisticas.add(estadistica);
			}
			return estadisticas;
		default:
			return null;
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOUsuario> obtenerSolicitudesPendientes(String idPersona, int offset, int size) {
		List<DTOUsuario> res = new ArrayList<>();
		try {
			DbManager.open();
			List<UsuarioContacto> usuariosContactos = DbManager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.contactoIdPersona = '" + idPersona + "' and usuariocontacto.estadoContactos = '" + estadosContactos.pendiente + "' order by usuariocontacto.fechaContactos desc", offset, size);
			for(UsuarioContacto usuarioContacto : usuariosContactos) {
				Usuario usuario = DbManager.find(Usuario.class, usuarioContacto.getIdPersona());
				DTOUsuario dtoUsuario = new DTOUsuario(usuario);
				res.add(dtoUsuario);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
}
