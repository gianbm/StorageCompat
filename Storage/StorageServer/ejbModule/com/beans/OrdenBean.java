package com.beans;

import javax.ejb.Stateless;

import com.entities.Orden;

@Stateless
public class OrdenBean extends CRUDBean<Orden, Long> implements OrdenBeanRemote{

}
