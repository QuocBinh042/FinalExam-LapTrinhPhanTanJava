package entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_translations")
@PrimaryKeyJoinColumn(name = "ISBN")
public class BookTranslation extends Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "translate_name")
	private String translateName;

	private String language;

	public BookTranslation() {
		super();
	}

	public BookTranslation(String translateName, String language) {
		super();
		this.translateName = translateName;
		this.language = language;
	}

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

}
