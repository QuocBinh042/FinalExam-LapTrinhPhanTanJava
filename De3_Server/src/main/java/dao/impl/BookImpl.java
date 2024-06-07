package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BookDAO;
import entity.Book;
import entity.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BookImpl implements BookDAO {
	private EntityManager em;

	public BookImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Book> listRatedBooks(String authors, int rating) {
		List<Book> books = new ArrayList<>();
		String sql = "select b.ISBN from books b join reviews r on r.ISBN=b.ISBN "
				+ "join books_authors ba on ba.ISBN=r.ISBN " + "where ba.author=:authors and r.rating>=:rating";

		List<String> list = em.createNativeQuery(sql).setParameter("authors", authors).setParameter("rating", rating)
				.getResultList();
		for (String isbn : list) {
			Book book = em.find(Book.class, isbn);
			books.add(book);
		}
		return books;

	}

	@Override
	public Map<String, Long> countBooksByAuthor() {
		String sql = "SELECT ba.author, COUNT(*) AS book_count "
				+ "FROM book_translations bt "
				+ "JOIN books_authors ba ON bt.ISBN = ba.ISBN "
				+ "GROUP BY ba.author ";

		List<Object[]> results = em.createNativeQuery(sql, Object[].class).getResultList();

		Map<String, Long> bookCountsByAuthor = new HashMap<String, Long>();
		for (Object[] result : results) {
			String author = (String) result[0];
			Long bookCount = ((Number) result[1]).longValue();
			bookCountsByAuthor.put(author, bookCount);
		}

		return bookCountsByAuthor;
	}

	public static void main(String[] args) {
		BookImpl b = new BookImpl();
		List<Book> l = b.listRatedBooks("Robert C. Martin", 0);
//		l.forEach(System.out::println);
		Map<String, Long> l2 = b.countBooksByAuthor();
		l2.forEach((String, Long) -> {
			System.out.println(String + " " + Long);
		});
	}

}
