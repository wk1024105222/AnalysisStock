package crawler.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("unchecked")
public class HibernateUtil_old {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static List query(String hql) {
		return query(hql, new ArrayList());
	}

	public static List query(String hql, List args) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Query query = session.createQuery(hql);

		String type_long = null;
		String type_short = null;
		Method m = null;
		Object o = null;
		for (int i = 0; i != args.size(); i++) {
			o = args.get(i);
			type_long = o.getClass().toString().substring(6);
			type_short = type_long.substring(type_long.lastIndexOf(".") + 1);
			try {
				if(type_short.equals("Integer")) {
					m = query.getClass().getMethod("set" + type_short, int.class, int.class);
					m.invoke(query, i, ((Integer) o).intValue());
				} else if(type_short.equals("Long")) {
					m = query.getClass().getMethod("set" + type_short, int.class, long.class);
					m.invoke(query, i, ((Long) o).longValue());
				} else if(type_short.equals("Float")) {
					m = query.getClass().getMethod("set" + type_short, int.class, float.class);
					m.invoke(query, i, ((Float) o).floatValue());
				} else if(type_short.equals("Double")) {
					m = query.getClass().getMethod("set" + type_short, int.class, double.class);
					m.invoke(query, i, ((Double) o).doubleValue());
				} else if(type_short.equals("Boolean")) {
					m = query.getClass().getMethod("set" + type_short, int.class, boolean.class);
					m.invoke(query, i, ((Boolean) o).booleanValue());
				} else if(type_short.equals("Byte")) {
					m = query.getClass().getMethod("set" + type_short, int.class, byte.class);
					m.invoke(query, i, ((Byte) o).byteValue());
				} else if(type_short.equals("Character")) {
					m = query.getClass().getMethod("set" + type_short, int.class, char.class);
					m.invoke(query, i, ((Character) o).charValue());
				} else if(type_short.equals("Short")) {
					m = query.getClass().getMethod("set" + type_short, int.class, short.class);
					m.invoke(query, i, ((Short) o).shortValue());
				} else {
					m = query.getClass().getMethod("set" + type_short, int.class, Class.forName(type_long));
					m.invoke(query, i, o);
				}
			} catch (SecurityException e) {

				e.printStackTrace();
			} catch (NoSuchMethodException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			}

		}

		List tmp = query.list();
		session.getTransaction().commit();

		return tmp;

	}

	public static void save(String entityName, Object o) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.save(entityName, o);

		session.getTransaction().commit();
	}
	
	public static void save(Object o) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.save(o);

		session.getTransaction().commit();
	}

	public static void update(String entityName, Object o) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.update(entityName, o);

		session.getTransaction().commit();

	}

	public static void update(Object o) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.update(o);

		session.getTransaction().commit();

	}

	public static void update(List list) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		for (Object o : list) {
			session.update(o);
		}

		session.getTransaction().commit();

	}

	public static Object get(String entityName, Serializable id) {

		Session session = HibernateUtil_old.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Object o = session.get(entityName, id);

		session.getTransaction().commit();

		return o;

	}

	// public static String getClassName(Object o) {
	//		
	// }

}

/**
 * class SaveObject { public String className; public Object object;
 * 
 * public SaveObject(String className, Object object) { super(); this.className
 * = className; this.object = object; }
 * 
 * }
 */
