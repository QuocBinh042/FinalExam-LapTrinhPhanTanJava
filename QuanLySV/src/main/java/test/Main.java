package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
	private static EntityManager em;
	public static void main(String[] args) {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();

	}
}
