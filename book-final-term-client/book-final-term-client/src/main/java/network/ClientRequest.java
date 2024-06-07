package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Book;

public class ClientRequest {
	private static final String HOST = "localhost";
	private Socket server = null;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int port;

	public ClientRequest(int port) {
		super();
		this.port = port;
	}

	public void connect() {
		if (server == null) {
			try {
				server = new Socket(HOST, port);
				out = new ObjectOutputStream(server.getOutputStream());
				in = new ObjectInputStream(server.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void disconnect() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Book> listRatedBooks(String author, int rating) {
		connect();
		
		List<Book> result = new ArrayList<>();

		try {
			out.writeObject("listRatedBooks");
			out.writeObject(author);
			out.writeObject(rating);
			
			out.flush();
			
			result = (List<Book>) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Map<String, Long> countBooksByAuthor() {
		connect();
		
		Map<String, Long> result = new HashMap<>();
		try {
			out.writeObject("countBooksByAuthor");
			
			out.flush();
			
			result = (Map<String, Long>) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		connect();
		
		boolean result = false;

		try {
			out.writeObject("updateReviews");
			out.writeObject(isbn);
			out.writeObject(readerID);
			out.writeObject(rating);
			out.writeObject(comment);
			
			out.flush();
			
			result = (boolean) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
