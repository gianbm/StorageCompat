package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRODUCTOS database table.
 * 
 */
@Entity
@Table(name="PRODUCTOS")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO", unique=true, nullable=false, precision=38)
	private long idProducto;

	@Column
	private String codigo;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(name="precio")
	private BigDecimal precio;

	@Column(precision=38)
	private int stock;

	@Column(nullable=false, length=50)
	private String tipo;
	
	@Column(length=100)
	private String img;

	//bi-directional many-to-many association to Orden
	@ManyToMany(mappedBy="productos")
	private List<Orden> ordenes;

	public Producto() {
	}

	public long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Orden> getOrdenes() {
		return this.ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}