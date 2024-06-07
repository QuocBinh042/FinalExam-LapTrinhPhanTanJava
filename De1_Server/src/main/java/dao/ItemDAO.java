package dao;

import java.util.Map;

import entity.Food;

public interface ItemDAO {
	public Map<Food, Double> listFoodAndCost();
}
