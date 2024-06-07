package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.google.gson.Gson;

import entities.Address;
import entities.Customer;
import entities.Order;
import util.HibernateUtil;

public class OrderDao {
  private OgmSessionFactory sessionFactory;

  public OrderDao() {
    sessionFactory = HibernateUtil.getInstance().getSessionFactory();
  }

  public boolean addOrder(Order order) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(order);
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return false;
  }

  // 7) List the order that sell certain products when the product name is known
  // not sure
  public List<Order> getOrdersByProductName(String productName) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    try {
      String mongo = "db.orders.aggregate([{'$unwind':'$order_items'},{'$lookup':{'from':'products', 'localField':'order_items.product_id', 'foreignField':'_id', 'as':'products'}},{'$unwind':'$products'},{'$match':{'products.product_name':'"
          + productName
          + "'}},{'$project':{'products':0}},{'$addFields':{'order_total':{'$multiply':['$order_items.quantity','$order_items.list_price', {'$subtract':[1,'$order_items.discount']}]}}}])";
      NativeQuery<Order> query = session.createNativeQuery(mongo, Order.class);
      List<Order> list = query.getResultList();
      tx.commit();
      return list;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return null;
  }

  // 8) List the invoices with the purchase of products with a discount of 20% or
  // more, sort
  // order day by day. The list includes: Order number, order date, product name
  // discount and total amount.
  public List<Object> getOrdersByDiscount() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    try {
      String mongo = "db.orders.aggregate([{'$unwind':'$order_items'},{'$match':{'order_items.discount':{'$gte':0.2}}},{'$sort':{'order_date':-1}},{'$lookup':{'from':'products', 'localField':'order_items.product_id', 'foreignField':'_id', 'as':'products'}},{'$unwind':'$products'},{'$addFields':{'product_name':'$products.product_name'}}, {'$project':{'_id':'$_id','order_date':'$order_date','product_name':'$products.product_name','total':{'$multiply':['$order_items.quantity','$order_items.list_price', {'$subtract':[1,'$order_items.discount']}]}}}])";
      List<Object[]> list = (List<Object[]>) session.createNativeQuery(mongo).getResultList();
      Map<String, Object> map = new HashMap<String, Object>();
      Gson gson = new Gson();
      List<Object> list2 = new ArrayList<>();
      for (Object[] obj : list) {
        map.put("_id", obj[0]);
        map.put("order_date", obj[1]);
        map.put("product_name", obj[2]);
        map.put("total", obj[3]);
        list2.add(gson.toJson(map));
      }

      tx.commit();
      return list2;

    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return null;
  }

  // 14) Calculate the total amount of the order when knowing the order number.
  public double getTotalAmountByOrderNumber(int orderNumber) {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    double total = 0;
    try {
      String mongo = " db.orders.aggregate([{'$match':{'_id':" + orderNumber
          + "}},{'$unwind':'$order_items'},{'$addFields':{'total':{'$multiply':['$order_items.quantity','$order_items.list_price', {'$subtract':[1,'$order_items.discount']}]}}},{'$group':{'_id':'$_id','total':{'$sum':'$total'}}}])";
      List<Object[]> order = (List<Object[]>) session.createNativeQuery(mongo).getResultList();
      total = Double.parseDouble(order.get(0)[1].toString());
      tx.commit();
      return total;
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    }
    return 0;
  }

  private Address fromDocument(Object document) {
    Address address = new Address();
    Document doc = (Document) document;
    address.setCity(doc.getString("city"));
    address.setStreet(doc.getString("street"));
    address.setZipCode(doc.getString("zip_code"));
    address.setState(doc.getString("state"));
    return address;
  }

  // 15) Count the number of orders of each customer.
  public Map<Customer, Integer> getOrdersByCustomers() {
    OgmSession session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    Map<Customer, Integer> map = new HashMap<Customer, Integer>();
    try {
      String mongo = "db.customers.aggregate([{'$lookup':{'from':'orders', 'localField':'_id', 'foreignField':'customer_id', 'as':'orders'}},{'$project':{'first_name':1, 'last_name':1,'email':1,'address':1,'sl':{'$size':'$orders'}}}])";
      List<Object[]> list = (List<Object[]>) session.createNativeQuery(mongo).getResultList();
      for (Object[] obj : list) {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(obj[0].toString()));
        customer.setFirstName(obj[1].toString());
        customer.setLastName(obj[2].toString());
        customer.setEmail(obj[3].toString());
        customer.setAddress(fromDocument(obj[4]));
        map.put(customer, Integer.parseInt(obj[5].toString()));
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
