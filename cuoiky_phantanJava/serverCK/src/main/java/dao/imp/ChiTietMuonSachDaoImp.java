package dao.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ChiTietMuonSachDaoService;
import db.DatabaseConnection;
import enties.ChiTietMuonSach;
import enties.DocGia;
import enties.Sach;

public class ChiTietMuonSachDaoImp extends UnicastRemoteObject implements ChiTietMuonSachDaoService{
	private Session session;

	public ChiTietMuonSachDaoImp()  throws RemoteException{
		session = DatabaseConnection.getInstance().getSessionFactory().openSession();
	}
	
	@Override
	public boolean themChiTietMuonSach(DocGia docGia, Sach sach) throws RemoteException{
		Calendar cal = Calendar.getInstance();
		Transaction transaction = session.beginTransaction();
		ChiTietMuonSach chiTietMuonSach = new ChiTietMuonSach();
		try {
			chiTietMuonSach.setDocGia(docGia);
			chiTietMuonSach.setSach(sach);
			chiTietMuonSach.setNgayTra(null);
			chiTietMuonSach.setNgayMuon(cal.getTime());
			
			if(sach.getNamXB() > 2015) {
				chiTietMuonSach.setTienCoc(sach.getGiaBia()*50/100);
			}else {
				chiTietMuonSach.setTienCoc(sach.getGiaBia());
			}
			
			session.persist(chiTietMuonSach);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

}
