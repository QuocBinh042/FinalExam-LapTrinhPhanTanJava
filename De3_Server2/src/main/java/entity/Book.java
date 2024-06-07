package entity;

import java.io.Serializable;
import java.util.HashSet;
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
	@Id
	@Column(nullable = false)
	protected String ISBN;
	@Column(columnDefinition = "nvarchar(255)")
	protected String name;
	@Column(name = "publish_year")
	protected int publishYear;
	@Column(name = "num_of_pages")
	protected int numOfPages;
	protected double price;

	@ElementCollection
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author", nullable = false)
	protected Set<String> authors = new HashSet<String>();
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "publisher_id")
	protected Publisher publisher;

	@OneToMany (mappedBy = "book")
	protected List<Reviews> reviews;
	
	public Book(String iSBN, String name, int publishYear, int numOfPages, double price, Set<String> authors,
			Publisher publisher) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.publishYear = publishYear;
		this.numOfPages = numOfPages;
		this.price = price;
		this.authors = authors;
		this.publisher = publisher;
	}

	public Book() {
		super();
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

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", publishYear=" + publishYear + ", numOfPages=" + numOfPages
				+ ", price=" + price + ", authors=" + authors + ", publisher=" + publisher + "]";
	}

}
