package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.ItemDAO;
import dao.impl.ItemImpl;
import entity.Item;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1801)){
			System.out.println("Server is running on port 1801");
			while (true) {
				Server tmp = new Server();
				Socket client = server.accept();
				Thread thread = new Thread(tmp.new ClientHanler(client));
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public class ClientHanler implements Runnable  {
		private Socket socket;
		private ItemDAO itemDAO;
		public ClientHanler(Socket socket) {
			this.socket = socket;
			this.itemDAO = new ItemImpl();
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
					String supplierName = in.readUTF();
					List<Item> listItems = itemDAO.listItems(supplierName);
					out.writeObject(listItems);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
}
