package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import entities.Customer;
import util.HibernateUtil;

public class CustomerDao {
	private OgmSessionFactory sessionFactory;

	public CustomerDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	public boolean addCus(Customer cus) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			session.save(cus);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	// test
	public List<Customer> getAllCus() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Customer> list = null;
		try {
			NativeQuery<Customer> query = session.createNativeQuery("db.customers.find({})", Customer.class);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

	// 13) Statistics of customers by state
	public Map<String, Integer> getNumberCustomerByState() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			String mongo = "db.customers.aggregate([{'$group':{'_id':'$address.state', 'soDan':{'$sum':1}}}])";
			List<Object[]> list = (List<Object[]>) session.createNativeQuery(mongo).getResultList();
			for (Object[] objects : list) {
				map.put(objects[0].toString(), Integer.parseInt(objects[1].toString()));
			}
			tx.commit();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
}
