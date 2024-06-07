package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dao.ConnectDB;
import services.BookService;

public class ClientHandler implements Runnable {

	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ClientHandler(ObjectOutputStream out, ObjectInputStream in) {
		super();
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {
		BookService service = new BookService(ConnectDB.instance.manager);
		boolean isRunning = true;
		while (isRunning) {
			try {
				String request = (String) in.readObject();

				switch (request) {
				case "listRatedBooks":
					String author = (String) in.readObject();
					int rating = (int) in.readObject();

					out.writeObject(service.listRatedBooks(author, rating));
					out.flush();
					break;
				case "countBooksByAuthor":
					out.writeObject(service.countBooksByAuthor());
					out.flush();
					break;
				case "updateReviews":
					String isbn = (String) in.readObject();
					String readerID = (String) in.readObject();
					rating = (int) in.readObject();
					String comment = (String) in.readObject();

					out.writeObject(service.updateReviews(isbn, readerID, rating, comment));
					out.flush();
					break;
				default :
					break;
				}

			} catch (ClassNotFoundException | IOException e) {
				isRunning = false;
				System.out.println("no more request");
//				e.printStackTrace();
			}
		}
	}

}
