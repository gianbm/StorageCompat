package com.beans;

import javax.ejb.Stateless;

import com.entities.OrdenOperador;

@Stateless
public class OrdenOperadorBean extends CRUDBean<OrdenOperador, Long> implements OrdenOperadorBeanRemote{

}
