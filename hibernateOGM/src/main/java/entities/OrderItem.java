package entities;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class OrderItem implements Serializable {

  private int quantity;
  @Column(name = "list_price")
  private double price;
  private double discount;
  private double lineTotal;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public OrderItem() {
  }

  public OrderItem(int quantity, double price, double discount, double lineTotal, Product product) {
    this.quantity = quantity;
    this.price = price;
    this.discount = discount;
    this.lineTotal = quantity * price * (1 - discount);
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public double getLineTotal() {
    return lineTotal;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public String toString() {
    return "OrderItem [quantity=" + quantity + ", price=" + price + ", discount=" + discount + ", lineTotal="
        + lineTotal + "]";
  }

}
