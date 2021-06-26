package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.model.Configuracion;
import com.javaee.pryectoBack.util.DbManager;

@Singleton
public class ControladorConfigSistemaDA implements ControladorConfigSistemaDALocal, ControladorConfigSistemaDARemote {

	public ControladorConfigSistemaDA()
	{
	}
	
	@Override
	public DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		DTOConfiguracion res = new DTOConfiguracion();
		try {
			DbManager.open();
			Configuracion configuracion = DbManager.find(Configuracion.class, dtoConfiguracion.getIdConfiguracion());
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
				DbManager.merge(configuracion);
				res = dtoConfiguracion;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DTOConfiguracion getByIdPersona(String idPersona, boolean isEmailNotification) {
		DTOConfiguracion res = new DTOConfiguracion();
		try {
			DbManager.open();
			List<Configuracion> configuraciones = DbManager.createQuery("SELECT configuracion FROM Configuracion configuracion where configuracion.idPersona = '" + idPersona + "'");
			if (configuraciones.size() > 0) {
				for (Configuracion configuracion : configuraciones) {
					if (isEmailNotification == configuracion.isEmailNotification()) {
						res = new DTOConfiguracion(configuracion);
					}
				}	
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

}
