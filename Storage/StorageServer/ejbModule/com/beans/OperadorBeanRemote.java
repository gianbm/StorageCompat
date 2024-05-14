package com.beans;

import javax.ejb.Remote;

import com.entities.Operador;

@Remote
public interface OperadorBeanRemote extends CRUDRemote<Operador, Long>{

}
