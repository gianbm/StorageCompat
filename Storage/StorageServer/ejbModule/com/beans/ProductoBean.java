package com.beans;

import javax.ejb.Stateless;

import com.entities.Producto;

@Stateless
public class ProductoBean extends CRUDBean<Producto, Long> implements ProductoBeanRemote{

}
