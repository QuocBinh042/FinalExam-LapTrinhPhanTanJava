package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dao.BrandDao;
import dao.CategoryDao;
import dao.CustomerDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.StaffDao;

public class ServerOGM {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket();

		System.out.println("server started");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
			new Thread(new Handler(socket)).start();

		}
	}
}

class Handler implements Runnable {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private CustomerDao cusDao;
	private BrandDao brandDao;
	private CategoryDao cateDao;
	private ProductDao proDao;
	private StaffDao staffDao;
	private OrderDao orderDao;

	public Handler(Socket socket) {

		this.socket = socket;

		cusDao = new CustomerDao();
		brandDao = new BrandDao();
		cateDao = new CategoryDao();
		proDao = new ProductDao();
		staffDao = new StaffDao();
		orderDao = new OrderDao();
	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			String request = in.readUTF();
			switch (request) {
			case "1":
				out.writeObject(proDao.getProductByModel(2016));
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
