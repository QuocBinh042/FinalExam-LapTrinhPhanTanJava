package entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="book_translations")
public class BookTranslation extends Book implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="translate_name")
	protected String translateName;
	
	protected String language;

	public String getTranslateName() {
		return translateName;
	}

	public void setTranslateName(String translateName) {
		this.translateName = translateName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BookTranslation(String ISBN, String name, int publishYear, int numOfPages, double price, Publisher publisher,
			String translateName, String language) {
		super(ISBN, name, publishYear, numOfPages, price, publisher);
		this.translateName = translateName;
		this.language = language;
	}

	public BookTranslation(String ISBN, String name, int publishYear, int numOfPages, double price,
			Publisher publisher) {
		super(ISBN, name, publishYear, numOfPages, price, publisher);
	}

	@Override
	public String toString() {
		return "BookTranslation [translateName=" + translateName + ", language=" + language + "]";
	}
	
	
	
}
