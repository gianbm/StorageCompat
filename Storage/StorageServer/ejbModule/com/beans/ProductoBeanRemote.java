package com.beans;

import javax.ejb.Remote;

import com.entities.Producto;

@Remote
public interface ProductoBeanRemote extends CRUDRemote<Producto, Long>{

}
