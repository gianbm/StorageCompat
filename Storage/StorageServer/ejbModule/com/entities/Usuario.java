package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO", unique=true, nullable=false, precision=38)
	private long idUsuario;

	@Column(nullable=false, length=100)
	private String apellido;

	@Column(nullable=false, length=50)
	private String contrasenia;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(nullable=false, precision=38)
	private BigDecimal telefono;

	@Column(nullable=false, length=100)
	private String usuario;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private Set<Cliente> clientes;

	//bi-directional many-to-one association to Operador
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private Set<Operador> operadores;

	public Usuario() {
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		if(cliente == null) {
			return null;
		}
		getClientes().add(cliente);
		cliente.setUsuario(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setUsuario(null);

		return cliente;
	}

	public Set<Operador> getOperadores() {
		return this.operadores;
	}

	public void setOperadores(Set<Operador> operadores) {
		this.operadores = operadores;
	}

	public Operador addOperadore(Operador operadore) {
		if(operadore == null) {
			return null;
		}
		getOperadores().add(operadore);
		operadore.setUsuario(this);

		return operadore;
	}

	public Operador removeOperadore(Operador operadore) {
		getOperadores().remove(operadore);
		operadore.setUsuario(null);

		return operadore;
	}

}