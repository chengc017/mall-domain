package com.sohu.sur.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * hibernate 功能扩展
 * @author xuewuhao
 *
 */
public class HibernateBaseDao extends HibernateDaoSupport {
	
	static Logger logger = Logger.getLogger(HibernateBaseDao.class);

	public Object loadObject(Class entity, Serializable id) {

		return getHibernateTemplate().load(entity, id);
	}

	public List getObjects(final Class entity, final List id) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List list = new ArrayList();
				for (int i = 0; i < id.size(); i++) {
					list.add(session.load(entity, (Serializable) id.get(i)));
				}
				return list;
			}
		});
	}

	public List getObjects(final Class entity, final Serializable[] id) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List list = new ArrayList();
				for (int i = 0; i < id.length; i++) {
					list.add(session.load(entity, id[i]));
				}
				return list;
			}
		});
	}

	public Object getObject(Class entity, Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

	public Object saveObject(Object obj) {
		getHibernateTemplate().save(obj);
		return obj;
	}

	public void saveObjects(final Collection data) {
		if (data == null)
			return;
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				for (Iterator i = data.iterator(); i.hasNext();) {
					session.save(i.next());
				}
				return null;
			}
		});
	}

	public void saveOrUpdateAll(Collection collections) {
		getHibernateTemplate().saveOrUpdateAll(collections);
	}

	public void saveOrUpdateObject(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
		return;
	}

	public Object updateObject(Object obj) {
		getHibernateTemplate().update(obj);
		return obj;
	}

	public void updateObjects(final List list) throws DataAccessException {
		if (list == null)
			return;
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				for (int i = 0; i < list.size(); i++) {
					session.update(list.get(i));
				}
				return null;
			}
		});
	}

	public int executeQuery(final String hql, final List params) {

		Object obj = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				int size = params.size();
				for (int i = 0; i < size; i++) {
					Object object = params.get(i);
					setQueryParameter(query, object, i);
				}

				return new Integer(query.executeUpdate());
			}
		});

		return ((Integer) obj).intValue();

	}

	public void executeSqlUpdate(final String sql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				try {
					Connection con = session.connection();
					Statement st = con.createStatement();
					st.execute(sql);
				} catch (SQLException ex) {
					throw new DataAccessResourceFailureException(ex
							.getMessage());
				}
				return null;
			}
		});
	}

	public void executeBatchUpdate(final List sqls) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				try {
					Connection con = session.connection();
					Statement st = con.createStatement();
					for (int i = 0; i < sqls.size(); i++) {
						st.addBatch((String) sqls.get(i));
					}
					if (sqls.size() > 0)
						st.executeBatch();
				} catch (SQLException ex) {
					throw new DataAccessResourceFailureException(ex
							.getMessage());
				}
				return null;
			}
		});
	}

	public void removeObject(Class entity, Serializable id) {
		Object obj = getObject(entity, id);
		if (obj != null) {
			getHibernateTemplate().delete(obj);
		}
	}

	public void removeObject(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public List findByNamedQuery(String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);

	}

	public List findByNamedQuery(String queryName, Object value) {
		return getHibernateTemplate().findByNamedQuery(queryName, value);

	}

	public List findByNamedQuery(String queryName, Object[] value) {
		return getHibernateTemplate().findByNamedQuery(queryName, value);

	}

	public List findObjectListByHql(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public List findObjectListByHql(final String hql, final int firstResult,
			final int maxResult) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
				return query.list();
			}
		});
	}

	public List findObjectListByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	public List findObjectListByCriteria(
			final DetachedCriteria detachedCriteria, final int firstResult,
			final int maxResult) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				return criteria.setFirstResult(firstResult).setMaxResults(
						maxResult).list();
			}
		});
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						criteria.setProjection(null);
						Object obj = criteria.setProjection(
								Projections.rowCount()).uniqueResult();
						return obj;
					}
				});
		return count.intValue();
	}

	public int getCountByHql(String hql) {
		String countHql = this.getCountingString(hql);

		return ((Integer) (getHibernateTemplate().iterate(countHql).next()))
				.intValue();

	}

	public Query getQuery(final String hql) {
		return (Query) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query;
			}
		});
	}

	public Object getResultValue(String hql) {
		List list = getHibernateTemplate().find(hql);
		if (list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Connection getJDBCConnection() {
		return (Connection) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.connection();
					}
				});
	}

	public Session getHibernateSession() {
		return (Session) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return session;
					}
				});
	}

	public void sessionFlush() {
		try {
			this.getSession(false).flush();
		} catch (Exception ex) {
			throw new DataAccessResourceFailureException(ex.getMessage());
		}
	}

	private String getCountingString(String s1) {
		int position1 = s1.toUpperCase().indexOf("FROM");
		String s2 = s1.substring(position1, s1.length());
		int position2 = s2.toUpperCase().indexOf(" ORDER ");
		int position3 = s2.toUpperCase().indexOf(" GROUP ");

		if (position2 != -1 || position3 != -1) {
			if (position2 < position3) {
				if (position2 != -1) {
					s2 = s2.substring(0, position2);
				} else {
					s2 = s2.substring(0, position3);
				}
			} else if (position2 > position3) {
				if (position3 != -1) {
					s2 = s2.substring(0, position3);
				} else {
					s2 = s2.substring(0, position2);
				}
			}
		}

		return "select count(*) " + s2;
	}

	public Criteria getCriteria(final Class entity) {
		return (Criteria) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(entity);
					}
				});
	}

	public long getSequence(final String seqName) {
		return ((Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Connection con = session.connection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT " + seqName
						+ ".nextval FROM dual");
				rs.next();
				return new Long(rs.getLong(1));
			}
		})).longValue();
	}

	private void setQueryParameter(Query query, Object value, int index) {
		Class type = value.getClass();

		if (type.equals(String.class)) {
			query.setString(index, (String) value);

		} else if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
			query.setInteger(index, ((Integer) value).intValue());
		} else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
			query.setBoolean(index, ((Boolean) value).booleanValue());
		} else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
			query.setLong(index, ((Long) value).longValue());
		} else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
			query.setDouble(index, ((Double) value).doubleValue());
		} else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
			query.setFloat(index, ((Float) value).floatValue());
		} else if (type.equals(Short.TYPE) || type.equals(Short.class)) {
			query.setShort(index, ((Short) value).shortValue());
		} else if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
			query.setByte(index, ((Byte) value).byteValue());
		} else if (type.equals(Date.class)) {
			query.setTimestamp(index, new java.sql.Timestamp(((Date) value)
					.getTime()));
		} else {
			query.setEntity(index, value);
		}
	}
}
