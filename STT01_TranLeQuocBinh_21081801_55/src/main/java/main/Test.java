package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
}
