package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import entities.Product;
import util.HibernateUtil;

public class ProductDao {
  private OgmSessionFactory sessionFactory;

  public ProductDao() {
    sessionFactory = HibernateUtil.getInstance().getSessionFactory();
  }

  public boolean addProduct(Product product) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(product);
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return false;
  }

  // 2) Display products with model in any 1 year.
  public List<Product> getProductByModel(int model) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {
      NativeQuery<Product> query = session.createNativeQuery("db.products.find({model_year:" + model + "})",
          Product.class);
      list = query.getResultList();
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 3) Display products in any category when the catalog number is known.
  public List<Product> getProductByCateID(int cateID) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.categories.aggregate([{'$match':{'_id':" + cateID
          + "}},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'category_id', 'as':'products'}},{'$unwind':'$products'},{'$replaceWith':'$products'}])";

      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 4) Display products in any category when the catalog name is known.
  public List<Product> getProductByCateName(String categoryName) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.categories.aggregate([{'$match':{'category_name':'" + categoryName
          + "'}},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'category_id', 'as':'products'}},{'$unwind':'$products'},{'$replaceWith':'$products'}])";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 5) Display products in any brand when the brand code is known.
  public List<Product> getProductByBrandID(int id) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.brands.aggregate([{'$match':{'_id':" + id
          + "}},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'category_id', 'as':'products'}},{'$unwind':'$products'},{'$replaceWith':'$products'}])";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 6) Display products in any brand when the brand name is known. e.g. "Electra"
  public List<Product> getProductByBrandName(String brandName) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.brands.aggregate([{'$match':{'brand_name':'" + brandName
          + "'}},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'brand_id', 'as':'products'}},{'$unwind':'$products'},{'$replaceWith':'$products'}])";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 10) Use text search to search on a column or on multiple columns with string
  // values.
  public List<Product> getProductByTextSearch(String key) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.products.find({$text:{$search:'" + key + "'}})";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 11) Find the list of products with the highest prices.
  public List<Product> getProductByHighestPrice() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.products.aggregate([{'$group':{'_id':'null', 'products':{'$push':'$$ROOT'}, 'max_price':{'$max':'$list_price'}}}, {'$unwind':'$products'},{'$match':{'$expr':{'$eq':['$max_price', '$products.list_price']}}},{'$replaceWith':'$products'}])";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  // 12) Find a list of products that haven't sold yet.
  public List<Product> getProductNotSold() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    List<Product> list = null;
    try {

      String mongo = "db.orders.aggregate([{'$unwind':'$order_items'},{'$lookup':{'from':'products', 'localField':'order_items.product_id', 'foreignField':'_id', 'as':'products'}},{'$unwind':'$products'},{'$project':{'product':'$products','tonTai':{'$eq':['products._id','order_items.product_id']}}},{'$match':{'tonTai':"
          + false
          + "}},{'$replaceWith':'$product'},{'$project':{'_id':1,'product_name':1,'list_price':1,'model_year':1}}])";
      NativeQuery<Product> query = session.createNativeQuery(mongo, Product.class);
      list = query.getResultList();
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return list;
  }

  private Product fromDocument(Object document) {
    Product product = new Product();
    Document doc = (Document) document;
    product.setId(doc.getInteger("_id"));
    product.setName(doc.getString("product_name"));
    product.setPrice(doc.getDouble("list_price"));
    product.setModelYear(doc.getInteger("model_year"));
    return product;
  }

  // 16) Calculate the total quantity of each product sold.
  public Map<Product, Integer> getTotalProduct() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    Map<Product, Integer> map = new HashMap<Product, Integer>();
    try {

      String mongo = "db.orders.aggregate([{'$unwind':'$order_items'},{'$group':{'_id':'$order_items.product_id', 'total':{'$sum':'$order_items.quantity'}}},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'_id', 'as':'products'}},{'$unwind':'$products'},{'$replaceWith':{'product':'$products', 'total':'$total'}},{'$project':{'product':1, 'total':1}}])";
      List<Object[]> list = (List<Object[]>) session.createNativeQuery(mongo).getResultList();
      for (Object[] objects : list) {

        Product product = fromDocument(objects[0]);
        Integer total = Integer.parseInt(objects[1].toString());
        map.put(product, total);
      }
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return map;

  }
}
