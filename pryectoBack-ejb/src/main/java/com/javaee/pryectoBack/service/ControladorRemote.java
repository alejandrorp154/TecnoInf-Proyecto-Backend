package com.javaee.pryectoBack.service;

import java.util.List;

import com.javaee.pryectoBack.model.Usuario;

public interface ControladorRemote {
	List<Usuario> getUsuarios(int offset, int size);
	void addUsuario(Usuario usuario);
	Usuario getUsuario(int idUsuario) throws Exception;
	void sendNotification(String message);
}
