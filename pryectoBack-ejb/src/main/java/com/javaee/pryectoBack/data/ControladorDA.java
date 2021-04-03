package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.model.Usuario;

@Singleton
public class ControladorDA implements ControladorDALocal, ControladorDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	public ControladorDA()
	{
	}

	@Override
	public Usuario getUsuario(int idUsuario) throws Exception {
		Usuario usuario = manager.find(Usuario.class, idUsuario);
		if (usuario == null)
		{
			usuario = new Usuario();
		}
		return usuario;
	}

	@Override
	public List<Usuario> getUsuarios(int offset, int size) {
		TypedQuery<Usuario> query = manager.createQuery("SELECT usuario FROM Usuario usuario order by usuario.idUsuario", Usuario.class);
		List<Usuario> usuarios = query.setFirstResult(offset).setMaxResults(size).getResultList();
		return usuarios;
	}

	@Override
	public void addUsuario(Usuario usuario) {
		manager.persist(usuario);
	}
}
