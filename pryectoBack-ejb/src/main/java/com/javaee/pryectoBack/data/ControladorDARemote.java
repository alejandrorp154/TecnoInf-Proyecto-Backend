package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.model.Usuario;

@Remote
public interface ControladorDARemote {
	List<Usuario> getUsuarios();
	void addUsuario(Usuario usuario);
	Usuario getUsuario(int idUsuario) throws Exception;
}
