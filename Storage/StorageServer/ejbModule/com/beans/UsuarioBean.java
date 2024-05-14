package com.beans;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;

@Stateless
public class UsuarioBean extends CRUDBean<Usuario, Long> implements UsuarioBeanRemote{

	@Override
	public Usuario selectUserBy(String username) {
		try {
			@SuppressWarnings("unchecked")
			TypedQuery<Usuario> query = (TypedQuery<Usuario>) super.getEntityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario =: username")
				.setParameter("username", username);
			return query.getSingleResult();
		} catch(PersistenceException e) {
			return null;
		}
	}
}
