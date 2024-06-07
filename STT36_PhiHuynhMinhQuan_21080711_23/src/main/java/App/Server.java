package App;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.EntityManagerFactoryUtil;

import jakarta.persistence.EntityManager;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket server= new ServerSocket(6789);){
			ExecutorService executorService=Executors.newCachedThreadPool();
			System.out.println("Server listening on port 6789...");
			while(true) {
				
				Socket socket=server.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
				executorService.execute(new clientHandler(socket));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
class clientHandler implements Runnable {
	private Socket socket;
	private EntityManagerFactoryUtil entityManagerFactory;
	private EntityManager entityManager;
	public clientHandler(Socket socket) {
		this.socket = socket;
		this.entityManagerFactory =new  EntityManagerFactoryUtil();
		this.entityManager = entityManagerFactory.getEnManager();
	}

	@Override
	public void run() {
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

} 