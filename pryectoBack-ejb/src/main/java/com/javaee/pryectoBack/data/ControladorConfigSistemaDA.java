package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.model.Configuracion;
import com.javaee.pryectoBack.model.Usuario;

@Singleton
public class ControladorConfigSistemaDA implements ControladorConfigSistemaDALocal, ControladorConfigSistemaDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public ControladorConfigSistemaDA()
	{
	}
	
	@Override
	public DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		DTOConfiguracion res = new DTOConfiguracion();
		try {
			Configuracion configuracion = manager.find(Configuracion.class, dtoConfiguracion.getIdConfiguracion());
			if (configuracion != null) {
				configuracion.setAltaContacto(dtoConfiguracion.isAltaContacto());
				configuracion.setAltaEvento(dtoConfiguracion.isAltaEvento());
				configuracion.setAltaPublicacion(dtoConfiguracion.isAltaPublicacion());
				configuracion.setReaccionPublicacion(dtoConfiguracion.isReaccionPublicacion());
				configuracion.setComentarPublicacion(dtoConfiguracion.isComentarPublicacion());
				configuracion.setInvitacionUsuario(dtoConfiguracion.isInvitacionUsuario());
				configuracion.setSalirEvento(dtoConfiguracion.isSalirEvento());
				configuracion.setRecuperarContrasenia(dtoConfiguracion.isRecuperarContrasenia());
				configuracion.setBloquearUsuario(dtoConfiguracion.isBloquearUsuario());
				configuracion.setDesbloquearUsuario(dtoConfiguracion.isDesbloquearUsuario());
				configuracion.setChatUsuario(dtoConfiguracion.isChatUsuario());
				configuracion.setBajaEvento(dtoConfiguracion.isBajaEvento());
				configuracion.setModificacionEvento(dtoConfiguracion.isModificacionEvento());
				manager.merge(configuracion);
				res = dtoConfiguracion;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public DTOConfiguracion getByIdPersona(String idPersona) {
		DTOConfiguracion res = new DTOConfiguracion();
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				Configuracion configuracion = usuario.getConfiguracion();
				res = new DTOConfiguracion(configuracion);
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

}
