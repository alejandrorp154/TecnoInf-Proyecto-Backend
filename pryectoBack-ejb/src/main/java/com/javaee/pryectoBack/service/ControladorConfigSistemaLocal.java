package com.javaee.pryectoBack.service;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Local
public interface ControladorConfigSistemaLocal {
	DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
	DTOConfiguracion getByIdPersona(String idPersona, boolean isEmailNotification);
}
