package com.api.schemas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OperadorDTO {
	
	private UsuarioCreateDTO usuario;
	private CargoDTO cargo;
	
	public UsuarioCreateDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioCreateDTO usuario) {
		this.usuario = usuario;
	}
	public CargoDTO getCargo() {
		return cargo;
	}
	public void setCargo(CargoDTO cargo) {
		this.cargo = cargo;
	}
	
}
