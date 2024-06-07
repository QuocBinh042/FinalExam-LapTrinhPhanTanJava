package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.ogm.options.shared.IndexOption;
import org.hibernate.ogm.options.shared.IndexOptions;

@Entity
@Table(name = "doctors", indexes = { @Index(name = "doctors_specialty", columnList = "specialty") })
@IndexOptions(@IndexOption(forIndex = "doctors_specialty", options = "{text:true}"))
public class Doctor implements Serializable {
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<String> certifications;
  private String email;
  private String firstName;
  @Id
  private String id;
  private String lastName;
  private String phone;
  private String specialty;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  public Doctor() {
  }

  public Doctor(String id) {
    this.id = id;
  }

  public Doctor(Set<String> certifications, String email, String firstName, String id, String lastName, String phone,
      String specialty) {
    this.certifications = certifications;
    this.email = email;
    this.firstName = firstName;
    this.id = id;
    this.lastName = lastName;
    this.phone = phone;
    this.specialty = specialty;
  }

  public Set<String> getCertifications() {
    return certifications;
  }

  public void setCertifications(Set<String> certifications) {
    this.certifications = certifications;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Doctor [certifications=" + certifications + ", email=" + email + ", firstName=" + firstName + ", id=" + id
        + ", lastName=" + lastName + ", phone=" + phone + ", specialty=" + specialty + ", department=" + department
        + "]";
  }

}
