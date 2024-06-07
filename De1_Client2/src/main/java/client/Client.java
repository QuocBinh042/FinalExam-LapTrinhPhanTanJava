package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Item;

public class Client {
	public static void main(String[] args) {
		try (Socket client = new Socket("DESKTOP-K2I7FKM", 1801); Scanner sc = new Scanner(System.in)) {
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			System.out.println("Connected to server");
			int choose = 0;
			while (true) {
				System.out.println("Enter choose");
				choose = sc.nextInt();
				out.writeInt(choose);
				out.flush();
				switch (choose) {
				case 1:

					break;
				case 2: 
					sc.nextLine();
					System.out.println("Enter supplierName: ");
					String supplierName = sc.nextLine();
					out.writeUTF(supplierName);
					out.flush();					
					List<Item> l =  (List<Item>) in.readObject();
					l.forEach(System.out::println);
					
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
