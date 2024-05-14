package com.beans;

import javax.ejb.Remote;

import com.entities.Usuario;

@Remote
public interface UsuarioBeanRemote extends CRUDRemote<Usuario, Long> {

}
