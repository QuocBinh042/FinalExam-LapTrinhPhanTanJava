package client;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try (Socket client = new Socket("DESKTOP-K2I7FKM", 1801); Scanner sc = new Scanner(System.in)) {
			DataInputStream in = new DataInputStream(client.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

			System.out.println("Connected to server");

			int choose = 0;
			while (true) {
				System.out.println("Enter choose");
				sc.nextLine();
				choose = sc.nextInt();
				out.writeInt(choose);
				out.flush();
				switch (choose) {
				case 1:

					break;

				default:
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
