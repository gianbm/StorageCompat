package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORDENES_OPERADORES database table.
 * 
 */
@Embeddable
public class OrdenOperadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_OPERADOR", insertable=false, updatable=false, unique=true, nullable=false, precision=38)
	private long idOperador;

	@Column(name="ID_ORDEN", insertable=false, updatable=false, unique=true, nullable=false, precision=38)
	private long idOrden;

	public OrdenOperadorPK() {
	}
	public long getIdOperador() {
		return this.idOperador;
	}
	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}
	public long getIdOrden() {
		return this.idOrden;
	}
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdenOperadorPK)) {
			return false;
		}
		OrdenOperadorPK castOther = (OrdenOperadorPK)other;
		return 
			(this.idOperador == castOther.idOperador)
			&& (this.idOrden == castOther.idOrden);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idOperador ^ (this.idOperador >>> 32)));
		hash = hash * prime + ((int) (this.idOrden ^ (this.idOrden >>> 32)));
		
		return hash;
	}
}