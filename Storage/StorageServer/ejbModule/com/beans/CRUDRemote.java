package com.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Usuario;

@Remote
public interface CRUDRemote<T, ID> extends Serializable{

	int create(T entity);

	int update(T entity);

	int delete(T entity);

	List<T> selectAll();

	T selectById(Long id);

	String[] getColsNames();

	List<T> selectAllByActive(int activo);

	List<T> selectAllBy(Collection<String> names);

	Usuario selectUserBy(String username);

}
