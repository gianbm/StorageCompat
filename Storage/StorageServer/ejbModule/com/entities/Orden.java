package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ORDENES database table.
 * 
 */
@Entity
@Table(name="ORDENES")
@NamedQuery(name="Orden.findAll", query="SELECT o FROM Orden o")
public class Orden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ORDEN", unique=true, nullable=false, precision=38)
	private long idOrden;

	@Column(precision=38)
	private BigDecimal importe;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE", nullable=false)
	private Cliente cliente;

	//bi-directional many-to-one association to OrdenOperador
	@OneToMany(mappedBy="ordene")
	private List<OrdenOperador> ordenesOperadores;

	//bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(
		name="ORDENES_PRODUCTOS"
		, joinColumns={
			@JoinColumn(name="ID_ORDEN", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PRODUCTO", nullable=false)
			}
		)
	private List<Producto> productos;

	public Orden() {
	}

	public long getIdOrden() {
		return this.idOrden;
	}

	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<OrdenOperador> getOrdenesOperadores() {
		return this.ordenesOperadores;
	}

	public void setOrdenesOperadores(List<OrdenOperador> ordenesOperadores) {
		this.ordenesOperadores = ordenesOperadores;
	}

	public OrdenOperador addOrdenesOperadore(OrdenOperador ordenesOperadore) {
		getOrdenesOperadores().add(ordenesOperadore);
		ordenesOperadore.setOrdene(this);

		return ordenesOperadore;
	}

	public OrdenOperador removeOrdenesOperadore(OrdenOperador ordenesOperadore) {
		getOrdenesOperadores().remove(ordenesOperadore);
		ordenesOperadore.setOrdene(null);

		return ordenesOperadore;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}