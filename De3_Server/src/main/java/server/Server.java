package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1801)){
			System.out.println("Server is running on port 1801");
			while (true) {
				Socket client = server.accept();
				Server tmp = new Server();
				Thread thread = new Thread(tmp.new ClientHandler(client));
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private class ClientHandler implements Runnable{
		private Socket socket;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
