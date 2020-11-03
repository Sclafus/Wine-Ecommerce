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

 public class Ecommerce {

	protected List<Customer> customers = new ArrayList<Customer>();
	protected List<Employee> employees = new ArrayList<Employee>();
	protected List<Wine> wines = new ArrayList<Wine>();
	protected List<Order> ordersToBeProcessed = new ArrayList<Order>();

    /**
	 * Class {@code People} is a class that should never be instantiated.
	 * This class is used as a container for Class {@code Customer} and
	 * its subclass {@code Employee}.
	 * @see Customer
	 * @see Employee
	 */
    public abstract class People {
		
		protected String name;
		protected String surname;
		protected String email;
		protected Boolean authenticated;

		/**
		 * {@code People} Class constructor. This constructor should NEVER
		 * be called.
		 */
		protected People() {
			this.name = "";
			this.surname = "";
			this.email = "";
			this.authenticated = false;
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
			this.authenticated = false;
		}

    }
	
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
		protected Queue<String> pending_notifications = new LinkedList<String>();
		
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
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Gets the surname of the {@code Customer}.
		 * @return The surname of the customer. [String]
		 */
		public String getSurname() {
			return this.surname;
		}

		/**
		 * Gets the email of the {@code Customer}.
		 * @return The email of the customer. [String]
		 */
		public String getEmail() {
			return this.email;
		}
		
		/**
		 * prints all the informations regarding all 
		 * the wines produced in the provided year.
		 * @param year the year of production of the {@code Wine} we are searching. [int]
		 * @see Wine
		 */
		public void searchByYear(int year){
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
		 * Prints all the informations regarding all
		 * the wines with the provided name and year.
		 * @param name the name of the {@code Wine} we are searching. [String]
		 * @param year the year of production of the {@code Wine} we are searching. [int]
		 * @see Wine
		 */
		public void searchByNameAndYear(String name, int year){
			for (Wine wine : wines){
				if(wine.getName() == name && wine.getYear() == year){
					wine.printInfo();
				}
			}
		}
		
		/**
		 * adds to {@code cart} a selected {@code Wine} with the selected quantity.
		 * Warns the {@code Customer} in case the desired quantity is unavailable.  
		 * @param wine the wine the customer wants to buy. [Wine]
		 * @param quantity the quantity of the selected {@code Wine}. [int]
		 * @see Wine
		 */
		public void addToCart(Wine wine, int quantity){

			if(quantity > 0){

				

				if (wine.getQuantity() < quantity){

					System.out.println(new StringBuilder("The selected quantity of ").append(wine.getName())
					.append(" is currently unavailable. You will be notified when it will come back in stock!"));
					wine.addToNotifications(this);

				} else {

					Wine wine_tmp = new Wine(wine.getName(),
					wine.getProducer(), wine.getYear(), wine.getNotes(), quantity, wine.getGrapewines());

					System.out.format("Added %s (%d units) to cart!\n", wine.getName(), quantity);
					this.cart.add(wine_tmp);

				}
			}
		}

		/**
		 * Removes the selected wine from the {@code cart}.
		 * @param wine the wine the customer wants to remove from the {@code cart}. [Wine]
		 * @see Wine
		 */
		public void removeFromCart(Wine wine){
			this.cart.remove(wine);
		} 

		/**
		 * Checks if the current {@code Customer} is authenticated or not.
		 * @return The authentication status of the {@code Customer}. [Boolean]
		 */
		public boolean isAuth(){
			return this.authenticated;
		}

		/**
		 * Toggles the authentication status of the current {@code Customer}.
		 */
		public void toggleAuth(){
			this.authenticated = !this.authenticated;
		}
		
		/**
		 * Buys everything that has been added to the cart. 
		 * If the quantity in the cart is greater than the one in stock,
		 * the customer will be automatically added to the notification 
		 * list for the wine in question.
		 * @see Wine
		 */
		public void buy(){

			if(this.isAuth()){
				for(Wine wine_toBuy : cart){
					for(Wine wine_inStock : wines){

						if((wine_toBuy.getName() == wine_inStock.getName())){

							if (wine_toBuy.getQuantity() > wine_inStock.getQuantity()){

								System.out.println(new StringBuilder("Wine ").append(wine_inStock.getName())
								.append("is currently not available in the selected quantity.")
								.append("You will be notified when we will restock our storage!"));
								wine_inStock.addToNotifications(this);
								cart.remove(wine_toBuy);

							}
						}
					}
				}
				Wine[] new_cart = new Wine[cart.size()];
				new_cart = cart.toArray(new_cart);
				Order order = new Order(new_cart);
				ordersToBeProcessed.add(order);
				this.orders.add(order);
				cart.clear();

			} else {
				System.out.println("You are not authenticated, please authenticate before buying.");
			}
		}

		/**
		 * Adds the specified String to the notification queue.
		 * @param str The String that needs to be added to the queue.
		 */
		public void enqueueNotification(String str){
			this.pending_notifications.add(str);
		}

		/**
		 * Removes the String at the beginning of the notification queue.
		 * @return The String removed from the beginning of the queue. 
		 */
		public String dequeueNotification(){
			return this.pending_notifications.remove();
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
		
		/**
		 * Adds a new {@code Wine} in the {@code wines} list.
		 * @param name name of the {@code Wine}. [String]
		 * @param producer producer of the {@code Wine}. [String]
		 * @param year year of production of the {@code Wine}. [int]
		 * @param notes notes for the the {@code Wine}. [String]
		 * @param quantity quantity of the {@code Wine}. [int]
		 * @param grapes the grapes used for the {@code Wine}. [String Array]
		 * @see Wine
		 */
		public void addWine(final String name, final String producer, final int year,
		final String notes, final int quantity, final String[] grapes){
			Wine wine = new Wine(name, producer, year, notes, quantity, grapes);
			wines.add(wine);
		}

		/**
		 * Restocks the storage of the selected {@code Wine} with the specified quantity. 
		 * @param wine the {@code Wine} that has to be restocked. [Wine]
		 * @param quantity the quantity to restock. [int]
		 * @see Wine
		 */
		public void restockWine(Wine wine, int quantity){
			wine.addQuantity(quantity);
		}

		/**
		 * Withdraws the selected quantity of {@code Wine} from the storage.
		 * @param wine the {@code Wine} that has to be restocked. [Wine]
		 * @param quantity the quantity to restock. [int]
		 * @see Wine
		 */
		public void withdrawWine(Wine wine, int quantity){
			wine.subtractQuantity(quantity);
		}

		/**
		 * Ships all the Wines in the specified {@code Order}.
		 * If the wine in stock is not enough for the selected order, 
		 * the employee must contact the {@code Customer} manually.
		 * @param order the selected order. [Order]
		 * @see Order
		 * @see Wine
		 */
		public void ship(Order order){
			int counter = 0;
			for(Wine wine_toShip : order.getWines()){
				for(Wine wine_inStock : wines){

					if(wine_toShip.getName() == wine_inStock.getName()){
						if (wine_inStock.getQuantity() < wine_toShip.getQuantity()){
							System.out.format("There is not enough %s! Contact the customer.", wine_toShip.getName());
						} else {
							wine_inStock.subtractQuantity(wine_toShip.getQuantity());
							counter++;
						}
					}
				}
			}
			if (counter == order.getWines().length){
				ordersToBeProcessed.remove(order);
			}
		}
    }

	/**
	 * The {@code Order} class is pretty straight forward. Every order has
	 * a list of wines that has an ArrayList of wines and a unique id.
	 * @see Wine
	 */
	public static class Order {

		private ArrayList<Wine> items = new ArrayList<Wine>();
		private static int id;

		/**
		 * {@code Order} class constructor.
		 */
		public Order(){
			++Ecommerce.Order.id; 
		}

		/**
		 * {@code Order} class constructor.
		 * @param wines the wines the {@code Customer} wants to buy. [Wine Array]
		 * @see Wine
		 * @see Customer
		 */
		public Order(final Wine[] wines){
			Collections.addAll(items, wines);
			++Ecommerce.Order.id;
		}

		
		/**
		 * Gets the id of the selected {@code Order}.
		 * @return the id of the {@code Order}. [int]
		 */
		public int getId() {
			return Ecommerce.Order.id;
		}

		/**
		 * Gets the wines from the selected {@code Order}.
		 * @return the wines of the {@code Order}. [Wine Array]
		 * @see Wine
		 */
		public Wine[] getWines(){
			Wine[] wines_arr = new Wine[items.size()]; 
			wines_arr = items.toArray(wines_arr);
			return wines_arr;
		}
	}

	/**
	 * Abstraction of a determined Wine. Every wine has a name, 
	 * a producer, the year of production, some notes that may be useful
	 * for the customers, the list of grapes used, the quantity
	 * and a list of customers subscribed for notifications upon
	 * restock.
	 * @see Customer
	 */
	public class Wine {

		private String name;
		private String producer;
		private int year;
		private String notes;
		private ArrayList<String> grapewines = new ArrayList<String>();
		private int quantity;
		private ArrayList<Customer> notification_customers = new ArrayList<Customer>();

		/**
		 * {@code Wine} class constructor.
		 */
		public Wine(){
			this.name = "";
			this.producer = "";
			this.year = -1;
			this.notes = "";
			this.quantity = -1;
		}

		/**
		 * {@code Wine} class constructor.
		 * @param name name of the wine. [String]
		 * @param producer producer of the wine. [String]
		 * @param year year of production of the wine. [int]
		 * @param notes notes for the wine. [String] 
		 * @param quantity quantity of the wine. [int]
		 * @param grapes list of the grapes. [String array]
		 */
		public Wine(final String name, final String producer, final int year,
		 final String notes, final int quantity, final String[] grapes){

			this.name = name;
			this.producer = producer;
			this.year = year;
			this.notes = notes;
			this.quantity = quantity;
			Collections.addAll(this.grapewines, grapes);
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
		public int getYear(){
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
		 * @return the list of grapewines of the {@code Wine}. [String Array]
		 */
		public String[] getGrapewines(){
			String[] grapes = new String[grapewines.size()];
			grapes = grapewines.toArray(grapes);
			return grapes;
		}

		/**
		 * Gets the list of Customers subscribed with notifications for the {@code Wine}.
		 * @return the list of Customers for the {@code Wine}. [Customer Array]
		 * @see Customer
		 */
		public Customer[] getNotificationCustomers(){
			Customer[] notification_customers_arr = new Customer[notification_customers.size()];
			notification_customers_arr = notification_customers.toArray(notification_customers_arr);
			return notification_customers_arr;
		}

		public void addToNotifications(Customer customer){
			notification_customers.add(customer);
		}

		/**
		 * Adds the specified quantity to the selected {@code Wine}.
		 * Also checks if some customers have to be notified by the restock.
		 * @param quantity the quantity to add. [int]
		 */
		public void addQuantity(int quantity){
			this.quantity += quantity;
			String notification = this.name.concat(" has been restocked.")
			.concat("If you are still interested, you may consider to buy it!\n");

			for (Customer customer : notification_customers){
				if(customer.isAuth()){
					System.out.println(notification);
				} else {
					customer.enqueueNotification(notification);
				}
			}
		}

		/**	
		 * Substracts the specified quantity to the selected {@code Wine}.
		 * @param quantity the quantity to subtract. [int]
		 */
		public void subtractQuantity(int quantity){
			this.quantity -= quantity;
		}


		/**
		 * Prints in console the relevant informations of the selected {@code Wine}.
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
	
	/**
	 * Adds a new {@code Customer} to {@code customers} list.
	 * @param name the name of the new {@code Customer}. [String]
	 * @param surname the surname of the new {@code Customer}. [String]
	 * @param mail the email of the new {@code Customer}. [String]
	 * @see Customer
	 */
	public void register_customer(final String name, final String surname, final String mail) {
		Customer new_cust = new Customer(name, surname, mail);
		customers.add(new_cust);
	}

	/**
	 * Adds a new {@code Employee} to {@code employees} list.
	 * @param name the name of the new {@code Employee}. [String]
	 * @param surname the surname of the new {@code Employee}. [String]
	 * @param mail the email of the new {@code Employee}. [String]
	 * @see Employee
	 */
	public void register_employee(final String name, final String surname, final String mail) {
		Employee new_emp = new Employee(name, surname, mail);
		customers.add(new_emp);
		employees.add(new_emp);
	}

	/**
	 * Authenticates the provided customer.
	 * It uses a really bad method for authentication: it only requires
	 * the email of the customer. This is really bad for security
	 * reasons, but since we don't have a password field, this is the
	 * only workaround we can use. 
	 * @param cust the customer we want to authenticate. [Customer]
	 * @param email the email of the customer we want to check. [String]
	 * @see Customer
	 */
	public void auth(Customer cust, String email){

		if(cust.getEmail() == email && !cust.isAuth()){
			cust.toggleAuth();
		} else if(cust.getEmail() == email && cust.isAuth()){
			System.out.println("You are already authenticated.");
		} else {
			System.out.println("The provided email is invalid.");
		}
		while(!cust.pending_notifications.isEmpty()){
			System.out.println(cust.dequeueNotification());
		}
	}

	public static void main(String argv[]) {
		Ecommerce ecc = new Ecommerce();
		ecc.true_main(ecc);
	}
	
	public void true_main(Ecommerce ecc){
		
		ecc.register_customer("Mario", "Verdi", "mario.verdi@gmail.com");
		ecc.register_customer("Giovanni", "Venti", "gio20@gmail.com");
		ecc.register_customer("Francesco", "Franceschini", "francifrance99@hotmail.com");
		ecc.register_employee("Massimiliano", "De Santis", "maxdesa@libero.it");

		Employee emp = ecc.employees.get(0);
		
		String[] grapes = {"Uve Garganega e Trebbiano", "Sangiovese"};
		emp.addWine("Soave Doc", "Vivaldi", 2019, "Il Soave DOCG Superiore, un vino approcciabile, fruttato, pronto, sapido e di medio corpo, ottimo per esaltare piatti di pesce, pasta al pesto e antipasti di mare.", 456, grapes);
		emp.addWine("Chianti", "Carpineto", 2018, "Il vino Chianti Classico DOCG ha colore rubino brillante, tendente al granato e odore profondamente vinoso. Il gusto è asciutto, sapido tendente con il tempo al morbido vellutato.", 6, grapes);

		Customer cust0 = ecc.customers.get(0);
		auth(cust0, "mario.verdi@gmail.com");
		cust0.addToCart(ecc.wines.get(0), 10);
		cust0.buy();
		
		Customer cust1 = ecc.customers.get(1);
		auth(cust1, "gio20@gmail.com");

		cust1.addToCart(ecc.wines.get(1), 6);
		cust1.buy();

		Customer cust2 = ecc.customers.get(2);
		auth(cust2, "francifrance99@hotmail.com");
		cust2.addToCart(ecc.wines.get(1), 18);
		cust2.buy();
		
		emp.restockWine(ecc.wines.get(1), 500);
	}
}