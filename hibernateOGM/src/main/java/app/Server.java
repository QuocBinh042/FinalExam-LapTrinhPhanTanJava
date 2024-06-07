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

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);

		System.out.println("Server started on port 8080");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println(
					"Client connected from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
			new Thread(new Handle(socket)).start();
		}
	}
}

class Handle implements Runnable {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private CustomerDao cusDao;
	private BrandDao brandDao;
	private CategoryDao cateDao;
	private ProductDao proDao;
	private StaffDao staffDao;
	private OrderDao orderDao;

	public Handle(Socket socket) {
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
			System.out.println("Request: " + request);
			switch (request) {
			case "1":
				out.writeObject(proDao.getProductByModel(2016));
				break;
			case "2":
				out.writeObject(proDao.getProductByCateID(2));
				break;
			case "3":
				out.writeObject(proDao.getProductByCateID(2));
				break;
			case "4":
				out.writeObject(proDao.getProductByCateName("Cruisers Bicycles"));
				break;
			case "5":
				out.writeObject(proDao.getProductByBrandID(1));
				break;
			case "6":
				out.writeObject(proDao.getProductByBrandName("Electra"));
				break;
			case "7":
				out.writeObject(orderDao.getOrdersByProductName("Pure Cycles William 3-Speed - 2016"));
				break;
			case "8":
				out.writeObject(orderDao.getOrdersByDiscount());
				break;
			case "9":
				out.writeObject(cateDao.getCategoriesByProductNumber());
				break;
			case "10":
				out.writeObject(orderDao.getOrdersByCustomers());
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
