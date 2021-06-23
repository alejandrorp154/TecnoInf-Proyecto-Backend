package com.javaee.pryectoBack.data;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Remote
public interface ControladorConfigSistemaDARemote {
	DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
	DTOConfiguracion getByIdPersona(String idPersona, boolean isEmailNotification);
}
