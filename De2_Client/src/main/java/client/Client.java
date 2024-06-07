package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import entity.Position;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("DESKTOP-K2I7FKM", 1234); Scanner sc = new Scanner(System.in)) {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("Connected to Server");
            int choose = 0;

            while (true) {
                choose = sc.nextInt();
                out.writeInt(choose);
                out.flush();
                switch (choose) {
                    case 1:
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.println("Enter candidateID: ");
                        String id = sc.nextLine();
                        out.writeUTF(id);
                        out.flush();
                        Map<Position, Integer> list = (Map<Position, Integer>) in.readObject();
                        list.forEach((position, integer) -> {
                            System.out.println(position.getId() + ": " + integer);
                        });
                        break;
                    default:
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

