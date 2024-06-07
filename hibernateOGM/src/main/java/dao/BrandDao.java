package dao;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entities.Brand;
import util.HibernateUtil;

public class BrandDao {
  private OgmSessionFactory sessionFactory;

  public BrandDao() {
    sessionFactory = HibernateUtil.getInstance().getSessionFactory();
  }

  public boolean addBrand(Brand brand) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(brand);
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return false;
  }

}
