package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Candidate;
import entities.Certificate;
import entities.Position;

public class client {
 public static void main(String[] args) {
	try(Scanner sc=new Scanner(System.in)) {
		try(Socket socket=new Socket("localhost",6789)) {
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
			int choice =0;
			
			while(true) {
				System.out.println("Nhap lua chon");
				choice = sc.nextInt();
				out.writeInt(choice);
				out.flush();
				switch (choice) {
				case 1:
					sc.nextLine();
					System.out.println("NhapID");
					String candidateID=sc.nextLine();
					out.writeUTF(candidateID);
					out.flush();
					Map<Position, Integer> map= (Map<Position, Integer>) in.readObject();
					map.entrySet().stream().forEach(entry ->{
						System.out.println(entry.getKey().getName()+" : "+entry.getValue());
					});
					break;
				case 2:
					sc.nextLine();
					System.out.println("NhapID");
					String a= sc.nextLine();
					out.writeUTF(a);
					out.flush();
					System.out.println("Nhap tien");
					double b= sc.nextDouble();
					out.writeDouble(b);
					out.flush();
					System.out.println("Nhap tien");
					double c= sc.nextDouble();
					out.writeDouble(c);
					out.flush();
					List<Position> positions=(List<Position>) in.readObject();
					positions.forEach(System.out::println);
					break;
				case 3:
					sc.nextLine();
					Map<Candidate, Long> map1= (Map<Candidate, Long>) in.readObject();
					map1.entrySet().stream().forEach(entry ->{
						System.out.println(entry.getKey().getFullName()+" : "+entry.getValue());
					});
					break;
				case 4:
					sc.nextLine();
					Map<Candidate, Position> map2= (Map<Candidate, Position>) in.readObject();
					map2.entrySet().stream().forEach(entry-> {
						System.out.println(entry.getKey().getFullName()+" : "+entry.getValue().getName());
					});
					break;
				case 5:
					sc.nextLine();
					System.out.println("NhapID");
					String cadiID= sc.nextLine();
					out.writeUTF(cadiID);
					out.flush();
					System.out.println("NhapTen");
					String name=sc.nextLine();
					out.writeUTF(name);
					out.flush();
					boolean dungsai=in.readBoolean();
					 if(dungsai==true) {
						 System.out.println("Them Thanh Cong");
					 }else {
						 System.out.println("That bai");
					 }
					 break;
				case 6:
					sc.nextLine();
					Map<Candidate, Set<Certificate>> map3= (Map<Candidate, Set<Certificate>>) in.readObject();
					map3.entrySet().stream().forEach(x->{
						System.out.println(x.getKey().getFullName());
						for ( Certificate certificate : x.getValue()) {
							System.out.println(certificate.toString());
						}
					});
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
