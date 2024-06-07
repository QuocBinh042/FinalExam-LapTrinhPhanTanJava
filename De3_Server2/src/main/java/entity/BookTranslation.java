package entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
	@Column(name = "translate_name", columnDefinition = "nvarchar(250)")
	private String translateName;
	private String language;

	public BookTranslation(String iSBN, String name, int publishYear, int numOfPages, double price, Set<String> authors,
			Publisher publisher, String translateName, String language) {
		super(iSBN, name, publishYear, numOfPages, price, authors, publisher);
		this.translateName = translateName;
		this.language = language;
	}

	public BookTranslation(String iSBN, String name, int publishYear, int numOfPages, double price, Set<String> authors,
			Publisher publisher) {
		super(iSBN, name, publishYear, numOfPages, price, authors, publisher);
	}

	public String getTranslateName() {
		return translateName;
	}

	public void setTranslateName(String translateName) {
		this.translateName = translateName;
	}

	public String getLanguge() {
		return language;
	}

	public void setLanguge(String languge) {
		this.language = languge;
	}

	@Override
	public String toString() {
		return "BookTranslation [translateName=" + translateName + ", languge=" + language + "]";
	}

}
