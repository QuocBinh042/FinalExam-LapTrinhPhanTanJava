package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverages")
@PrimaryKeyJoinColumn(name = "id")
public class Beverage extends Item{
	@Enumerated(EnumType.STRING)
	private Size size;
	@Column(name = "supplier_name", columnDefinition = "nvarchar(250)")
	private String supplierName;
	public Beverage(String id, String name, double price, String description, boolean onSpecial, Size size,
			String supplierName) {
		super(id, name, price, description, onSpecial);
		this.size = size;
		this.supplierName = supplierName;
	}
	
	public Beverage() {
		
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "Beverage [size=" + size + ", supplierName=" + supplierName + "]";
	}
	
}
