import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;

import db.DBManager;
import model.Animal;

/**
 * @author Allen Tong - awtong
 * CIS175 - Spring 2023
 * Jan 30, 2023
 */
public class Main {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to the animal catalogue thing");
		
		String animal;
		String species;
		String habitat;
		
		Animal found;
		
		boolean repeat = true;
		DBManager.start();
		
		while (repeat) {
			
			System.out.println("What would you like to do?");
			
			System.out.println("1 - Add an animal");
			System.out.println("2 - Update an animal");
			System.out.println("3 - Delete an animal");
			System.out.println("4 - View all animals");
			System.out.println("5 - Exit");
			
			System.out.print("Enter your selection: ");
			
			while (!in.hasNextInt()) {
				System.out.println("Please enter a number");
				in.next();
				System.out.print("Enter your selection: ");
			}
			int selection = in.nextInt();
			in.nextLine();
			
			switch (selection) {
			case 1:
				System.out.print("Enter the type of animal: ");
				
				animal = in.nextLine();
				System.out.print("Enter the species: ");
				species = in.nextLine();
				System.out.print("Enter the animal's habitat: ");
				habitat = in.nextLine();
				found = new Animal(animal, species, habitat);
				DBManager.addAnimal(found);
				
				System.out.println("Animal added");
				break;
			
			case 2:
				System.out.println("Let's find the animal to update");
				
				System.out.print("Enter the type of animal: ");
				animal = in.nextLine();
				System.out.print("Enter the species: ");
				species = in.nextLine();
				System.out.print("Enter the animal's habitat: ");
				habitat = in.nextLine();
				
				try {
					found = DBManager.searchForAnimal(animal, species, habitat);
					
					System.out.println("Type in the new traits. If a trait will not be updated, enter what it already is.");
					
					System.out.print("Enter the new type of animal: ");
					String newAnimal = in.nextLine();
					System.out.print("Enter the new species: ");
					String newSpecies = in.nextLine();
					System.out.print("Enter the new habitat: ");
					String newHabitat = in.nextLine();
					
					found.setAnimal(newAnimal);
					found.setSpecies(newSpecies);
					found.setHabitat(newHabitat);
					
					DBManager.updateAnimal(found);
					
					System.out.println("Animal updated");
				} catch (NoResultException e) {
					System.out.println("Animal not found");
				}
				break;
				
			case 3:
				System.out.println("Enter the traits of the animal to be deleted");
				
				System.out.print("Enter the type of animal: ");
				animal = in.nextLine();
				System.out.print("Enter the species: ");
				species = in.nextLine();
				System.out.print("Enter the animal's habitat: ");
				habitat = in.nextLine();
				found = new Animal(animal, species, habitat);
				
				DBManager.deleteAnimal(found);
				
				System.out.println("Animal deleted");
				break;
				
			case 4:
				List<Animal> allAnimals = DBManager.showAllAnimals();
				
				for (Animal i : allAnimals) {
					System.out.println(i.details());
				}
				break;
				
			default:
				repeat = false;
			}
			
		}
		
		in.close();
		DBManager.cleanUp();
		
		System.out.println("Goodbye!");
		
	}
	
}
