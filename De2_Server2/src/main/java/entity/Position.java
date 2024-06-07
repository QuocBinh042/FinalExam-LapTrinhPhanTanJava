package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position implements Serializable {
	@Id
	@Column(name = "position_id", nullable = false)
	private String id;
	private String name;
	private String description;
	private double salary;
	private int number;
	@OneToMany (mappedBy = "position")
	private List<Experience> experiences;
	
	@OneToMany (mappedBy = "position")
	private List<Candidate> candidates;
	public Position(String id, String name, String description, double salary, int number) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
		this.number = number;
	}

	public Position() {
		super();
	}

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

	@Override
	public String toString() {
		return "Postition [id=" + id + ", name=" + name + ", description=" + description + ", salary=" + salary
				+ ", number=" + number + "]";
	}

}
