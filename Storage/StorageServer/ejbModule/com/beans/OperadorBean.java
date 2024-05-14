package com.beans;

import javax.ejb.Stateless;

import com.entities.Operador;

@Stateless
public class OperadorBean extends  CRUDBean<Operador, Long> implements OperadorBeanRemote{

}
