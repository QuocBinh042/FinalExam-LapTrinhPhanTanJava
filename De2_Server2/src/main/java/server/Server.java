package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dao.CandidateDAO;
import dao.impl.CandidateImpl;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1801)) {
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
		private CandidateDAO candidateDAO;

		public ClientHandler(Socket socket) {
			this.socket = socket;
			this.candidateDAO = new CandidateImpl();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				int choose = 0;

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
