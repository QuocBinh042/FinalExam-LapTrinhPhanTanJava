package app;

import dao.BrandDao;
import dao.CategoryDao;
import dao.CustomerDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.StaffDao;

public class Query {
	public static void main(String[] args) {
		CustomerDao cusDao = new CustomerDao();
		BrandDao brandDao = new BrandDao();
		CategoryDao cateDao = new CategoryDao();
		ProductDao proDao = new ProductDao();
		StaffDao staffDao = new StaffDao();
		OrderDao orderDao = new OrderDao();
		orderDao.getOrdersByCustomers().forEach((k, v) -> System.out.println(k + " " + v));
		// 1
		// proDao.getProductByModel(2016).forEach(System.out::println);

		// 2
		// proDao.getProductByCateID(2).forEach(System.out::println);

		// 3
		// proDao.getProductByCateID(2).forEach(System.out::println);

		// 4
		// proDao.getProductByCateName("Cruisers
		// Bicycles").forEach(System.out::println);

		// 5
		// proDao.getProductByBrandID(1).forEach(System.out::println);

		// 6
		// proDao.getProductByBrandName("Electra").forEach(System.out::println);

		// 7
		// orderDao.getOrdersByProductName("Pure Cycles William 3-Speed -
		// 2016").forEach(System.out::println);

		// 8
		// orderDao.getOrdersByDiscount().forEach(System.out::println);

		// 9
		// cateDao.getCategoriesByProductNumber().forEach(System.out::println);

	}
}
