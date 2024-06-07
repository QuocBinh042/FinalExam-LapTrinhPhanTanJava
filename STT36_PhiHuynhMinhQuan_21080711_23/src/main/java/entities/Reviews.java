package entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Reviews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="person_id")
	protected Person people;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ISBN")
	protected Book book;
	
	protected String comment;
	
	protected int rating;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((people == null) ? 0 : people.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reviews other = (Reviews) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (people == null) {
			if (other.people != null)
				return false;
		} else if (!people.equals(other.people))
			return false;
		return true;
	}

	public Person getPeople() {
		return people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Reviews(Person people, Book book, String comment, int rating) {
		super();
		this.people = people;
		this.book = book;
		this.comment = comment;
		this.rating = rating;
	}

	public Reviews() {
		super();
	}

	@Override
	public String toString() {
		return "Reviews [people=" + people + ", book=" + book + ", comment=" + comment + ", rating=" + rating + "]";
	}

	

	
	
	
}
