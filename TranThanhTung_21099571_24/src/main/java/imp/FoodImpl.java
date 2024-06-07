package imp;

import java.util.List;

import entity.Food;
import entity.Item;

public interface FoodImpl {
	public boolean addFood(Food food);
	public List<Item> listItems(String supplierName);
}
