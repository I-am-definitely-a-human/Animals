package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Animal;

/**
 * @author Allen Tong - awtong
 * CIS175 - Spring 2023
 * Jan 30, 2023
 */
public class DBManager {
	
	static EntityManagerFactory factory;
	
	public static void start() {
		factory = Persistence.createEntityManagerFactory("Animals");
	}
	
	/**
	 * Adds an animal to the database.
	 * @param animal - the animal to add
	 */
	public static void addAnimal(Animal animal) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(animal);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	/**
	 * Removes the specified animal from the database. If there are multiple of the same animal, it removes the first one (I think)
	 * @param animal - the animal to delete
	 */
	public static void deleteAnimal(Animal animal) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		TypedQuery<Animal> query = manager.createQuery("select i from Animal i where i.animal = :a and i.species = :s and i.habitat = :h", Animal.class);
		
		query.setParameter("a", animal.getAnimal());
		query.setParameter("s", animal.getSpecies());
		query.setParameter("h", animal.getHabitat());
		
		query.setMaxResults(1);
		
		try {
			manager.remove(query.getSingleResult());
		} catch (NoResultException e) {
			System.out.println("Animal not found");
		}
		
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
	/**
	 * Returns a list of all the animals in the database.
	 * @return - a list of all the animals in the database.
	 */
	public static List<Animal> showAllAnimals() {
		EntityManager manager = factory.createEntityManager();
		List<Animal> all = manager.createQuery("select i from Animal i", Animal.class).getResultList();
		manager.close();
		return all;
	}
	
	/**
	 * Updates an animal. Which animal is specified with the ID of the Animal passed in.
	 * @param animal - the animal with the all the new properties that the specified animal should be updated to.
	 */
	public static void updateAnimal(Animal animal) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		manager.merge(animal);
		
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	/**
	 * Searches for an animal of the specified type, species, and habitat. If there are multiple, the first one is returned (I think)
	 * @param animal - the type of animal
	 * @param species - the species
	 * @param habitat - the habitat
	 * @return - the animal found
	 */
	public static Animal searchForAnimal(String animal, String species, String habitat) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		TypedQuery<Animal> query = manager.createQuery("select i from Animal i where i.animal = :a and i.species = :s and i.habitat = :h", Animal.class);
		
		query.setParameter("a", animal);
		query.setParameter("s", species);
		query.setParameter("h", habitat);
		
		query.setMaxResults(1);
		
		return query.getSingleResult();
	}
	
	/**
	 * Cleans up.
	 */
	public static void cleanUp() {
		factory.close();;
	}

	
	/**
	 * Default constructor
	 */
	public DBManager() {}
	
}
