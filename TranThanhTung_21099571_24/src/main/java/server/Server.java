package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.FoodDao;
import entity.Food;
import entity.Item;
import entity.Type;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(9571);) {
			System.out.println("Server ready...");
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected");
				System.out.println("Client IP: " + socket.getInetAddress().getHostName());
				
				Server temp = new Server();
				Thread thread = new Thread(temp.new Handler(socket));
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private class Handler implements Runnable {
		private Socket socket;
		private FoodDao foodDao;
		public Handler(Socket socket) {
			this.socket = socket;
			foodDao = new FoodDao();
		}
		
		public void run() {
			try(DataInputStream in = new DataInputStream(socket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
					int number = in.readInt();
					switch(number) {
						case 1 ->{
							System.out.println("Enter Id for food:");
							String id = in.readUTF();
							Food food = new Food(id, "", 0.0, "", false,Type.DESSERT, 10, 10);
							out.writeObject(foodDao.addFood(food));
							System.out.println("Server write object");
							out.flush();
							break;
						}
						case 2 -> {
							System.out.println("Enter supplier name:");
							String name = in.readUTF();
							List<Item> items = foodDao.listItems(name);
							out.writeObject(items);
							out.flush();
							System.out.println("Listed items for supplier: " + name);
							break;
						}
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
