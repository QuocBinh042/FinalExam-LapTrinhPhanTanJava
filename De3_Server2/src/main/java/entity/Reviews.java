package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews implements Serializable {
	private int rating;
	private String comment;
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "ISBN")
	private Book book;
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "person_id")
	private Person person;
	public Reviews(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}

	public Reviews() {
		super();
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

	@Override
	public String toString() {
		return "Reviews [rating=" + rating + ", comment=" + comment + "]";
	}

}
