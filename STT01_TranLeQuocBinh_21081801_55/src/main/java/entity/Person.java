package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table (name = "Person")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Person implements Serializable{
	@Id
	@Column (nullable = false)
	protected int id;
	protected String firstName;
	protected String lastName;
	
}
