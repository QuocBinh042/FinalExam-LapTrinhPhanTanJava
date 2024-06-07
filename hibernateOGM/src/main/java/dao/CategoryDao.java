package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entities.*;
import util.HibernateUtil;

public class CategoryDao {
  private OgmSessionFactory sessionFactory;

  public CategoryDao() {
    sessionFactory = HibernateUtil.getInstance().getSessionFactory();
  }

  public boolean addCategory(Category category) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(category);
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return false;
  }

  // 9) Calculate the total number of products by each category, the information
  // includes: Catalog code, category name and
  // Product number
  public List<Map<Category, Integer>> getCategoriesByProductNumber() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Map<Category, Integer>> list = new ArrayList<Map<Category, Integer>>();
    try {
      String mongo = "db.categories.aggregate([{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'category_id', 'as':'products'}}, {'$project':{'category_name':1,'soluong':{'$size':'$products'}}}])";
      List<Object[]> listObj = session.createNativeQuery(mongo).getResultList();
      for (Object[] obj : listObj) {
        Map<Category, Integer> map = new HashMap<>();
        Category cate = new Category();
        cate.setId(Integer.parseInt(obj[0].toString()));
        cate.setName(obj[1].toString());
        map.put(cate, Integer.parseInt(obj[2].toString()));
        list.add(map);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return null;

  }
}
