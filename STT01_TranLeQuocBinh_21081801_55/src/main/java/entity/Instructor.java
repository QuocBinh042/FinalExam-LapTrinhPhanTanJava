package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue ("Instructor")
public class Instructor extends Person implements Serializable{
	private LocalDateTime hireDate;
}
