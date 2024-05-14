package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the CLIENTES database table.
 * 
 */
@Entity
@Table(name="CLIENTES")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CLIENTE", unique=true, nullable=false, precision=38)
	private long idCliente;

	@Column(length=100)
	private String documento;

	@Column(nullable=false, precision=1)
	private BigDecimal preferencial;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="cliente")
	private List<Orden> ordenes;

	public Cliente() {
	}

	public long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public BigDecimal getPreferencial() {
		return this.preferencial;
	}

	public void setPreferencial(BigDecimal preferencial) {
		this.preferencial = preferencial;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Orden> getOrdenes() {
		return this.ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public Orden addOrdene(Orden ordene) {
		getOrdenes().add(ordene);
		ordene.setCliente(this);

		return ordene;
	}

	public Orden removeOrdene(Orden ordene) {
		getOrdenes().remove(ordene);
		ordene.setCliente(null);

		return ordene;
	}

}