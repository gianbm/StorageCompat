package com.beans;	

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.entities.Cargo;

@Stateless
public class CargoBean extends CRUDBean<Cargo, Long> implements CargoBeanRemote {

	public CargoBean() {
		// TODO Auto-generated constructor stub
	}

}
