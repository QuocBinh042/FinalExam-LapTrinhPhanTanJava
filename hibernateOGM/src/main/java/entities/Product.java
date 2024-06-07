package entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.ogm.options.shared.IndexOption;
import org.hibernate.ogm.options.shared.IndexOptions;

@Entity
@Table(name = "products", indexes = { @Index(name = "product_name_text", columnList = "product_name") })
@IndexOptions(@IndexOption(forIndex = "product_name_text", options = "{text:true}"))
public class Product implements Serializable {
  @Id
  private int id;
  @Column(name = "product_name")
  private String name;

  @Column(name = "model_year")
  private int modelYear;

  @Column(name = "list_price")
  private double price;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Product() {
  }

  public Product(int id, String name, int modelYear, double price) {
    this.id = id;
    this.name = name;
    this.modelYear = modelYear;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getModelYear() {
    return modelYear;
  }

  public void setModelYear(int modelYear) {
    this.modelYear = modelYear;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", modelYear=" + modelYear + ", price=" + price + ", brand=" + brand
        + ", category=" + category + "]";
  }

}
