package entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReviewId id;
	
	@ManyToOne
	@JoinColumn(name = "ISBN", insertable=false, updatable=false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "person_id", insertable=false, updatable=false)
	private Person person;
	
	private int rating;
	
	private String comment;

	public Review() {
		super();
	}

	public Review(ReviewId id, Book book, Person person, int rating, String comment) {
		super();
		this.id = id;
		this.book = book;
		this.person = person;
		this.rating = rating;
		this.comment = comment;
	}

	public ReviewId getId() {
		return id;
	}

	public void setId(ReviewId id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
