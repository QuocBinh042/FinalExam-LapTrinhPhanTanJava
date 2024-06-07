package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course implements Serializable {
	protected int credits;
	@Id
	@Column(name = "CourseID")
	protected int id;
	protected String title;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DepartmentID")
	protected Department department;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CourseInstructor", joinColumns = @JoinColumn(name = "PersonID"), inverseJoinColumns = @JoinColumn(name = "CourseID"))
	protected Set<Instructor> instructors;
}
