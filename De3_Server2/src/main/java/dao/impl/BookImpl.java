package dao.impl;

import dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BookImpl implements BookDAO {
	private EntityManager em;
	
	public BookImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
	
	public static void main(String[] args) {
		BookImpl b = new BookImpl();
		
	}
}
