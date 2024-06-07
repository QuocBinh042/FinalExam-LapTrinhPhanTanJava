package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import dao.ItemDAO;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import enums.Type;
@TestInstance (Lifecycle.PER_CLASS)
class ItemTest {
	private static ItemDAO iDAO;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		iDAO = new ItemImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
		iDAO = null;
	}

	@Test
	void testAddFoodTrue() {
		Boolean kq = iDAO.addFood(new Food("F112", "COM", 30.0, "a", false, null, Type.APPETIZER, 5, 3));
		assertTrue(kq);
	}
	
	@Test
	void testAddFoodFalse() {
		Boolean kq = iDAO.addFood(new Food("F111", "COM", 30.0, "a", false, null, Type.APPETIZER, 5, 3));
		assertEquals(false, kq);
	}
	
	@Test
	void testListItemsTrue() {
		List<Item> l = iDAO.listItems("Anna Food Distributors");
		assertEquals(3, l.size());
	}
	@Test
	void testListItemsFalse() {
		List<Item> l = iDAO.listItems("A");
		assertEquals(0, l.size());
	}
}
