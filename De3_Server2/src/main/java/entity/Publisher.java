package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {
	@Id
	@Column(name = "publisher_id", nullable = false)
	private String id;
	private String name;
	private String address;
	private String email;
	private String phone;
	@OneToMany (mappedBy = "publisher")
	private List<Book> books;
	public Publisher(String id, String name, String address, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public Publisher() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone="
				+ phone + "]";
	}

}
