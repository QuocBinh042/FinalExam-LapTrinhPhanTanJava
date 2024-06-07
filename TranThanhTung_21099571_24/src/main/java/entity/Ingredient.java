package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
	@Id
	@Column(name = "ingredient_id")
	private String id;
	@Column(name = "ingredient_name", nullable = false, columnDefinition = "nvarchar(250)")
	private String name;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String unit;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private double quantity;
	@Column(name = "manufacturing_date")
	private LocalDate manufacturingDate;
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	@Column(name = "supplier_name", columnDefinition = "nvarchar(250)")
	private String supplierName;
	@ManyToMany(mappedBy = "ingredients")
	private Set<Item> items = new HashSet<Item>();
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		return Objects.equals(id, other.id);
	}
	public Ingredient(String id, String name, String unit, double price, double quantity, LocalDate manufacturingDate,
			LocalDate expiryDate, String supplierName) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
	}
	public Ingredient() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit + ", price=" + price + ", quantity="
				+ quantity + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate
				+ ", supplierName=" + supplierName + "]";
	}
}
