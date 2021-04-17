package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorDALocal;
import com.javaee.pryectoBack.model.Usuario;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

@Stateless
@Remote(ControladorRemote.class)
public class Controlador implements ControladorRemote, ControladorLocal {

	@EJB
	private ControladorDALocal controladorDA;

	@Override
	public List<Usuario> getUsuarios(int offset, int size) {
		return this.controladorDA.getUsuarios(offset, size);
	}

	@Override
	public void addUsuario(Usuario usuario) {
		
		GeometryFactory geometryFactory = new GeometryFactory();
		Point punto = geometryFactory.createPoint( new Coordinate( -34.2020363, -53.8581112 ) );
		
		usuario.setPunto(punto);
		this.controladorDA.addUsuario(usuario);
		
	}

	@Override
	public Usuario getUsuario(int idUsuario) throws Exception {
		Usuario res = this.controladorDA.getUsuario(idUsuario);
		if (res != null) {
			return res;
		}
		throw new Exception("No existe Usuario con id=" + idUsuario);
	}
}
