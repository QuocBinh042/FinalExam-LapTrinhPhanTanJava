package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Beverage;
import entity.Food;
import entity.Item;
import entity.Size;
import entity.Type;
import imp.FoodImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FoodDao implements FoodImpl{
	private EntityManager em;
	public FoodDao() {
		em = Persistence.createEntityManagerFactory("data").createEntityManager();
	}
	
	public static void main(String[] args) {
		FoodDao foodDao = new FoodDao();
		System.out.println(foodDao.listItems("D"));
	}
	
	public boolean addBeverage(Beverage beverage) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if(em.find(Beverage.class, beverage.getId()) != null) {
				return false;
			}
			
			em.merge(beverage);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}
	
	public boolean addFood(Food food) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if(em.find(Food.class, food.getId()) != null) {
				return false;
			}
			
			em.merge(food);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	@Override
	public List<Item> listItems(String supplierName) {
		return em.createQuery("select b from Beverage b where b.supplierName like :supplierName and b.onSpecial = TRUE", Item.class)
				 .setParameter("supplierName", "%" + supplierName + "%")
				 .getResultList();
	}
	
	public List<Item> listItems2() {
		return em.createQuery("select b from Beverage b where b.onSpecial = TRUE", Item.class)
				 .getResultList();
	}
	
	public List<Item> getAllItems() {
		return em.createQuery("select i from Beverage i", Item.class)
				 .getResultList();
	}
}
