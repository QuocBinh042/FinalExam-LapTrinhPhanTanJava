package dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ItemDAO;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ItemImpl implements ItemDAO{
	private EntityManager em;

	public ItemImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public Map<Food, Double> listFoodAndCost() {
//	    String jpql = "Select f.id, sum(1 * ig.price) + (preparation_time + serving_time)*2 as cost\r\n"
//	    		+ "from [dbo].[foods] f inner join [dbo].[items] i on i.id = f.id\r\n"
//	    		+ "inner join [dbo].[items_ingredients] ii on ii.item_id = i.id\r\n"
//	    		+ "inner join [dbo].[ingredients] ig on ig.ingredient_id = ii.ingredient_id\r\n"
//	    		+ "group by f.id, preparation_time,  serving_time";
	    String sql="select f.id, (sum(ig. quantity*ig. price) (f. preparation_time f. serving)*0.2) as Total"
	    			+ "from Food f"
	    			+ "join f.ingredients ig "
	    			+ "group by f.id, f. preparation_time, f.serving_time"
	    			+ "order by Total desc";
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
//		list.forEach((Food,Double)->{
//			System.out.println(Food.getId() + " " + Double);
//		});
	}
}
