package app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.imp.ChiTietMuonSachDaoImp;
import dao.imp.DocGiaDaoImp;
import dao.imp.SachDaoImp;

public class Server {
	public static void main(String[] args) throws NamingException, RemoteException {
		/**
		 * coder by Le Quoc Phong 
		 */
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.security.policy", "rmi/policy.policy");

		LocateRegistry.createRegistry(8080);
		System.out.println("Server started on port 8080.");
		
		 Context ctx = new InitialContext(env);
		 
		 ChiTietMuonSachDaoImp chiTietMuonSachDaoImp = new ChiTietMuonSachDaoImp();
		 DocGiaDaoImp docGiaDaoImp = new DocGiaDaoImp();
		 SachDaoImp sachDaoImp = new SachDaoImp();
		 
		 
		 ctx.bind("rmi://localhost:8080/chiTietMuonSach", chiTietMuonSachDaoImp);
		 ctx.bind("rmi://localhost:8080/docGia", docGiaDaoImp);
		 ctx.bind("rmi://localhost:8080/sach", sachDaoImp);
		 System.out.println("Service has bound to rmiregistry");
	}
}
