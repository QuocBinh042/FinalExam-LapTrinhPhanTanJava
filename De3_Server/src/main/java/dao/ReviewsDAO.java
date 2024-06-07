package dao;

public interface ReviewsDAO {
	public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
