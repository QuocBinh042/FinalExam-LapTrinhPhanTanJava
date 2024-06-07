package dao.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import dao.DocGiaDaoService;
import db.DatabaseConnection;
import enties.DocGia;

public class DocGiaDaoImp extends UnicastRemoteObject implements DocGiaDaoService{
	private Session session;

	public DocGiaDaoImp()  throws RemoteException{
		session = DatabaseConnection.getInstance().getSessionFactory().openSession();
	}
	
	@Override
	public List<DocGia> getDsDocGia(String tuaSach) throws RemoteException{
		Transaction transaction = session.beginTransaction();
		List<DocGia> ds = new ArrayList<DocGia>();
		try {
			String sql="SELECT DocGia.MaDG, DocGia.DienThoai, DocGia.Email, DocGia.HoTenDG, COUNT(DocGia.MaDG) AS SoLanMuon FROM  ChiTietMuonSach INNER JOIN DocGia ON ChiTietMuonSach.MaDG = DocGia.MaDG INNER JOIN Sach ON ChiTietMuonSach.MaSach = Sach.MaSach where Sach.TuaSach=:x group by DocGia.MaDG, DocGia.DienThoai, DocGia.Email, DocGia.HoTenDG having COUNT(DocGia.MaDG) >= 2";
			NativeQuery<DocGia> query = session.createNativeQuery(sql, DocGia.class);
			query.setParameter("x", tuaSach);
			ds = query.getResultList();
			transaction.commit();
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

}
