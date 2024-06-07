package entities;

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
@Table(name="certificates")
public class Certificate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="certificate_id")
	protected String id;
	
	protected String name;
	
	protected String organization;
	
	@Column(name="issue_date")
	private LocalDate issueDate;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	public Certificate(String id, String name, String organization, LocalDate issueDate, Candidate candidate) {
		super();
		this.id = id;
		this.name = name;
		this.organization = organization;
		this.issueDate = issueDate;
		this.candidate = candidate;
	}

	public Certificate() {
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", name=" + name + ", organization=" + organization + ", issueDate="
				+ issueDate + ", cadidate=" + candidate.getId() + "]";
	}
	
	
}
