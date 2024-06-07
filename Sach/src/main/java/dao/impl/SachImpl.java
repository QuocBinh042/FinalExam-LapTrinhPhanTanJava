package dao.impl;

import dao.Sach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class SachImpl implements Sach{
	private EntityManager em;

	public SachImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
}
