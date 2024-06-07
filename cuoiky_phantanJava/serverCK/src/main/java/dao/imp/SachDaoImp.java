package dao.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SachDaoService;
import db.DatabaseConnection;
import enties.Sach;

public class SachDaoImp extends UnicastRemoteObject implements SachDaoService{
	private Session session;

	public SachDaoImp()  throws RemoteException{
		session = DatabaseConnection.getInstance().getSessionFactory().openSession();
	}
	@Override
	public boolean updateSach(Sach sach) throws RemoteException{
		Transaction transaction = session.beginTransaction();
		Calendar cal = Calendar.getInstance();
		try {
			int namXBs = cal.get(Calendar.YEAR);
			if(sach.getNamXB() > namXBs) {
				System.out.println("Nam xuat ban phai be hon = naam hien tai");
				return false;
			}
			if(sach.getGiaBia() <= 0) {
				System.out.println("Gia bia phai la so duong");
				return false;
			}
			
			session.merge(sach);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

}
