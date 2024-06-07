package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {
  private String address;
  private int age;
  private String firstName;
  private String gender;
  @Id
  private String id;
  private String lastName;
  private String phone;
  @ElementCollection
  private List<Treatment> treatments;

  public Patient() {
  }

  public Patient(String address, int age, String firstName, String gender, String id, String lastName, String phone) {
    this.address = address;
    this.age = age;
    this.firstName = firstName;
    this.gender = gender;
    this.id = id;
    this.lastName = lastName;
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Treatment> getTreatments() {
    return treatments;
  }

  public void setTreatments(List<Treatment> treatments) {
    this.treatments = treatments;
  }

  @Override
  public String toString() {
    return "Patient [address=" + address + ", age=" + age + ", firstName=" + firstName + ", gender=" + gender + ", id="
        + id + ", lastName=" + lastName + ", phone=" + phone + ", treatments=" + treatments + "]";
  }

}
