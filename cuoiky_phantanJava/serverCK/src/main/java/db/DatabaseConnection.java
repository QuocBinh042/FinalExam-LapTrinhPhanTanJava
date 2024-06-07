package db;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import enties.ChiTietMuonSach;
import enties.DocGia;
import enties.Sach;

public class DatabaseConnection {
	private SessionFactory sessionFactory;
	private static DatabaseConnection databaseConnection = null;

	private DatabaseConnection() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(ChiTietMuonSach.class)
				.addAnnotatedClass(DocGia.class).
				addAnnotatedClass(Sach.class)
				.getMetadataBuilder().build();

		this.sessionFactory = metadata.getSessionFactoryBuilder().build();

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public synchronized static DatabaseConnection getInstance() {
		if (databaseConnection == null)
			databaseConnection = new DatabaseConnection();

		return databaseConnection;
	}
}
