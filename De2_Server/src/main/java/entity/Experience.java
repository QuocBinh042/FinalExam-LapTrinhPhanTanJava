package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "experiences")
public class Experience implements Serializable {
	@Id
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "from_date")
	private LocalDate fromDate;
	@Column(name = "to_date")
	private LocalDate toDate;
	private String description;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "position_id")
	private Position position;

	public Experience() {
		super();
	}

	public Experience(String companyName, LocalDate fromDate, LocalDate toDate, String description, Candidate candidate,
			Position position) {
		super();
		this.companyName = companyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
		this.candidate = candidate;
		this.position = position;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Experience [companyName=" + companyName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", description=" + description + ", candidate=" + candidate + ", position=" + position + "]";
	}

}
