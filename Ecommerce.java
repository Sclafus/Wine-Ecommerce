/*Project Developed by 
Alessandro Sclafani 298779 & 
Martina Caffagnini 294520 for 
Ingegneria del Software*/

import java.util.*;


/**
 * The {@code Ecommerce} class is a container class. It contains:
 * <ul>
 * <li>{@code People} class</li>
 * <li>{@code Customer} class, that extends {@code People} class</li>
 * <li>{@code Employee} class, that extends {@code Customer} class</li>
 * <li>{@code Wine} class</li>
 * <li>{@code Order} class</li>
 * <li>{@code register} method</li>
 * </ul>
 * In the Ecommerce class there are three {@code ArrayList}:
 * <ul>
 * <li>{@code customers} arraylist, which contains all the customers 
 * and employees in the ecommerce </li>
 * <li>{@code wines} arraylist which contains all the wines currently listed in the ecommerce</li>
 * <li>{@code ordersToBeProcessed} arraylist which contains all the orders that have to be sent</li>
 * </ul>
 * This class acts as an abstraction of a wine ecommerce.
 * @see Wine
 * @see Order
 * @see Employee
 * @see Customer
 */

 //? customers and wines should be an hash table instead?
 public class Ecommerce {

	protected List<Customer> customers = new ArrayList<Customer>();
	protected List<Wine> wines = new ArrayList<Wine>();
	protected List<Order> ordersToBeProcessed = new ArrayList<Order>();

	//? class People should become an abstract class?
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
		 */
		protected People(final String name, final String sur, final String email) {
			this.name = name;
			this.surname = sur;
			this.email = email;
		}

    }
	
	//? cart and orders should be an hash table too?
    /**
	 * {@code Customer} is a class that rappresents a generic Customer of the shop.
	 * It is a subclass of {@code People} and it is extended by the class {@code Employee}.
	 * Every {@code Customer} has a {@code cart} and 
	 * an {@code orders} list, like in every ecommerce ever.
	 * @see People
	 * @see Employee
	 */
	public class Customer extends People {
		protected ArrayList<Wine> cart = new ArrayList<Wine>();
		protected ArrayList<Order> orders = new ArrayList<Order>();
		//TODO: authentication method
		
		/**
		 * {@code Customer} class constructor. Customer is an extension of class {@code People}.
		 * @see People
		 */
		public Customer() {
			super();
		}

		/**
		 * {@code Customer} class constructor. Customer is an extension of class {@code People}.
		 * @param name name of the new customer [String]
		 * @param sur surname(lastname) of the new customer [String]
		 * @param email email of the new customer [String]
		 * @see People
		 */
		public Customer(final String name, final String sur, final String email) {
			super(name, sur, email);
		}

		/**
		 * Gets the name of the {@code Customer}.
		 * @return The name of the customer. [String]
		 * @see Customer
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Gets the surname of the {@code Customer}.
		 * @return The surname of the customer. [String]
		 * @see Customer
		 */
		public String getSurname() {
			return this.surname;
		}

		/**
		 * Gets the email of the {@code Customer}.
		 * @return The email of the customer. [String]
		 * @see Customer
		 */
		public String getEmail() {
			return this.email;
		}
		
		/**
		 * prints all the informations regarding all 
		 * the wines produced in the provided year.
		 * @param year the year of production of the {@code Wine} we are searching. [short]
		 * @see Wine
		 */
		public void searchByYear(short year){
			for (Wine wine : wines){
				if(wine.getYear() == year){
					wine.printInfo();
				}
			}
		}
		
		/**
		 * prints all the informations regarding all
		 * the wines with the provided name.
		 * @param name the name of the {@code Wine} we are searching. [String]
		 * @see Wine
		 */
		public void searchByName(String name){
			for (Wine wine : wines){
				if(wine.getName() == name){
					wine.printInfo();
				}
			}
		}
		
		/**
		 * adds to {@code cart} a selected {@code Wine} with the selected quantity.
		 * Warns the {@code Customer} in case the desired quantity is unavailable.  
		 * @param wine the wine the customer wants to buy. [Wine]
		 * @param quantity the quantity of the selected {@code Wine}. [short]
		 * @see Wine
		 */
		public void addToCart(Wine wine, short quantity){
			if(quantity > 0){
				Wine wine_tmp = new Wine(wine.getName(),
				wine.getProducer(), wine.getYear(), wine.getNotes(), quantity, wine.getGrapewines());
				if (wine.getQuantity() < quantity){
					System.out.println("Warning - The selected quantity is unavailable");
				}
				this.cart.add(wine_tmp);
			}
		}

		/**
		 * Removes the selected wine from the {@code cart}.
		 * @param wine the wine the customer wants to remove from the {@code cart}. [Wine] 
		 */
		public void removeFromCart(Wine wine){
			this.cart.remove(wine);
		} 
		
		//TODO: discuss this
		public void buy(){
			Boolean flag = false;

			for(Wine wine_toBuy : cart){
				for(Wine wine_inStock : wines){

					if( (wine_toBuy.getName() == wine_inStock.getName()) && 
					(wine_toBuy.getQuantity() > wine_inStock.getQuantity()) ){
						flag = !flag;
						System.out.format("Wine %s is currently not available in the selected quantity.");
					}
				}
			}

			Order order = new Order(this.cart);
			this.orders.add(order);
		}
    }

    /**
	 * The {@code Employee} class is an extension of class {@code Customer}.
	 * An employee can access all the methods contained in {@code Customer},
	 * but it also can send the orders of the clients and restock the storage.
	 * @see Customer
	 * @see People
	 * @see Ecommerce
	 */
	public class Employee extends Customer {
		
		/**
		 * {@code Employee} Class constructor. Employee extends class {@code Customer}.
		 * @see Customer
		 */
		public Employee() {
			super();
		}
		
		/**
		 * {@code Employee} Class constructor. Employee extends class {@code Customer}.
		 * 
		 * @param name name of the new employee. [String]
		 * @param sur surname(last name) of the new employee. [String]
		 * @param email email of the new employee. [String]
		 * @see Customer
		 */
		public Employee(final String name, final String sur, final String email) {
			super(name, sur, email);
        }
    }

	//TODO: finish all these javadocs
	/**
	 * The {@code Order} class is pretty straight forward. Every order has
	 * a list of wines that have been 
	 */
	public class Order {

		//TODO: we need to find a way to have a unique id for every order
		private ArrayList<Wine> items = new ArrayList<Wine>();
		private long id;

		/**
		 * 
		 */
		public Order() {
			++this.id;
		}

		/**
		 * @param wines List of {@code Wine}s
		 * @see Wine
		 */
		public Order(final List<Wine> wines){
			this.id++;
			for (Wine wine : wines){
				this.items.add(wine);
			}
		}

		
		/**
		 * Gets the id of the selected {@code Order}.
		 * @return the id of the {@code Order}. [long]
		 */
		public long getId() {
			return this.id;
		}
	}

	//TODO: javadoc for the class and missing methods.
	/**
	 * 
	 */
	public class Wine {

		private String name;
		private String producer;
		private short year;
		private String notes;
		private ArrayList<String> grapewines = new ArrayList<String>();
		private int quantity;

		/**
		 * 
		 */
		public Wine(){
			this.name = "";
			this.producer = "";
			this.year = -1;
			this.notes = "";
			this.quantity = -1;
		}

		/**
		 * 
		 * @param name name of the wine. [String]
		 * @param producer producer of the wine. [String]
		 * @param year year of production of the wine. [short]
		 * @param notes notes for the wine. [String] 
		 * @param quantity quantity of the wine. [int]
		 * @param grapes list of the grapes. [List of Strings]
		 */
		public Wine(final String name, final String producer, final short year,
		 final String notes, final int quantity, final List<String> grapes){

			this.name = name;
			this.producer = producer;
			this.year = year;
			this.notes = notes;
			this.quantity = quantity;

			for (String grape : grapes){
				grapewines.add(grape);
			}
		}

		/**
		 * Gets the name of the {@code Wine}.
		 * @return the name of the {@code Wine}. [String]
		 */
		public String getName(){
			return this.name;
		}

		/**
		 * Gets the producer of the {@code Wine}.
		 * @return the producer of the {@code Wine}. [String]
		 */
		public String getProducer(){
			return this.producer;
		}

		/**
		 * Gets the name of the {@code Wine}.
		 * @return the name of the {@code Wine}. [String]
		 */
		public short getYear(){
			return this.year;
		}
		
		/**
		 * Gets the notes of the {@code Wine}.
		 * @return the notes of the {@code Wine}. [String]
		 */
		public String getNotes(){
			return this.notes;
		}

		/**
		 * Gets the quantity of the {@code Wine}.
		 * @return the quantity of the {@code Wine}. [int]
		 */
		public int getQuantity(){
			return this.quantity;
		}

		/**
		 * Gets the list of grapewines of the {@code Wine}.
		 * @return the list of grapewines of the {@code Wine}. [ArrayList of Strings]
		 */
		public ArrayList<String> getGrapewines(){
			return this.grapewines;
		}

		//? these methods should be used by the employee, we should move them
		public void restock(int quantity){
			this.quantity += quantity;
		}

		public void withdraw(int quantity){
			this.quantity -= quantity;
		}


		/**
		 * Prints in console the relevant informations of the {@code Wine}.
		 */
		public void printInfo(){
			System.out.println(new StringBuilder("Name: ").append(this.name)
			.append("\nYear: ").append(this.year)
			.append("\nProducer: ").append(this.producer)
			.append("\nNotes: ").append(this.notes)
			.append("\nQuantity: ").append(this.quantity)
			.append("\nGrapewines:"));

			for (String grape : this.grapewines){
				System.out.format("\n-%s", grape);
			}
		}
	}
	
	public void register(final String name, final String surname, final String mail ) {
		Customer new_cust = new Customer(name, surname, mail);
		customers.add(new_cust);
	}

	public static void main(String argv[]) {
		Ecommerce ecc = new Ecommerce();
		ecc.true_main();
	}
	
	public void true_main(){
	}
}