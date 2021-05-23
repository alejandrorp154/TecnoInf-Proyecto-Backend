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
	public boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		// TODO Auto-generated method stub
		return false;
	}
}
