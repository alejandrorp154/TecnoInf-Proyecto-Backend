package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOInteres;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.util.DbManager;
import com.javaee.pryectoBack.util.PuntosUsuario;

@Singleton
public class ControladorInteresDA implements ControladorInteresDALocal, ControladorInteresDARemote {

	private PuntosUsuario puntoUsuario;

	public ControladorInteresDA()
	{
		puntoUsuario = new PuntosUsuario();
	}
	
	
	@Override
	public DTOInteres alta(DTOInteres dtoInteres) {
		try {
			DbManager.open();
			Interes interes = new Interes();
			interes.setInteres(dtoInteres.getInteres());
			DbManager.persist(interes);
			DTOInteres dtoInte = new DTOInteres(interes);
			return dtoInte;
		} catch (Exception exception) {
			return new DTOInteres();
		}
	}

	@Override
	public boolean baja(int idInteres) {
		try {
			DbManager.open();
			DTOInteres dtoInteres = getById(idInteres);
			if (dtoInteres.getIdInteres() == 0)
				return false;
			Interes interes = new Interes(dtoInteres);
			DbManager.removeIfContains(interes);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public DTOInteres getById(int idInteres) {
		DTOInteres dtoInteres = new DTOInteres();
		DbManager.open();
		Interes interes = DbManager.find(Interes.class, idInteres);
		if (interes != null)
		{
			dtoInteres = new DTOInteres(interes);
		}
		return dtoInteres;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DTOInteres> getAll(int offset, int size) {
		DbManager.open();
		List<DTOInteres> dtoIntereses = new ArrayList<DTOInteres>();
		List<Interes> intereses = DbManager.createQuery("SELECT interes FROM Interes interes order by interes.idInteres", offset, size);
		for(Interes interes : intereses) {
			DTOInteres dtoInteres = new DTOInteres(interes);
			dtoIntereses.add(dtoInteres);
		}
		return dtoIntereses;
	}

	@Override
	public DTOInteres modificar(DTOInteres dtoInteres) {
		try {
			DbManager.open();
			Interes interes;
			DTOInteres dtoInteresSaved = getById(dtoInteres.getIdInteres());
			if (dtoInteresSaved.getIdInteres() == 0)
			{
				interes = new Interes(dtoInteres);
				interes.setIdInteres(0);
				DbManager.persist(interes);
			}
			else {
				interes = DbManager.find(Interes.class, dtoInteresSaved.getIdInteres());
				interes.setInteres(dtoInteres.getInteres());
				DbManager.merge(interes);
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
			DbManager.open();
			PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, idPersona);
			if (perfil != null) {
				Interes interes = DbManager.find(Interes.class, idInteres);
				if (interes != null) {
					List<Interes> listAux = perfil.getIntereses();
					listAux.add(interes);
					perfil.setIntereses(listAux);
					List<PerfilUsuario> perfilesAux = interes.getPerfiles();
					perfilesAux.add(perfil);
					DbManager.merge(perfil);
					Usuario usuario = DbManager.find(Usuario.class, perfil.getIdPersona());
					if (usuario != null) {
						DTOUsuario dtoUsuario = new DTOUsuario(usuario);
						puntoUsuario.getPuntosUsuario("SubscribeInteres", dtoUsuario);
					}
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
		boolean res = false;
		try {
			PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, idPersona);
			if (perfil != null) {
				Interes interes = DbManager.find(Interes.class, idInteres);
				if (interes != null) {
					List<PerfilUsuario> perfiles = interes.getPerfiles();
					boolean perfilRemovido = perfiles.remove(perfil);
					if (perfilRemovido) {
						DbManager.merge(interes);
						Usuario usuario = DbManager.find(Usuario.class, perfil.getIdPersona());
						if (usuario != null) {
							DTOUsuario dtoUsuario = new DTOUsuario(usuario);
							puntoUsuario.getPuntosUsuario("DesuscribirInteres", dtoUsuario);
						}
						res = true;
					}
				}
			}
			return res;
		} catch (Exception exception) {
			return res;
		}
	}

}
