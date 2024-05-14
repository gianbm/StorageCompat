package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the OPERADORES database table.
 * 
 */
@Entity
@Table(name="OPERADORES")
@NamedQuery(name="Operador.findAll", query="SELECT o FROM Operador o")
public class Operador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_OPERADOR", unique=true, nullable=false, precision=38)
	private long idOperador;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="ID_CARGO", nullable=false)
	private Cargo cargo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to OrdenOperador
	@OneToMany(mappedBy="operadore")
	private List<OrdenOperador> ordenesOperadores;

	public Operador() {
	}

	public long getIdOperador() {
		return this.idOperador;
	}

	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<OrdenOperador> getOrdenesOperadores() {
		return this.ordenesOperadores;
	}

	public void setOrdenesOperadores(List<OrdenOperador> ordenesOperadores) {
		this.ordenesOperadores = ordenesOperadores;
	}

	public OrdenOperador addOrdenesOperadore(OrdenOperador ordenesOperadore) {
		getOrdenesOperadores().add(ordenesOperadore);
		ordenesOperadore.setOperadore(this);

		return ordenesOperadore;
	}

	public OrdenOperador removeOrdenesOperadore(OrdenOperador ordenesOperadore) {
		getOrdenesOperadores().remove(ordenesOperadore);
		ordenesOperadore.setOperadore(null);

		return ordenesOperadore;
	}

}