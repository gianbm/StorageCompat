package com.beans;

import javax.ejb.Stateless;

import com.entities.Cliente;

@Stateless
public class ClienteBean  extends CRUDBean<Cliente, Long> implements ClienteBeanRemote {

}
