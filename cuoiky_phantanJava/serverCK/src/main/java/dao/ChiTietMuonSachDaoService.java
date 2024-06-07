package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import enties.DocGia;
import enties.Sach;

public interface ChiTietMuonSachDaoService extends Remote{
	public boolean themChiTietMuonSach(DocGia docGia, Sach sach)throws RemoteException;
}
