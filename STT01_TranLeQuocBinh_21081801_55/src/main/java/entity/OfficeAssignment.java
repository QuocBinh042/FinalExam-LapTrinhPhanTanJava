package entity;

import java.io.Serializable;
import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "OfficeAssignment")
public class OfficeAssignment implements Serializable{
	private String location;
	private Timestamp timestamp;
	@Id
	@OneToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "InstructorID")
	private Instructor instructor;
}
