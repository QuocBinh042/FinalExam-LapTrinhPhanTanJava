package app;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dao.BrandDao;
import dao.CategoryDao;
import dao.CustomerDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.StaffDao;
import entities.Address;
import entities.Brand;
import entities.Category;
import entities.Customer;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.Staff;

public class TestCRUD {
  public static void main(String[] args) {
    CustomerDao cusDao = new CustomerDao();
    BrandDao brandDao = new BrandDao();
    CategoryDao cateDao = new CategoryDao();
    ProductDao proDao = new ProductDao();
    StaffDao staffDao = new StaffDao();
    OrderDao orderDao = new OrderDao();
    Address address = new Address("123 Main St", "New York", "NY", "10001");
    Customer cus = new Customer(8080, "John", "Smith", "kuga@gmail.com");
    cus.setAddress(address);
    // System.out.println(cusDao.addCus(cus));
    Brand brand = new Brand(802, "Nikes");
    // System.out.println(brandDao.addBrand(brand));
    Category category = new Category(888, "Category 1");
    // System.out.println(cateDao.addCategory(category));
    Product product = new Product(123, "Product 1", 100, 10);
    product.setBrand(brand);
    product.setCategory(category);
    // System.out.println(proDao.addProduct(product));
    Staff staff = new Staff(98764, "kuga", "cter", "kugacter@gamil.com", "123456", 2);
    Staff staffManager = new Staff(98765);
    staff.setStaff(staffManager);

    // System.out.println(staffDao.addStaff(staff));

    OrderItem orderItem = new OrderItem(1, 1, 1, 1, product);
    OrderItem orderItem2 = new OrderItem(2, 2, 2, 2, product);
    List<OrderItem> orderItems = Arrays.asList(orderItem, orderItem2);
    Order order = new Order(123213, 3, new Date("2020/12/10"), new Date("2020/12/11"), new Date("2020/12/12"),
        orderItems, cus, staffManager);
    order.setOrderItems(orderItems);
    order.setCustomer(cus);
    order.setStaff(staff);

    // System.out.println(orderDao.addOrder(order));

  }
}
