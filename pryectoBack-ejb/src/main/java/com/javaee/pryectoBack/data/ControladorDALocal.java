package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.model.Usuario;

@Local
public interface ControladorDALocal {
	List<Usuario> getUsuarios(int offset, int size);
	void addUsuario(Usuario usuario);
	Usuario getUsuario(String idUsuario) throws Exception;
}