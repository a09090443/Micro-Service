package com.localhost.base.dao;

import java.util.List;
import java.util.Map;

public interface BaseHibernateDAO<T> {

	public void setClazz(final Class<T> clazzToSet);

	public void clearSession();

	public T getById(final Long id);

	public List<T> findAll();

	public List<T> findAll(int start, int resultSize);

	public void save(final T entity);
	
	public void saveOrUpdate(final T entity);

	public void update(final T entity);

	public void update(final T entity, Map<String, Object> argMap) throws Exception;

	public void delete(final T entity);

	public void deleteById(final Long entityId);

	public List<?> queryHql(String hql, Map<String, Object> argMap, Boolean cache);

	public List<?> querySql(String sql, Map<String, Object> argMap, Map<String, Class<?>> entityMap);

	public void executeHql(String hql, Map<String, Object> argMap);

	public void executeSql(String sql, Map<String, Object> argMap);

}