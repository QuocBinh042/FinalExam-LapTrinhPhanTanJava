package network;

import java.util.List;
import java.util.Map;

import entities.Book;

public class Client {
	public static void main(String[] args) {
		ClientRequest clientRequest = new ClientRequest(8123);
		
		List<Book> result = clientRequest.listRatedBooks("Erich Gamma", 3);
		for (Book book : result) {
			System.out.println(book.getName());
		}
		
		Map<String, Long> mapResult = clientRequest.countBooksByAuthor();
		for (String key : mapResult.keySet()) {
			System.out.println(key + " - " + mapResult.get(key));
		}
		
		boolean boolResult = clientRequest.updateReviews("978-0201633610", "1", 5, "Nguyen Minh Khang - execllent book !");
		System.out.println(boolResult);
	}
}
