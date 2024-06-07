package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person implements Serializable {
	@Id
	@Column(name = "person_id", nullable = false)
	private int id;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "first_name")
	private String firstName;
	private String email;
	@Column(name = "professional_title")
	private String professionalTitle;

	@OneToMany (mappedBy = "person")
	private List<Reviews> reviews;
	public Person(int id, String lastName, String firstName, String email, String professionalTitle) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.professionalTitle = professionalTitle;
	}

	public Person() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", professionalTitle=" + professionalTitle + "]";
	}

}
