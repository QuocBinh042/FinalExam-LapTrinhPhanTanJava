package dao;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Patient;
import util.Connect;

public class PaitentDao {
  private OgmSessionFactory sessionFactory;

  public PaitentDao() {
    sessionFactory = Connect.getInstance().getSessionFactory();
  }

  public boolean addPaintent(Patient patient) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.save(patient);
      tr.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tr.rollback();
    }
    return false;
  }
}
