package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
	@Id
	@Column(nullable = false)
	private String id;
	private String name;
	private double price;
	private String description;
	@Column(name = "on_special")
	private boolean onSpecial;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "items_ingredients", 
		joinColumns = @JoinColumn(name = "ingredient_id"), 
		inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();

	public Item() {
		super();
	}

	public Item(String id, String name, double price, String description, boolean onSpecial,
			Set<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.onSpecial = onSpecial;
		this.ingredients = ingredients;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnSpecial() {
		return onSpecial;
	}

	public void setOnSpecial(boolean onSpecial) {
		this.onSpecial = onSpecial;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", onSpecial=" + onSpecial + ", ingredients=" + ingredients + "]";
	}

}
