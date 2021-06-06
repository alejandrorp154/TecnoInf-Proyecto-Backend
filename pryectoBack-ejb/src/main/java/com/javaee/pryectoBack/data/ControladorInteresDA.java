package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.datatypes.DTOInteres;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.PerfilUsuario;

@Singleton
public class ControladorInteresDA implements ControladorInteresDALocal, ControladorInteresDARemote {


	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public ControladorInteresDA()
	{
	}
	
	
	@Override
	public DTOInteres alta(DTOInteres dtoInteres) {
		try {
			Interes interes = new Interes();
			interes.setInteres(dtoInteres.getInteres());
			manager.persist(interes);
			DTOInteres dtoInte = new DTOInteres(interes);
			return dtoInte;
		} catch (Exception exception) {
			return new DTOInteres();
		}
	}

	@Override
	public boolean baja(int idInteres) {
		try {
			DTOInteres dtoInteres = getById(idInteres);
			if (dtoInteres.getIdInteres() == 0)
				return false;
			Interes interes = new Interes(dtoInteres);
			manager.remove(manager.contains(interes) ? interes : manager.merge(interes));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}


	@Override
	public DTOInteres getById(int idInteres) {
		DTOInteres dtoInteres = new DTOInteres();
		Interes interes = manager.find(Interes.class, idInteres);
		if (interes != null)
		{
			dtoInteres = new DTOInteres(interes);
		}
		return dtoInteres;
	}

	@Override
	public List<DTOInteres> getAll(int offset, int size) {
		List<DTOInteres> dtoIntereses = new ArrayList<DTOInteres>();
		TypedQuery<Interes> query = manager.createQuery("SELECT interes FROM Interes interes order by interes.idInteres", Interes.class);
		List<Interes> intereses = query.setFirstResult(offset).setMaxResults(size).getResultList();
		for(Interes interes : intereses) {
			DTOInteres dtoInteres = new DTOInteres(interes);
			dtoIntereses.add(dtoInteres);
		}
		return dtoIntereses;
	}

	@Override
	public DTOInteres modificar(DTOInteres dtoInteres) {
		try {
			Interes interes;
			DTOInteres dtoInteresSaved = getById(dtoInteres.getIdInteres());
			if (dtoInteresSaved.getIdInteres() == 0)
			{
				interes = new Interes(dtoInteres);
				interes.setIdInteres(0);
				manager.persist(interes);
			}
			else {
				dtoInteresSaved.setInteres(dtoInteres.getInteres());
				interes = new Interes(dtoInteresSaved);
				manager.merge(interes);
			}
			return getById(interes.getIdInteres());
		} catch (Exception exception) {
			return new DTOInteres();
		}
	}

	@Override
	public boolean suscribe(String idPersona, int idInteres) {
		boolean res = false;
		try {
			PerfilUsuario perfil = manager.find(PerfilUsuario.class, idPersona);
			if (perfil != null) {
				Interes interes = manager.find(Interes.class, idInteres);
				if (interes != null) {
					List<Interes> listAux = perfil.getIntereses();
					listAux.add(interes);
					perfil.setIntereses(listAux);
					List<PerfilUsuario> perfilesAux = interes.getPerfiles();
					perfilesAux.add(perfil);
					manager.merge(perfil);
					res = true;
				}
			}
			return res;
		} catch (Exception exception) {
			return res;
		}
	}

	@Override
	public boolean desuscribe(String idPersona, int idInteres) {
		// TODO Auto-generated method stub
		return false;
	}

}
