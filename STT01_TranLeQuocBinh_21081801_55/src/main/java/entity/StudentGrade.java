package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "StudentGrade")
public class StudentGrade implements Serializable{
	
	@Id	
	private int enrollmentID;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "CourseID")
	private Course course;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "StudentID")
	private Student student;
	private double grade;
}
