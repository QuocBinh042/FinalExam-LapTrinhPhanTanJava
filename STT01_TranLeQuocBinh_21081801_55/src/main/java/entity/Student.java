package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue ("Student")
public class Student extends Person implements Serializable {
	private LocalDateTime enrollmentDate;
	
	@OneToMany (mappedBy = "student")
	private Set<StudentGrade> studentGrades;
}
