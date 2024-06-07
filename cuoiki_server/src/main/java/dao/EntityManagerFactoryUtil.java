package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	public EntityManagerFactoryUtil() {
		entityManagerFactory= Persistence.createEntityManagerFactory("cuoiki_server");
		entityManager=entityManagerFactory.createEntityManager();
	}
	public EntityManager getEnManager() {
		return entityManager;
	}

	public void closeEnManager() {
		entityManager.close();
	}
	
	public void closeEnManagerFactory() {
		entityManagerFactory.close();
	}
}
