package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import dao.CandidateDAO;
import dao.PositionDAO;
import dao.impl.CandidateImpl;
import dao.impl.PositionImpl;
import entity.Position;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1234)) {
			System.out.println("Server is running on port 1234");
			while (true) {
				Socket client = server.accept();
				Server tmp = new Server();
				Thread thread = new Thread(tmp.new ClientHandler(client));
				thread.start();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private class ClientHandler implements Runnable {
		private Socket socket;
		private PositionDAO positionDAO;
		private CandidateDAO candidateDAO;

		public ClientHandler(Socket socket) {
			super();
			this.socket = socket;
			this.positionDAO = new PositionImpl();
			this.candidateDAO = new CandidateImpl();
		}

		@Override
		public void run() {
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

				int choose = in.readInt();
				switch (choose) {
				case 1:

					break;
				case 2:
					String id = in.readUTF();
					Map<Position, Integer> list = positionDAO.listYearsOfExperienceByPosition(id);
					out.writeObject(list);
					break;

				case 3:

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
