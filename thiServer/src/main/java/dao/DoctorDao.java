package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Department;
import entity.Doctor;
import util.Connect;

public class DoctorDao {
  private OgmSessionFactory sessionFactory;

  public DoctorDao() {
    sessionFactory = Connect.getInstance().getSessionFactory();
  }

  public List<Doctor> getDoctorsBySpecialty(String specialty) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      String mongo = "db.doctors.find({$text:{$search:'" + specialty + "'}})";
      tr.begin();
      List<Doctor> doctors = session.createNativeQuery(mongo, Doctor.class).getResultList();
      tr.commit();
      return doctors;
    } catch (Exception e) {
      e.printStackTrace();
      tr.rollback();
    }
    return null;
  }

  public Map<Department, Integer> getNumOfDoctorsByDepartments() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    Map<Department, Integer> map = new HashMap<Department, Integer>();
    try {
      String mongo = " db.departments.aggregate([{'$lookup':{'from':'doctors', 'localField':'_id', 'foreignField':'departmentId', 'as':'doctors'}},{'$project':{'name':1, 'location':1,'sl':{'$size':'$doctors'}}}])";
      tr.begin();
      List<Object[]> list = session.createNativeQuery(mongo).getResultList();
      for (Object[] objects : list) {
        Department department = new Department();
        department.setId(objects[0].toString());
        department.setName(objects[1].toString());
        department.setLocation(objects[2].toString());
        int numOfDoctors = Integer.parseInt(objects[3].toString());
        map.put(department, numOfDoctors);
      }
      tr.commit();
      return map;
    } catch (Exception e) {
      e.printStackTrace();
      tr.rollback();
    }
    return null;
  }
}
