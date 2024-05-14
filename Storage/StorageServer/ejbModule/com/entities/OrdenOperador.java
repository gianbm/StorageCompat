package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ORDENES_OPERADORES database table.
 * 
 */
@Entity
@Table(name="ORDENES_OPERADORES")
@NamedQuery(name="OrdenOperador.findAll", query="SELECT o FROM OrdenOperador o")
public class OrdenOperador implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdenOperadorPK id;

	@Column(nullable=false)
	private Timestamp fecha;

	//bi-directional many-to-one association to Operador
	@ManyToOne
	@JoinColumn(name="ID_OPERADOR", nullable=false, insertable=false, updatable=false)
	private Operador operadore;

	//bi-directional many-to-one association to Orden
	@ManyToOne
	@JoinColumn(name="ID_ORDEN", nullable=false, insertable=false, updatable=false)
	private Orden ordene;

	public OrdenOperador() {
	}

	public OrdenOperadorPK getId() {
		return this.id;
	}

	public void setId(OrdenOperadorPK id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Operador getOperadore() {
		return this.operadore;
	}

	public void setOperadore(Operador operadore) {
		this.operadore = operadore;
	}

	public Orden getOrdene() {
		return this.ordene;
	}

	public void setOrdene(Orden ordene) {
		this.ordene = ordene;
	}

}