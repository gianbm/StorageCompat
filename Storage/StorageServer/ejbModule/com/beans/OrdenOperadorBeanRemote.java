package com.beans;

import javax.ejb.Remote;

import com.entities.OrdenOperador;

@Remote
public interface OrdenOperadorBeanRemote extends CRUDRemote<OrdenOperador, Long>{

}
