package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	public EntityManagerFactoryUtil() {
		entityManagerFactory= Persistence.createEntityManagerFactory("STT36_PhiHuynhMinhQuan_21080711_23");
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
