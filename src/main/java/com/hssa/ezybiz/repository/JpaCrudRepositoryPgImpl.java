package com.hssa.ezybiz.repository;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.hssa.ezybiz.repository.JpaCrudRepositoryPg;
import com.hssa.ezybiz.utils.ReflectionUtil;

public abstract class JpaCrudRepositoryPgImpl<E, PK extends Serializable> implements JpaCrudRepositoryPg<E, PK> {

	private Class<E> persistentClass;
	
	@PersistenceContext(unitName = "postgresqlPU")
	private EntityManager entityMangerQdw;

	public EntityManager getEntityManager() {
		return entityMangerQdw;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityMangerQdw = entityManager;
	}

	@SuppressWarnings("unchecked")
	public JpaCrudRepositoryPgImpl() {
		Type[] types;
		types = ReflectionUtil.getParameterizedTypes(this);
		persistentClass = (Class<E>) types[0];
	}

	protected Class<E> getPersistenceClass() {
		return persistentClass;
	}

	@Override
	public void save(E newInstance) {
		getEntityManager().persist(newInstance);
	}

	@Override
	public void saveAll(List<E> newListInstance) {

		for (E obj : newListInstance)
			getEntityManager().persist(obj);

	}

	@Override
	public <T> int deleteAllEntities(Class<T> entityType) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<T> query = builder.createCriteriaDelete(entityType);
		query.from(entityType);
		return getEntityManager().createQuery(query).executeUpdate();
	}

	@Override
	public void update(E transientObject) {
		getEntityManager().merge(transientObject);
	}

	@Override
	public void delete(E persistentObject) {
		getEntityManager().remove(persistentObject);
	}

	@Override
	public E findById(PK id) {
		return getEntityManager().find(persistentClass, id);
	}

	@Override
	public List<E> findAll() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<E> query = cb.createQuery(getPersistenceClass());
		Root<E> root = query.from(getPersistenceClass());
		query.select(root);
		TypedQuery<E> tq = getEntityManager().createQuery(query);
		return tq.getResultList();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getMaxId(String propertyName) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = cb.createQuery(Integer.class);
		Root from = criteriaQuery.from(getPersistenceClass());
		Expression expression = cb.max(from.get(propertyName));
		CriteriaQuery<Integer> select = criteriaQuery.select(expression);
		TypedQuery<Integer> typedQuery = getEntityManager().createQuery(select);
		Integer maxId = typedQuery.getSingleResult();
		if (maxId == null)
			maxId = 0;
		return maxId;
	}

	public Long getRecordCount(CriteriaQuery<?> criteria) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<?> entityRoot = countCriteria.from(criteria.getResultType());
		countCriteria.select(builder.count(entityRoot));
		return getEntityManager().createQuery(countCriteria).getSingleResult();
	}


	@Override
    public void updateRecord(E transientObject) {
		getEntityManager().unwrap(Session.class).update(transientObject);
    }
	
	@Override
    public void saveOrUpdate(E transientObject) {
		getEntityManager().unwrap(Session.class).saveOrUpdate(transientObject);
    }
	
	@Override
	public void saveNewTransaction(E newInstance) {
		getEntityManager().persist(newInstance);
		getEntityManager().flush();
		getEntityManager().clear();
	}
	
}
