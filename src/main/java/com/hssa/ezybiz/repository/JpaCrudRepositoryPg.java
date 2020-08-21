package com.hssa.ezybiz.repository;

import java.io.Serializable;
import java.util.List;

public interface JpaCrudRepositoryPg<E, PK extends Serializable> {

	void save(E newInstance);

	void saveAll(List<E> newListInstance);

	public <T> int deleteAllEntities(Class<T> entityType);

	public Integer getMaxId(String propertyName);

	void update(E transientObject);

	void delete(E persistentObject);

	E findById(PK id);

	List<E> findAll();
	
	void updateRecord(E transientObject);

	void saveOrUpdate(E transientObject);

	void saveNewTransaction(E newInstance);

}
