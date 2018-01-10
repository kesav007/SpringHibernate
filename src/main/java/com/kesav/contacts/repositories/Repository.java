package com.kesav.contacts.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Repository<E> {

	private final EntityManager em = Persistence.createEntityManagerFactory("training").createEntityManager();
	private final Class<E> entityClass;

	public Repository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E find(Long id) {
		return em.find(entityClass, id);		
	}

	public List<E> findAll() {
		//return em.getEntityManagerFactory().createEntityManager().createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
		return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();		
	}

	public E save(E entity) {
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		return entity;
	}

	public void delete(E entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
