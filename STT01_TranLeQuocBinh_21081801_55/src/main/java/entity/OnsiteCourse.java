package entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "OnsiteCourse")
public class OnsiteCourse extends Course{
	private String days;
	private String location;
	private LocalDateTime time;
}
