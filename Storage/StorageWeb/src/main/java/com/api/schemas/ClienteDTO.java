package com.api.schemas;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClienteDTO {
	
	private UsuarioCreateDTO usuario;
	private String documento;
	
	public UsuarioCreateDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioCreateDTO usuario) {
		this.usuario = usuario;
	}

	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}
