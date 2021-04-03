package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorDALocal;
import com.javaee.pryectoBack.model.Usuario;

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
