package com.javaee.pryectoBack.service;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Remote
public interface ControladorConfigSistemaRemote {
	DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
	DTOConfiguracion getByIdPersona(String idPersona);
}
