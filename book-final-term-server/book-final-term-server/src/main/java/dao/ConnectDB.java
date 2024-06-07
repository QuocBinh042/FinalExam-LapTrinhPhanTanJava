package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectDB {
	public EntityManagerFactory factory;
	public EntityManager manager;
	
	public static ConnectDB instance = new ConnectDB();
	
	private ConnectDB() {
		factory = Persistence.createEntityManagerFactory("myPersistenceUnit");
		manager = factory.createEntityManager();
	}
	
	public void close() {
		manager.close();
		factory.close();
	}

}
