package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff implements Serializable {

  @Id
  private int id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String email;
  private String phone;
  private int active;
  @ManyToOne
  @JoinColumn(name = "manager_id")
  private Staff staff;

  public Staff() {
  }

  public Staff(int id) {
    this.id = id;
  }

  public Staff(int id, String firstName, String lastName, String email, String phone, int active) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.active = active;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  @Override
  public String toString() {
    return "Staff [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
        + phone + ", active=" + active + ", staff=" + staff + "]";
  }

}
