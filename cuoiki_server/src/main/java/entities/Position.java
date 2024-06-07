package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="positions")
public  class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "position_id")
	protected String id;
	
	protected String name;
	protected String description;
	protected double salary;
	protected int number;
	
	@OneToMany(mappedBy = "position",fetch = FetchType.EAGER)
	private Set<Candidate> candidates;
	
	@OneToMany(mappedBy = "positions",fetch = FetchType.EAGER)
	private Set<Experience> experiences;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	public Position() {
		super();
	}

	public Position(String id, String name, String description, double salary, int number) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", name=" + name + ", description=" + description + ", salary=" + salary
				+ ", number=" + number  ;
	}
	
	
}
