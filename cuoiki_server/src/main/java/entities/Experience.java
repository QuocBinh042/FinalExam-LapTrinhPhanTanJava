package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="experiences")
public class Experience implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="position_id")
	private Position positions;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="candidate_id")
	private Candidate candidates;
	
	@Column(name="from_date")
	private LocalDate fromDate;
	
	@Column(name="to_date")
	private LocalDate toDate;
	
	@Id
	@Column(name="company_name")
	private String companyName;
	
	private String description;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(candidates, positions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experience other = (Experience) obj;
		return Objects.equals(candidates, other.candidates) && Objects.equals(positions, other.positions);
	}

	public Position getPositions() {
		return positions;
	}

	public void setPositions(Position positions) {
		this.positions = positions;
	}

	public Candidate getCandidates() {
		return candidates;
	}

	public void setCandidates(Candidate candidates) {
		this.candidates = candidates;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Experience(Position positions, Candidate candidates, LocalDate fromDate, LocalDate toDate,
			String companyName, String description) {
		super();
		this.positions = positions;
		this.candidates = candidates;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.companyName = companyName;
		this.description = description;
	}

	public Experience() {
		super();
	}

	@Override
	public String toString() {
		return "Experience [positions=" + positions + ", candidates=" + candidates + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", companyName=" + companyName + ", description=" + description + "]";
	}
	
}