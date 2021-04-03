package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.model.Usuario;

@Singleton
public class ControladorDA implements ControladorDALocal, ControladorDARemote {

	//PROVISORIO, luego se utiliza JPA.
	private List<Usuario> usuarios;
	
	public ControladorDA() {
		this.cargarDatosDebug();
	}

	// Agrega 5 Usuario
	public void cargarDatosDebug() {
		
		this.usuarios = new ArrayList<Usuario>();
		
		for (int i = 1; i < 6; i++) {
			String nombre = "nombre" + " " + Integer.toString(i);
			String email = "nombre" + Integer.toString(i) + "@example.com";
			Usuario usuario = new Usuario(i, nombre, email);
			usuarios.add(usuario);
		}
	}

	@Override
	public Usuario getUsuario(int idUsuario) throws Exception {
		for (Usuario usuario : usuarios) {
			if (usuario.getIdUsuario() == idUsuario) {
				return usuario;
			}
		}
		throw new Exception("No existe Usuario con id=" + idUsuario);
	}

	@Override
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	@Override
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
}
