package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.model.Usuario;

@Local
public interface ControladorLocal {
	List<Usuario> getUsuarios(int offset, int size);
	void addUsuario(Usuario usuario);
	Usuario getUsuario(int idUsuario) throws Exception;
	void sendNotification(String message);
}
