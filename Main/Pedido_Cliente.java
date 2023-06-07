package Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Client;
import Entities.Order;
import Entities.OrderItem;
import Entities.Product;
import Entities.Enums.OrderStatus;

public class Pedido_Cliente {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		LocalDateTime actualDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter the client data: ");
		
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Email: ");
		String email = sc.nextLine();

		System.out.print("Birth Date (DD/MM/YYYY): ");
        LocalDate birthDateUnformat = LocalDate.parse(sc.nextLine(), dtf1); // Formatting the birthDate to the pattern setted before
        Date birthDate = java.sql.Date.valueOf(birthDateUnformat); // Converting the LocalDate for Date ('birthDate' was created in Client Class with Date type just for have an example how to do this convertion)
        
        Client client = new Client(name,email,birthDate);
        
        System.out.println("Enter order data: ");
        
        System.out.print("Status: ");
        String status = sc.nextLine();
        
        System.out.print("How many items to this order? ");
        int itemsQuantity = sc.nextInt();
        
        Order order = new Order(actualDate, OrderStatus.valueOf(status), client);
        
        for(int i = 1; i <= itemsQuantity; i++) {
        	sc.nextLine();
        	System.out.println("Enter #"+i+" item data: ");
        	
        	System.out.print("Product name: ");
        	String productName = sc.nextLine();
        	
        	System.out.print("Product price: ");
        	Double productPrice = sc.nextDouble();
        	
           	System.out.print("Quantity: ");
        	int quantity = sc.nextInt();
        	
        	Product product = new Product(productName, productPrice); // Creating a new Product
        	
        	OrderItem oi = new OrderItem(quantity, productPrice, product); // Creating an order item using the product created before in the constructor params
        	
        	order.addItem(oi); // Finally adding in the list of the client orders the order item created before
        }
        
        System.out.println("ORDER SUMMARY: ");
        
        System.out.println(order.toString());
        
		sc.close();

	}

}
