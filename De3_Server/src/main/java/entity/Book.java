package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	protected String ISBN;
	protected String name;
	@Column(name = "publish_year")
	protected int publishYear;
	@Column(name = "num_of_pages")
	protected int numberOfPages;
	protected double price;
	@ElementCollection
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author", nullable = false)
	protected Set<String> authors;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publisher_id")
	protected Publisher publisher;

	@OneToMany(mappedBy = "book")
	protected List<Reviews> reviews;

	public Book() {
		super();
	}

	public Book(String iSBN, String name, int publishYear, int numberOfPages, double price, Set<String> authors,
			Publisher publisher, List<Reviews> reviews) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.publishYear = publishYear;
		this.numberOfPages = numberOfPages;
		this.price = price;
		this.authors = authors;
		this.publisher = publisher;
		this.reviews = reviews;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
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

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
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

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", publishYear=" + publishYear + ", numberOfPages="
				+ numberOfPages + ", price=" + price + ", authors=" + authors + ", publisher=" + publisher
				+ ", reviews=" + reviews + "]";
	}

//	@Override
//	public String toString() {
//		return "Book [ISBN=" + ISBN + ", name=" + name + ", publishYear=" + publishYear + ", numberOfPages="
//				+ numberOfPages + ", price=" + price + ", authors=" + authors + ", publisher=" + publisher
//				+ ", reviews=" + reviews + "]";
//	}

	
}
