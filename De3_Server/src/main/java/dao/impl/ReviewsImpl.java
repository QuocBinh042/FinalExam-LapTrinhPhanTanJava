package dao.impl;

import dao.ReviewsDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ReviewsImpl implements ReviewsDAO {
	private EntityManager em;

	public ReviewsImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		try {
			String sql = "UPDATE Reviews " + "SET rating = ?, comment = ? " + "WHERE ISBN = ? AND person_id = ? "
					+ "AND rating BETWEEN 1 AND 5 " + "AND comment IS NOT NULL AND comment != ''";

			Query query = em.createNativeQuery(sql);
			query.setParameter(1, rating);
			query.setParameter(2, comment);
			query.setParameter(3, isbn);
			query.setParameter(4, readerID);

			int rowsUpdated = query.executeUpdate();

			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		ReviewsImpl r = new ReviewsImpl();
		String isbn1 = "978-3-16-148410-0";
		String readerID1 = "reader123";
		int newRating1 = 4;
		String newComment1 = "This book is amazing!";
		boolean result1 = r.updateReviews(isbn1, readerID1, newRating1, newComment1);
		System.out.println("Test case 1: Update existing review - Result: " + result1);
	}
}
