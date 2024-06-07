package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import enties.ChiTietMuonSach;
import enties.DocGia;
import enties.Sach;

public class App {
	public static void main(String[] args) {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(ChiTietMuonSach.class)
				.addAnnotatedClass(DocGia.class)
				.addAnnotatedClass(Sach.class)
				.getMetadataBuilder()
				.build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}
}
