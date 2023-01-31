package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Allen Tong - awtong
 * CIS175 - Spring 2023
 * Jan 29, 2023
 */

@Entity
@Table(name = "animalcatalogue")
public class Animal {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "animal")
	private String animal;
	@Column(name = "species")
	private String species;
	@Column(name = "habitat")
	private String habitat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getHabitat() {
		return habitat;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	
	/**
	 * Prints out the details
	 * @return - the details of the animal
	 */
	public String details() {
		return String.format("ID %d - Animal: %s, Species: %s, Habitat: %s", this.id, this.animal, this.species, this.habitat);
	}
	
	/**
	 * Default constructor
	 */
	public Animal() {}
	
	/**
	 * Non-default constructor.
	 * @param animal - the animal
	 * @param species - the species of animal
	 * @param habitat - the animal's habitat
	 */
	public Animal(String animal, String species, String habitat) {
		this.animal = animal;
		this.species = species;
		this.habitat = habitat;
	}
	
}
