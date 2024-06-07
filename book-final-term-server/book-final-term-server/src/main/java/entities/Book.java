package entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "listRatedBooks", query = "SELECT b FROM Book b JOIN b.reviews r "
											+ "WHERE :author MEMBER OF b.authors AND r.rating >= :rating ")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ISBN")
	protected String isbn;

	protected String name;

	@Column(name = "publish_year")
	protected int publishYear;

	@Column(name = "num_of_pages")
	protected int numOfPages;

	protected double price;

	@ElementCollection
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author", nullable = false)
	protected Set<String> authors;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	protected Publisher publisher;

	@OneToMany(mappedBy = "book")
	protected Set<Review> reviews;

	public Book() {
		super();
	}

	public Book(String isbn, String name, int publishYear, int numOfPages, double price, Set<String> authors,
			Publisher publisher, Set<Review> reviews) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.publishYear = publishYear;
		this.numOfPages = numOfPages;
		this.price = price;
		this.authors = authors;
		this.publisher = publisher;
		this.reviews = reviews;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public int getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
