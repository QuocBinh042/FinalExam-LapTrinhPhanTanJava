package dao;

import java.util.List;
import java.util.Map;

import entity.Book;

public interface BookDAO {
	public List<Book> listRatedBooks(String author, int rating);
	public Map<String, Long> countBooksByAuthor();
}
