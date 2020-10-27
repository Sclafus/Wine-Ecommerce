/*Project Developed by 
Alessandro Sclafani 298779 & 
Martina Caffagnini 294520 for 
Ingegneria del Software*/

import java.util.*;

//TODO: fix this javadoc lol
/**
 * The {@code Club} class is a container class. It contains:
 * <ul>
 * <li>{@code People} class</li>
 * <li>{@code Activity} class</li>
 * </ul>
 * In the Club class there are three {@code ArrayList}:
 * <ul>
 * <li>{@code users} arraylist which contains all the people subscribed and the admins of the sport club </li>
 * <li>{@code lessons} arraylist which contains all the lessons activated by the sport club</li>
 * <li>{@code races} arraylist which contains all the races planned by the sport club</li>
 * </ul>
 * This class acts as an abstraction of a sport club.
 * @see ArrayList
 * @see Wine
 * @see Order
 * @see Employee
 * @see Customer
 */
public class Ecommerce {
	protected List<Customer> users = new ArrayList<Customer>();
	protected List<Wine> wines = new ArrayList<Wine>();
    /**
	 * Class {@code People} is a class that should never be instantiated.
	 * This class is used as a container for Class {@code Customer} and
	 * its subclass {@code Employee}.
	 * @see Customer
	 * @see Employee
	 */
    public class People {
		
		protected String name;
		protected String surname;
		protected String email;

		/**
		 * {@code People} Class constructor. This constructor should NEVER
		 * be called.
		 */
		protected People() {
			this.name = "";
			this.surname = "";
			this.email = "";
		}

		/**
		 * {@code People} Class constructor. 
		 * This constructor should NEVER be called.
		 * @param name name of the new user [String]
		 * @param sur surname(lastname) of the new user [String]
		 * @param email email of the new user [String]
		 * @param passwd password of the new user [String]
		 */
		protected People(final String name, final String sur, final String email) {
			this.name = name;
			this.surname = sur;
			this.email = email;
		}

    }
    
    /**
	 * The {@code User} class is a container class. 
	 * It contains all the people subscribed and the admins of the sport club.
	 * It is a subclass of {@code People} and it is extended by the class {@code Admin}.
	 * @see People
	 * @see Admin
	 */
	public class Customer extends People {

		/**
		 * {@code User} Class Constructor. User is an extension of class {@code People}.
		 * @see People
		 */
		public Customer() {
			super();
		}

		/**
		 * {@code User} Class constructor. User is an extension of class {@code People}.
		 * @param name name of the new user [String]
		 * @param sur surname(lastname) of the new user [String]
		 * @param email email of the new user [String]
		 * @see People
		 */
		public Customer(final String name, final String sur, final String email) {
			super(name, sur, email);
		}

		/**
		 * Gets the name of the {@code User}.
		 * @return The name of the user [String].
		 * @see Customer
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Gets the surname (last name) of the {@code User}.
		 * @return The surname of the user [String].
		 * @see Customer
		 */
		public String getSurname() {
			return this.surname;
		}

		/**
		 * Gets the email of the {@code User}.
		 * @return The email of the user [String].
		 * @see Customer
		 */
		public String getEmail() {
			return this.email;
        }
    }

    /**
	 * The {@code Admin} class is an extension of class {@code User}.
	 * An admin can access all the methods contained in {@code User},
	 * but it also has the priviledge to operate on the ArrayLists
	 * contained in {@code Club}.
	 * @see User
	 * @see People
	 * @see ArrayList
	 * @see Club
	 */
	public class Employee extends Customer {
		
		/**
		 * {@code Admin} Class constructor. Admin extends class {@code User}.
		 * @see User
		 */
		public Employee() {
			super();
		}
		
		/**
		 * {@code Admin} Class constructor. Admin extends class {@code User}.
		 * 
		 * @param name name of the new admin [String]
		 * @param sur surname(last name) of the new admin [String]
		 * @param email email of the new admin [String]
		 * @see User
		 */
		public Employee(final String name, final String sur, final String email) {
			super(name, sur, email);
        }
    }

	public class Order {
		private List<Wine> items = new ArrayList<Wine>();


		public Order(final List<Wine> wines){
			for (Wine wine : wines){
				items.add(wine);
			}
		}
	}

	//TODO: javadoc
	public class Wine {

		private String name;
		private String producer; //not really a great name tho.
		private short year;
		private String notes;
		private List<String> grapewines = new ArrayList<String>();
		private int quantity;

		public Wine(){
			this.name = "";
			this.producer = "";
			this.year = -1;
			this.notes = "";
			this.quantity=-1;
		}

		public Wine(final String name, final String producer, final short year, final String notes, final List<String> grapes){
			this.name = name;
			this.producer = producer;
			this.year = year;
			this.notes = notes;

			for (String grape : grapes){
				grapewines.add(grape);
			}
		}

		public void restock(int quantity){
			this.quantity += quantity;
		}
		public void withdraw(int quantity){
			this.quantity -= quantity;
			
		}
	}
	
	public static void main(String argv[]){
		Ecommerce ecc = new Ecommerce();
		ecc.true_main();
	}
	
	public void true_main(){

	}
}