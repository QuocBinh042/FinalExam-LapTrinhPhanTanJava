package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.DocGiaDaoService;

public class Cilent {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		DocGiaDaoService docGiaDaoService = (DocGiaDaoService) Naming.lookup("rmi://localhost:8080/docGia");
		docGiaDaoService.getDsDocGia("Cuoi Ky HSK JAVA").forEach(e -> System.out.println(e));
	}
}
