package com.beans;

import javax.ejb.Remote;

import com.entities.Cargo;

@Remote
public interface CargoBeanRemote extends CRUDRemote<Cargo, Long>{
	
}
