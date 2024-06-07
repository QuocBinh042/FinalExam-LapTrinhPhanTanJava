package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "OnlineCourse")
public class OnlineCourse extends Course{
	private String url;
}
