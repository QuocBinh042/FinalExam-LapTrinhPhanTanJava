package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import enties.Sach;

public interface SachDaoService extends Remote{
	public boolean updateSach(Sach sach)throws RemoteException;
}
