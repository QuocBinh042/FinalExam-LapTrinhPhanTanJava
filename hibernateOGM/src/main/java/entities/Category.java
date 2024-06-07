package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
  @Id
  private int id;
  @Column(name = "category_name")
  private String name;

  public Category() {
  }

  public Category(int id, String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "Category [id=" + id + ", name=" + name + "]";
  }

}
