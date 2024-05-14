package com.beans;

import javax.ejb.Remote;

import com.entities.Cliente;

@Remote
public interface ClienteBeanRemote extends CRUDRemote<Cliente, Long> {

}
