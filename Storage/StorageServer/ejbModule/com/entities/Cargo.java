package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CARGOS database table.
 * 
 */
@Entity
@Table(name="CARGOS")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CARGO")
	private long idCargo;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Operador
	@OneToMany(mappedBy="cargo",  cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Operador> operadores;

	public Cargo() {
	}

	public long getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(long idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Operador> getOperadores() {
		return this.operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}


}