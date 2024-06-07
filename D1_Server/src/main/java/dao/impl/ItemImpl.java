package dao.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ItemDAO;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ItemImpl implements ItemDAO {
	private EntityManager em;

	public ItemImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public Map<Food, Double> listFoodAndCost() {

	    String sql="SELECT f.id AS food_id, SUM(i.price * ig.quantity) + (f.preparation_time + f.serving_time)*0.2 AS cost"
	    		 + "FROM foods f "
	    		 + "JOIN items i ON f.id = i.id "
	    		 + "JOIN items_ingredients ii ON i.id = ii.item_id"
	    		 + "JOIN ingredients ig ON ii.ingredient_id = ig.ingredient_id"
	    		 + "GROUP BY f.id, f.preparation_time, f.serving_time"
	    		 + "ORDER BY cost DESC";
	    List<Object[]> results =em.createNativeQuery(sql).getResultList();
	    
	    return results.stream()
	            .collect(Collectors.toMap(
	                    row -> (Food) row[0],   
	                    row -> (Double) row[1] 
	            ));
	}

	public static void main(String[] args) {
		ItemImpl iDAO = new ItemImpl();
		Map<Food, Double> list = iDAO.listFoodAndCost();
		list.forEach((Food, Double) -> {
			System.out.println(Food.getId() + " " + Double);
		});
	}
}
