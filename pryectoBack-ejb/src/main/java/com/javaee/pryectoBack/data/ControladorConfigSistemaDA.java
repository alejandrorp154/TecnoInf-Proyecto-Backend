package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Singleton
public class ControladorConfigSistemaDA implements ControladorConfigSistemaDALocal, ControladorConfigSistemaDARemote {

	@Override
	public boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		// TODO Auto-generated method stub
		return false;
	}

}
