package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


import enties.DocGia;

public interface DocGiaDaoService extends Remote{
	public List<DocGia> getDsDocGia(String tuaSach)throws RemoteException;
}
