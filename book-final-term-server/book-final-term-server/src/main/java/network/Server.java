package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ServerSocket serverSocket;
	private ExecutorService executor;
	private int port;
	
	public Server(int port) {
		super();
		this.executor = Executors.newFixedThreadPool(10);
		this.port = port;
	}
	
	public static void main(String[] args) {
		Server server = new Server(8123);
		server.start();
	}
	
	public void start() {
		boolean isRunning = true;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Listening on port : "+ port);
			
			while(isRunning) {
				Socket clientSocket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				
				ClientHandler handler = new ClientHandler(out, in);
				executor.submit(handler);
			}
			
		} catch (IOException e) {
			isRunning = false;
			e.printStackTrace();
		}
	}
	
}
