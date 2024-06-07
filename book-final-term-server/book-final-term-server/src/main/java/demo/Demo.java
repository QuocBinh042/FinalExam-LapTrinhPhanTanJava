package demo;

import java.util.List;

import dao.ConnectDB;
import entities.Book;
import jakarta.persistence.EntityManager;
import services.BookService;

public class Demo {
	public static void main(String[] args) {
		EntityManager em = ConnectDB.instance.manager;
		
		BookService service = new BookService(em);
		
		List<Book> result = service.listRatedBooks("Erich Gamma", 3);
		for (Book book : result) {
			System.out.println(book.getName());
		}
	}
}
