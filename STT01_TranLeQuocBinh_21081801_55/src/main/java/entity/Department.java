package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Department")
public class Department implements Serializable{
	@Id
	@Column (name = "DepartmentID", nullable = false)
	private int id;
	private int administrator;
	private double budget;
	private String name;
	private LocalDateTime startDate;
	
	@OneToMany (mappedBy = "department")
	private List<Course> courses;
	
}
