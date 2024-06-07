package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "foods")
@PrimaryKeyJoinColumn(name = "id")
public class Food extends Item{
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(name = "preparation_time", nullable = false)
	private int preparationTime;
	@Column(name = "serving_time", nullable = false)
	private int servingTime;
	public Food(String id, String name, double price, String description, boolean onSpecial, Type type,
			int preparationTime, int servingTime) {
		super(id, name, price, description, onSpecial);
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	}
	public Food(String id, String name, double price, String description, boolean onSpecial) {
		super(id, name, price, description, onSpecial);
	}
	
	public Food() {
		
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}
	public int getServingTime() {
		return servingTime;
	}
	public void setServingTime(int servingTime) {
		this.servingTime = servingTime;
	}
	@Override
	public String toString() {
		return "Food [type=" + type + ", preparationTime=" + preparationTime + ", servingTime=" + servingTime + "]";
	}
}
