package com.localhost.base.dao.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.localhost.base.dao.BaseHibernateDAO;

@Transactional(rollbackFor = Exception.class)
public abstract class BaseHibernateDAOImpl<T extends Object> implements BaseHibernateDAO<T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseHibernateDAOImpl.class);

	private Class<T> clazz;
	private String entityName;

	@Autowired
	private SessionFactory sessionFactory;

	public void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	public T getById(final Long id) {
		if (id != null)
			return (T) getCurrentSession().get(clazz, id);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createCriteria(clazz).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(int start, int resultSize) {
		return getCurrentSession().createCriteria(clazz).setFirstResult(start).setMaxResults(resultSize).list();
	}

	@Transactional(readOnly = false)
	public void save(final T entity) {
		// getCurrentSession().persist(entity);
		getCurrentSession().save(entity);
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(final T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional(readOnly = false)
	public void update(final T entity) {
		getCurrentSession().merge(entity);
	}

	@Transactional(readOnly = false)
	public void update(final T entity, Map<String, Object> argMap) throws Exception {
		entityName = entity.getClass().getSimpleName();
		String hqlStr;
		hqlStr = "UPDATE " + entityName + " " + entityName.substring(0, 1) + " SET ";
		hqlStr += this.genHql(entity, argMap, "");
		logger.info("Execute Hql : {}", hqlStr);
		// getCurrentSession().createQuery(hqlStr.toString()).setProperties(argMap).executeUpdate();
		this.executeHql(hqlStr.toString(), argMap);
	}

	@Transactional(readOnly = false)
	public void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	public List<?> queryHql(String hql, Map<String, Object> argMap, Boolean cache) {
		if (null == cache || !cache) {
			return getCurrentSession().createQuery(hql).setProperties(argMap).list();
		} else {
			return getCurrentSession().createQuery(hql).setProperties(argMap).setCacheable(true).list();
		}
	}

	@SuppressWarnings("rawtypes")
	public List<?> querySql(String sql, Map<String, Object> argMap, Map<String, Class<?>> entityMap) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		Iterator<?> iter = entityMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			Class<?> val = (Class<?>) entry.getValue();
			query.addEntity(key, val);
		}

		Iterator iterMap = argMap.entrySet().iterator();
		StringBuilder mapContact = new StringBuilder();
		while (iterMap.hasNext()) {
			Map.Entry entry = (Map.Entry) iterMap.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			mapContact.append(key + " : " + val + "\n");
		}
		logger.info("Execute Sql : {}", sql);
		logger.info("argMap : {}", mapContact);

		return query.setProperties(argMap).list();
	}

	@Transactional(readOnly = false)
	public void executeHql(String hql, Map<String, Object> argMap) {
		getCurrentSession().createQuery(hql).setProperties(argMap).executeUpdate();
	}

	@Transactional(readOnly = false)
	public void executeSql(String sql, Map<String, Object> argMap) {
		getCurrentSession().createSQLQuery(sql).setProperties(argMap).executeUpdate();
	}

	public final void clearSession() {
		getCurrentSession().clear();
	}

	public final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private String genHql(Object entity, Map<String, Object> argMap, String hqlStr) throws Exception {
		String whereStr = "";
		String entityFirstWord = entityName.substring(0, 1);
		BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
		Integer entityLen = entity.getClass().getSimpleName().length();
		Boolean compareEntityName = entity.getClass().getSimpleName().substring(entityLen - 2, entityLen).equals("PK");
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String propName = descriptors[i].getName();
			Class<?> propType = descriptors[i].getPropertyType();
			Object value = PropertyUtils.getProperty(entity, propName);
			if ((!propName.equals("class") && null != value) || propName.equals("pk")) {
				if (propType.getSimpleName().equals("Integer") || propType.getSimpleName().equals("int")) {
					if (compareEntityName) {
						hqlStr += entityFirstWord + ".pk." + propName + "=" + value + "";
					} else {
						hqlStr += entityFirstWord + "." + propName + "=" + value + "";
					}
				} else if (propType.getSimpleName().equals("String")) {
					if (compareEntityName) {
						hqlStr += entityFirstWord + ".pk." + propName + "=" + "'" + value + "'";
					} else {
						hqlStr += entityFirstWord + "." + propName + "=" + "'" + value + "'";
					}
				} else if (propType.getSimpleName().equals("Boolean")) {
					hqlStr += entityFirstWord + "." + propName + "=" + "" + value + "";
				} else if (propName.equals("pk")) {
					if (null != value) {
						hqlStr = this.genHql(value, null, hqlStr);
					}
					Class<?> c = null;
					c = Class.forName(propType.getName());
					Field[] fields = c.getDeclaredFields();
					for (Field f : fields) {
						f.setAccessible(true);
					}
					for (Field f : fields) {
						String field = f.toString().substring(f.toString().lastIndexOf(".") + 1);
						if (null != argMap.get(field)) {
							whereStr += entityFirstWord + ".pk." + field + "=:" + field + " AND ";
						}
					}
				} else {
					continue;
				}
				if (null != value) {
					hqlStr += ",";
				}
				if (null != argMap && null != argMap.get(propName)) {
					whereStr += entityFirstWord + "." + propName + "=:" + propName + " AND ";
				}
			}
		}
		if (!whereStr.isEmpty()) {
			whereStr = " WHERE " + whereStr.substring(0, whereStr.length() - 5);
		}
		hqlStr = hqlStr.substring(0, hqlStr.length() - 1) + whereStr;
		return hqlStr;
	}
}