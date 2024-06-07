package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dao.DoctorDao;
import dao.PaitentDao;
import entity.Patient;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8080);
    System.out.println("Server is running...");
    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println(socket.getInetAddress().getHostName() + ":" + socket.getPort());
      new Thread(new Handle(socket)).start();
    }
  }
}

class Handle implements Runnable {
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  private PaitentDao paitentDao;
  private DoctorDao doctorDao;

  public Handle(Socket socket) {
    this.socket = socket;
    paitentDao = new PaitentDao();
    doctorDao = new DoctorDao();
  }

  @Override
  public void run() {
    try {
      in = new ObjectInputStream(socket.getInputStream());
      out = new ObjectOutputStream(socket.getOutputStream());
      String request = in.readUTF();
      System.out.println(request);
      switch (request) {
        case "1":
          Patient patient = (Patient) in.readObject();
          boolean rs = paitentDao.addPaintent(patient);
          out.writeObject(rs);
          break;
        case "2":
          out.writeObject(doctorDao.getDoctorsBySpecialty((String) in.readObject()));
          break;
        case "3":
          out.writeObject(doctorDao.getNumOfDoctorsByDepartments());
          break;
        default:
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
