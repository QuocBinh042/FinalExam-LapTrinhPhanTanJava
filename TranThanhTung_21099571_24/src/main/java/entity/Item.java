	package entity;
	
	import java.util.HashSet;
	import java.util.Set;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
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
	public abstract class Item {
		@Id
		@Column(name = "id")
		protected String id;
		@Column(nullable = false, columnDefinition = "nvarchar(250)")
		protected String name;
		@Column(nullable = false)
		protected double price;
		@Column(nullable = false, columnDefinition = "nvarchar(250)")
		protected String description;
		@Column(nullable = false, name = "on_special")
		protected boolean onSpecial;
		@ManyToMany
		@JoinTable(name = "items_ingredients", joinColumns = {@JoinColumn(name = "item_id")}, inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
		protected Set<Ingredient> ingredients = new HashSet<Ingredient>();
		public Item(String id, String name, double price, String description, boolean onSpecial) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.description = description;
			this.onSpecial = onSpecial;
		}
		public Item() {
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
		@Override
		public String toString() {
			return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
					+ ", onSpecial=" + onSpecial + "]";
		}
	}
