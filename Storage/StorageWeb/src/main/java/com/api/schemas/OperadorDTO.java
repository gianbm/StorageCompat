package com.api.schemas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OperadorDTO {
	
	private UsuarioCreateDTO usuario;
	private String cargo;
	
	public UsuarioCreateDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioCreateDTO usuario) {
		this.usuario = usuario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
