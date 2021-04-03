package com.javaee.pryectoBack.service;

import java.util.List;

import com.javaee.pryectoBack.model.Usuario;

public interface ControladorRemote {
	List<Usuario> getUsuarios();
	void addUsuario(Usuario usuario);
	Usuario getUsuario(int idUsuario) throws Exception;
}
