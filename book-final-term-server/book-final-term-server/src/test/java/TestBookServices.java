import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dao.ConnectDB;
import entities.Book;
import jakarta.persistence.EntityManager;
import services.BookService;

class TestBookServices {

	EntityManager em = ConnectDB.instance.manager;
	BookService service = new BookService(em);
	
	@Test
	void testListRatedBooks() {
		List<Book> result = service.listRatedBooks("Erich Gamma", 3);
		assertEquals(result.size(), 2);
	}

	@Test
	void countBooksByAuthor() {
		Map<String, Long> result = service.countBooksByAuthor();
		assertEquals(result.get("Martin Fowler"), 2);
	}
	
	@Test
	void updateReviews() {
		boolean result = service.updateReviews("978-0201633610", "1", 5, "Nguyen Minh Khang - execllent book !");
		assertTrue(result);
	}
}
