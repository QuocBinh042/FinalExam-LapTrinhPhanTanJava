package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Treatment implements Serializable {
  private String description;
  private Date endDate;
  private Date startDate;
  @ManyToOne
  @JoinColumn(name = "doctorID")
  private Doctor doctor;

  public Treatment() {
  }

  public Treatment(String description, Date endDate, Date startDate, Doctor doctor) {
    this.description = description;
    this.endDate = endDate;
    this.startDate = startDate;
    this.doctor = doctor;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  @Override
  public String toString() {
    return "Treatment [description=" + description + ", endDate=" + endDate + ", startDate=" + startDate + ", doctor="
        + doctor + "]";
  }

}
