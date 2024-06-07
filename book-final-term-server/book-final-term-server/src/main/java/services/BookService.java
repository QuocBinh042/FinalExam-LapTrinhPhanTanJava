package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BookDAO;
import entities.Book;
import entities.Review;
import entities.ReviewId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class BookService implements BookDAO {

	private EntityManager em;

	public BookService(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<Book> listRatedBooks(String author, int rating) {
//		String sql = "SELECT b.ISBN, b.name, b.num_of_pages, b.price, b.publish_year, b.publisher_id "
//				+ "FROM books b JOIN reviews r ON b.ISBN = r.ISBN "
//				+ "JOIN books_authors ba ON b.ISBN = ba.ISBN "
//				+ "WHERE ba.author LIKE :author AND r.rating >= :rating";
		
		
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
//
//		Root<Book> bookRoot = cq.from(Book.class);
//		
//		Join<>
		
//		Query query = em.createNativeQuery(sql, Book.class);
		
		Query query = em.createNamedQuery("listRatedBooks");
		query.setParameter("author", author);
		query.setParameter("rating", rating);

		List<Book> result = query.getResultList();

		return result;
	}

	@Override
	public Map<String, Long> countBooksByAuthor() {
		String sql = "SELECT ba.author, COUNT(DISTINCT bt.ISBN) "
				+ "FROM books b JOIN book_translations bt ON b.ISBN = bt.ISBN "
				+ "JOIN books_authors ba ON b.ISBN = ba.ISBN " + "GROUP BY ba.author " + "ORDER BY ba.author ";

		Query query = em.createNativeQuery(sql);

		List<Object[]> result = query.getResultList();

		Map<String, Long> resultMap = new HashMap<>();

		for (Object[] objects : result) {

			String key = (String) objects[0];
			Long value = ((Number) objects[1]).longValue();

			resultMap.put(key, value);
		}

		return resultMap;
	}

	@Override
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		if (!(rating >= 1 && rating <= 5) || comment.equals("") || comment == null) {
			return false;
		}

		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();

			ReviewId reviewID = new ReviewId(Integer.valueOf(readerID), isbn);
			Review review = em.find(Review.class, reviewID);

			if (review == null) {
				return false;
			}

			review.setComment(comment);
			review.setRating(rating);

			em.merge(review);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
