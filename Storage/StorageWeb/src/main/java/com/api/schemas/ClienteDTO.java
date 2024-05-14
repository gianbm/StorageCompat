package com.api.schemas;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClienteDTO {
	
	private UsuarioCreateDTO usuario;
	private BigDecimal preferencial;
	private String documento;
	
	public UsuarioCreateDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioCreateDTO usuario) {
		this.usuario = usuario;
	}
	public BigDecimal getPreferencial() {
		return preferencial;
	}
	public void setPreferencial(BigDecimal preferencial) {
		this.preferencial = preferencial;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}
