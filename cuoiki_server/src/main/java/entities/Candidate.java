package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="candidates")
public class Candidate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="candidate_id")
	protected String id;
	
	@Column(name="full_name")
	protected String fullName;
	
	@Column(name="year_of_birth")
	protected int yearOfBirth;
	
	protected String gender;
	
	protected String phone;
	
	protected String email;
	
	protected String description;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="position_id")
	private Position position;
	
	@OneToMany(mappedBy = "candidates",fetch = FetchType.EAGER)
	private Set<Experience> experiences;
	
	@OneToMany(mappedBy = "candidate",fetch = FetchType.EAGER)
	private Set<Certificate> certificates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}
	
	public Candidate() {
		super();
	}

	public Candidate(String id, String fullName, int yearOfBirth, String gender, String phone, String email,
			String description, Position position) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.position = position;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", fullName=" + fullName + ", yearOfBirth=" + yearOfBirth + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + ", description=" + description + ", position=" + position
				;
	}
	
	
}
