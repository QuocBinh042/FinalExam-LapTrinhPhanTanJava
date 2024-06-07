package entity;

import java.io.Serializable;

import enums.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage extends Item implements Serializable {
	@Enumerated(EnumType.STRING)
	private Size size;
	@Column(name = "supplier_name")
	private String supplierName;

	public Beverage(Size size, String supplierName) {
		super();
		this.size = size;
		this.supplierName = supplierName;
	}

	public Beverage() {
		super();
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
