package com.beans;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metamodel.spi.MetamodelImplementor;

import com.entities.Usuario;

@Stateless
public class CRUDBean<T, ID extends Serializable> implements CRUDRemote<T, ID> {
	
	@PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("StorageServer").createEntityManager();

	/**
	 * Default constructor.
	 */
	public CRUDBean() {
		// TODO Auto-generated constructor stub	
	}

	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public int create(T entity) {
		// TODO Auto-generated method stub
		try {
			em.persist(em.contains(entity) ? entity : em.merge(entity));
			em.flush();
			em.clear();
			return 0;
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		try {
			em.merge(entity);
			em.flush();
			return 0;
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public int delete(T entity) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			em.flush();
			return 0;
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		Class<T> entityClass = getEntityClass();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		TypedQuery<T> query = em.createQuery(cq);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T selectById(Long id) {
		// TODO Auto-generated method stub
		try {
			Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			return em.find(entityClass, id);
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public String[] getColsNames() {
		MetamodelImplementor metamodel = (MetamodelImplementor) em.getMetamodel();
		ClassMetadata classMetadata = (ClassMetadata) metamodel.entityPersister(getEntityClass().getName());

		// Get the columns names by obtaining the persisted property names in the class
		// metadata saved by hibernate
		// And also for the pk
		String[] colNames = classMetadata.getPropertyNames();
		String[] pkColName = { metamodel.entityPersister(getEntityClass().getName()).getIdentifierPropertyName() };

		return Stream.concat(Arrays.stream(colNames), Arrays.stream(pkColName)).toArray(String[]::new);
	}

	@Override
	public List<T> selectAllByActive(int activo) {
		// TODO Auto-generated method stub
		Class<T> entityClass = getEntityClass();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root).where(cb.equal(root.get("activo"), activo));
		TypedQuery<T> query = em.createQuery(cq);
		return query.getResultList();
	}

	/*
	 * Dada una lista de nombres asociadas a una entidad generica con atributo nombre,
	 * retorna todos los objetos que hayan en base de datos que cumplan la condicion de que su nombre
	 * este dentro de la coleccion provista.
	 * En SQL equivale a WHERE entidad.nombre IN (?);
	 */
	@Override
	public List<T> selectAllBy(Collection<String> names) {
		Class<T> entityClass = getEntityClass();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root).where(root.get("nombre").in(names));
		TypedQuery<T> query = em.createQuery(cq);
		return query.getResultList();
	}
	


}
