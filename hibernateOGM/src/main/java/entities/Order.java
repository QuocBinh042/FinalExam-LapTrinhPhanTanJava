package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")

public class Order implements Serializable {
  @Id
  private int id;
  @Column(name = "order_status")
  private int orderStatus;
  @Column(name = "order_date")
  private Date orderDate;
  @Column(name = "required_date")
  private Date requiredDate;
  @Column(name = "shipped_date")
  private Date shippedDate;
  @Column(name = "order_total")
  private double orderTotal;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<OrderItem> orderItems;

  public Order() {
  }

  public Order(int id, int orderStatus, Date orderDate, Date requiredDate, Date shippedDate,
      List<OrderItem> orderItems, Customer customer, Staff staff) {
    this.id = id;
    this.orderStatus = orderStatus;
    this.orderDate = orderDate;
    this.requiredDate = requiredDate;
    this.shippedDate = shippedDate;
    this.orderTotal = 0.0;
    this.orderItems = new ArrayList<OrderItem>();
    this.customer = customer;
    this.staff = staff;
  }

  public void addOrderItem(int quantity, double price, double discount, double lineTotal, Product product) {
    OrderItem orderItem = new OrderItem(quantity, price, discount, lineTotal, product);
    this.orderItems.add(orderItem);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getRequiredDate() {
    return requiredDate;
  }

  public void setRequiredDate(Date requiredDate) {
    this.requiredDate = requiredDate;
  }

  public Date getShippedDate() {
    return shippedDate;
  }

  public void setShippedDate(Date shippedDate) {
    this.shippedDate = shippedDate;
  }

  public double getOrderTotal() {
    return orderTotal;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + ", requiredDate="
        + requiredDate + ", shippedDate=" + shippedDate + ", orderTotal=" + orderTotal + ", customer=" + customer
        + ", staff=" + staff + ", orderItems=" + orderItems + "]";
  }

}
