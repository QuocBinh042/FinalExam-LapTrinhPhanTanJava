package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {
	@Id
	@Column(name = "ingredient_id", nullable = false)
	private String id;
	@Column (name = "ingredient_name")
	private String name;
	private String unit;
	private double price;
	private double quantity;
	@Column(name = "manufacturing_date")
	private LocalDate manufacturingDate;
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	@Column(name = "supplier_name")
	private String supplierName;

	public Ingredient() {
		super();
	}

	public Ingredient(String id, String name, String unit, double quantity, LocalDate manufacturingDate,
			LocalDate expiryDate, String supplierName) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
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
		return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit + ", quantity=" + quantity
				+ ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate + ", supplierName="
				+ supplierName + "]";
	}

}
