package com.beans;

import javax.ejb.Stateless;

import com.entities.OrdenOperadorPK;

@Stateless
public class OrdenOperadorPKBean extends CRUDBean<OrdenOperadorPK, Long> implements OrdenOperadorPKBeanRemote{

}
