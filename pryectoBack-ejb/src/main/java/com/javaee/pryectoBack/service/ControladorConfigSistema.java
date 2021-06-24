package com.javaee.pryectoBack.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorConfigSistemaDALocal;
import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Stateless
@Remote(ControladorConfigSistemaRemote.class)
public class ControladorConfigSistema implements ControladorConfigSistemaLocal, ControladorConfigSistemaRemote {

	@EJB
	private ControladorConfigSistemaDALocal controladorConfigSistemaDA;
	
	@Override
	public DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		return controladorConfigSistemaDA.configurarNotificaciones(dtoConfiguracion);
	}

	@Override
	public DTOConfiguracion getByIdPersona(String idPersona, boolean isEmailNotification) {
		return controladorConfigSistemaDA.getByIdPersona(idPersona, isEmailNotification);
	}
}
