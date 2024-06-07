package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dao.BookDAO;
import dao.impl.BookImpl;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1801)) {
			System.out.println("Server is running on port 1801");
			Server tmp = new Server();
			Socket client = server.accept();
			Thread thread = new Thread(tmp.new ClientHandler(client));
			thread.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public class ClientHandler implements Runnable {
		private Socket socket;
		private BookDAO bookDAO;

		public ClientHandler(Socket socket) {
			this.socket = socket;
			this.bookDAO = new BookImpl();
		}

		@Override
		public void run() {
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				int choose = in.readInt();
				switch (choose) {
				case 1:
					
					
					break;

				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
