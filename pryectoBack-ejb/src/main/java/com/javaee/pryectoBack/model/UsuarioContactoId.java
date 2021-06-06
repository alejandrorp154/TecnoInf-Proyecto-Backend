package com.javaee.pryectoBack.model;

import java.io.Serializable;

public class UsuarioContactoId implements Serializable {
	private static final long serialVersionUID = 1L;
    private String idPersona;

    private String contactoIdPersona;

    // default constructor

    public UsuarioContactoId(String idPersona, String contactoIdPersona) {
        this.idPersona = idPersona;
        this.contactoIdPersona = contactoIdPersona;
    }

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getContactoIdPersona() {
		return contactoIdPersona;
	}

	public void setContactoIdPersona(String contactoIdPersona) {
		this.contactoIdPersona = contactoIdPersona;
	}

	public UsuarioContactoId() {
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
        result = prime * result + ((contactoIdPersona == null) ? 0 : contactoIdPersona.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioContactoId other = (UsuarioContactoId) obj;
        if (idPersona == null) {
            if (other.idPersona != null)
                return false;
        } else if (!idPersona.equals(other.idPersona))
            return false;
        if (contactoIdPersona == null) {
            if (other.contactoIdPersona != null)
                return false;
        } else if (!contactoIdPersona.equals(other.contactoIdPersona))
            return false;
        return true;
    }
    
}
