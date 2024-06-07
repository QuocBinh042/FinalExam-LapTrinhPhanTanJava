package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import entity.Department;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("KUGA", 8080);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		while (true) {
			out.writeUTF("3");
//			Patient patient = new Patient("HCM", 20, "long", "nam", "PT-120", "nguyen", "0366231860");
//			List<Treatment> treatments = Arrays.asList(
//					new Treatment("kuga", new Date(), new Date(), new Doctor("DT100")),
//					new Treatment("kuga2", new Date(), new Date(), new Doctor("DT100")));
//			patient.setTreatments(treatments);
//			out.writeObject("Dermatologist");
			out.flush();
			try {
				Thread.sleep(1000);

//				boolean rs = in.readBoolean();
				Map<Department, Integer> list = (Map<Department, Integer>) in.readObject();

//				List<Doctor> list = (List<Doctor>) in.readObject();
				if (list != null) {
					System.out.println("Success");
					list.forEach((k, v) -> System.out.println(k + " - soluong: " + v));

				}
//				if (rs) {
//					System.out.println("Success");
//				} else {
//					System.out.println("Faild");
//				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
