package entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "person_id")
	private int personId;
	
	@Column(name = "ISBN")
	private String isbn;

	public ReviewId() {
		super();
	}

	public ReviewId(int personId, String bookId) {
		super();
		this.personId = personId;
		this.isbn = bookId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getBookId() {
		return isbn;
	}

	public void setBookId(String bookId) {
		this.isbn = bookId;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(personId, isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ReviewId)) {
			return false;
		}
		ReviewId other = (ReviewId) obj;

		return Objects.equals(personId, other.personId) && Objects.equals(isbn, other.isbn);
	}

}
