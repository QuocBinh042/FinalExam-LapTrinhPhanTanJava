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
	@Column(name = "company_name", nullable = false)
	private String companyName;
	@Column(name = "from_date")
	private LocalDate fromDate;
	@Column(name = "to_date")
	private LocalDate toDate;
	private String description;
	
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "candidate_id", nullable = false)
	private Candidate candidate;
	
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "position_id", nullable = false)
	private Position position;
	
	public Experience(String companyName, LocalDate fromDate, LocalDate toDate, String description) {
		super();
		this.companyName = companyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
	}

	public Experience() {
		super();
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

	@Override
	public String toString() {
		return "Experience [companyName=" + companyName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", description=" + description + "]";
	}

}
