package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ItemDAO;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ItemImpl implements ItemDAO {

	private EntityManager em;

	public ItemImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public boolean addFood(Food food) {
		EntityTransaction tx = em.getTransaction();
		try {
			if (!tx.isActive()) {
	            tx.begin();
	        }
			if ((em.find(Food.class, food.getId()) != null) && (food.getId().matches("^F\\d{3,}$"))) {
				return false;
			}
			em.persist(em.merge(food));
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<Food, Double> listFoodAndCost() {
		String jpql = "SELECT f, SUM(i.price * ii.quantity) + (f.preparationTime + f.servingTime) * 0.2 AS cost " +
                "FROM Food f " +
                "JOIN f.ingredients ii " +
                "JOIN ii.ingredients i " +
                "GROUP BY f, f.preparationTime, f.servingTime " +
                "ORDER BY cost DESC";

		List<Object[]> results = em.createQuery(jpql, Object[].class).getResultList();

		Map<Food, Double> foodCostMap = new HashMap<>();
		for (Object[] result : results) {
			Food food = (Food) result[0];
			Double cost = (Double) result[1];
			foodCostMap.put(food, cost);
		}

		return foodCostMap;
	}

	@Override
	public List<Item> listItems(String supplierName) {
		String jpql = "SELECT DISTINCT it FROM Item it " + "JOIN it.ingredients i "
				+ "WHERE i.supplierName = :supplierName";
		return em.createQuery(jpql, Item.class).setParameter("supplierName", supplierName).getResultList();
	}

	public static void main(String[] args) {
		ItemImpl it = new ItemImpl();
		List<Item> l = it.listItems("Anna Food Distributors");
		l.forEach(System.out::println);
		
//		Map<Food, Double> l2 = it.listFoodAndCost();
//		l2.forEach((Food, Double)->{
//			System.out.println(Food.getId() + " " + Double);
//		});
	}
}
