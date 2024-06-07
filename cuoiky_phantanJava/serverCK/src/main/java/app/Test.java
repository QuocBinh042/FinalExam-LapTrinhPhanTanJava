package app;

import java.rmi.RemoteException;

import dao.ChiTietMuonSachDaoService;
import dao.DocGiaDaoService;
import dao.SachDaoService;
import dao.imp.ChiTietMuonSachDaoImp;
import dao.imp.DocGiaDaoImp;
import dao.imp.SachDaoImp;
import enties.DocGia;
import enties.Sach;

public class Test {
	public static void main(String[] args) throws RemoteException {
		ChiTietMuonSachDaoService chiTietMuonSachDaoService = new ChiTietMuonSachDaoImp();
		SachDaoService sachDaoService = new SachDaoImp();
		DocGiaDaoService docGiaDaoService = new DocGiaDaoImp();
		
		Sach sach = new Sach();
		sach.setMaSach("S02");
		sach.setGiaBia(15000);
//		sach.setNamXB(2021);
//		sach.setTacGia("IUH");
//		sach.setTuaSach("Thi cuoi ky phan tan JAVA");
		
		DocGia docGia = new DocGia();
		docGia.setMaDG("DG03");
		
//		System.out.println(chiTietMuonSachDaoService.themChiTietMuonSach(docGia, sach));
		
//		System.out.println(sachDaoService.updateSach(sach));
		
		docGiaDaoService.getDsDocGia("Cuoi Ky HSK JAVA").forEach(e -> System.out.println(e));
		
		
	}
}	
