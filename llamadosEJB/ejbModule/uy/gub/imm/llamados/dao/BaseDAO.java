package uy.gub.imm.llamados.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uy.gub.imm.llamados.dao.local.BaseDAOLocal;

@Stateless
public class BaseDAO<E> implements BaseDAOLocal<E>{
	
	@PersistenceContext
	protected EntityManager em;

	protected Class<E> entityClass;
	protected String className;

	public BaseDAO() {

	}

	public BaseDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
		this.className = entityClass.getSimpleName();
	}

	public void merge(E entity) {

		em.merge(entity);
	}

	public void persist(E entity) {

		em.persist(entity);
	}

	public void flush() {

		em.flush();
	}

	public void remove(E entity) {

		entity = em.merge(entity);
		em.remove(entity);
	}

	public E findById(long id) {

		return em.find(entityClass, id);
	}

	public List<E> findListByQuery(String query, Map<String, Object> params) {

		List<E> list = null;
		StringBuffer paramLog = new StringBuffer();
		TypedQuery<E> typedQuery = em.createNamedQuery(query, entityClass);
		if (params != null) {

			for (Entry<String, Object> entry : params.entrySet()) {

				String key = entry.getKey();
				Object value = entry.getValue();

				typedQuery.setParameter(key, value);
				paramLog.append(key + " = " + value + ", ");
			}
		}

		list = typedQuery.getResultList();

		return list;
	}

	public E findObjectByQuery(String query, Map<String, Object> params) {

		E obj = null;
		StringBuffer paramLog = new StringBuffer();
		TypedQuery<E> typedQuery = em.createNamedQuery(query, entityClass);
		if (params != null) {

			for (Entry<String, Object> entry : params.entrySet()) {

				String key = entry.getKey();
				Object value = entry.getValue();

				typedQuery.setParameter(key, value);
				paramLog.append(key + " = " + value + ", ");
			}
		}
		try {
			obj = typedQuery.getSingleResult();
		} catch (NoResultException nre) {

		}

		return obj;
	}
	
	public List<E> findAll(){
		List<E> list = null;
		String query="select e from "+className+" e";
		TypedQuery<E> typedQuery = em.createQuery(query,entityClass);
		list = typedQuery.getResultList();
		return list;

	}

}


