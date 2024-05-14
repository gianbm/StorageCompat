package com.beans;

import javax.ejb.Remote;

import com.entities.Orden;

@Remote
public interface OrdenBeanRemote extends CRUDRemote<Orden, Long>{

}
