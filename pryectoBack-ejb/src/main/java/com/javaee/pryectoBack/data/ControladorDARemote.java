package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.model.Usuario;

@Remote
public interface ControladorDARemote {
	List<Usuario> getUsuarios(int offset, int size);
	void addUsuario(Usuario usuario);
	Usuario getUsuario(String idUsuario) throws Exception;
}
