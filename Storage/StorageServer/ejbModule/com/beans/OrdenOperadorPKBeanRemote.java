package com.beans;

import javax.ejb.Remote;

import com.entities.OrdenOperadorPK;

@Remote
public interface OrdenOperadorPKBeanRemote extends CRUDRemote<OrdenOperadorPK, Long>{

}
