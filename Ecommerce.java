/*Project Developed by 
Alessandro Sclafani 298779 & 
Martina Caffagnini 294520 for 
Ingegneria del Software*/

import java.util.*;

//TODO: fix this javadoc lol
/**
 * The {@code Ecommerce} class is a container class. It contains:
 * <ul>
 * <li>{@code People} class</li>
 * <li>{@code Activity} class</li>
 * </ul>
 * In the Ecommerce class there are three {@code ArrayList}:
 * <ul>
 * <li>{@code customers} arraylist which contains all the people subscribed and the admins of the sport ecommerce </li>
 * <li>{@code wines} arraylist which contains all the wines activated by the sport ecommerce</li>
 * <li>{@code races} arraylist which contains all the races planned by the sport ecommerce</li>
 * </ul>
 * This class acts as an abstraction of a sport ecommerce.
 * @see ArrayList
 * @see Wine
 * @see Order
 * @see Employee
 * @see Customer
 */

 public class Ecommerce {
	protected List<Customer> customers = new ArrayList<Customer>();
	protected List<Wine> wines = new ArrayList<Wine>();
	protected List<Order> ordersToBeProcessed = new ArrayList<Order>();

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
    
    /**
	 * The {@code User} class is a container class. 
	 * It contains all the people subscribed and the employees of the sport ecommerce.
	 * It is a subclass of {@code People} and it is extended by the class {@code Employee}.
	 * @see People
	 * @see Employee
	 */
	public class Customer extends People {
		private ArrayList<Wine> cart = new ArrayList<Wine>();
		private ArrayList<Order> orders = new ArrayList<Order>();
		//TODO: authentication method
		
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
		 * Gets the name of the {@code Customer}.
		 * @return The name of the user [String].
		 * @see Customer
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Gets the surname (last name) of the {@code Customer}.
		 * @return The surname of the user [String].
		 * @see Customer
		 */
		public String getSurname() {
			return this.surname;
		}

		/**
		 * Gets the email of the {@code Customer}.
		 * @return The email of the user [String].
		 * @see Customer
		 */
		public String getEmail() {
			return this.email;
		}
		
		public void searchByYear(short year){
			for (Wine wine : wines){
				if(wine.getYear() == year){
					wine.printInfo();
				}
			}
		}
		
		public void searchByName(String name){
			for (Wine wine : wines){
				if(wine.getName() == name){
					wine.printInfo();
				}
			}
		}
		
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

		public void removeFromCart(Wine wine){
			this.cart.remove(wine);
		} 
		
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
	 * but it also has the priviledge to operate on the ArrayLists
	 * contained in {@code Ecommerce}.
	 * @see Customer
	 * @see People
	 * @see ArrayList
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
		 * @param name name of the new employee [String]
		 * @param sur surname(last name) of the new employee [String]
		 * @param email email of the new employee [String]
		 * @see Customer
		 */
		public Employee(final String name, final String sur, final String email) {
			super(name, sur, email);
        }
    }

	public class Order {
		private ArrayList<Wine> items = new ArrayList<Wine>();
		private long id;
		

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
		private ArrayList<String> grapewines = new ArrayList<String>();
		private int quantity;

		public Wine(){
			this.name = "";
			this.producer = "";
			this.year = -1;
			this.notes = "";
			this.quantity=-1;
		}

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

		public String getName(){
			return this.name;
		}

		public String getProducer(){
			return this.producer;
		}

		public short getYear(){
			return this.year;
		}
		
		public String getNotes(){
			return this.notes;
		}

		public int getQuantity(){
			return this.quantity;
		}

		public ArrayList<String> getGrapewines(){
			return this.grapewines;
		}

		public void restock(int quantity){
			this.quantity += quantity;
		}

		public void withdraw(int quantity){
			this.quantity -= quantity;
		}


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