package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "people")
public class Person implements Serializable{
	@Id
	@Column (name = "person_id", nullable = false)
	private int id;
	@Column (name = "last_name")
	private String lastName;
	@Column (name = "first_name")
	private String firstName;
	private String email;
	@Column (name = "professional_title")
	private String professionalTitle;
	
	@OneToMany (mappedBy = "person")
	private List<Reviews> reviews;
}
