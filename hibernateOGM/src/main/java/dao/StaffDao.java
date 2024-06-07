package dao;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entities.*;
import util.HibernateUtil;

public class StaffDao {
  private OgmSessionFactory sessionFactory;

  public StaffDao() {
    sessionFactory = HibernateUtil.getInstance().getSessionFactory();
  }

  public boolean addStaff(Staff staff) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(staff);
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return false;
  }
}
