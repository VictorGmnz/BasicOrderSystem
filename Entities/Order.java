package Entities;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Entities.Enums.OrderStatus;

public class Order {
	private LocalDateTime moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList();
	
	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Order(LocalDateTime moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	public LocalDateTime getMoment() {
		return moment;
	}
	
	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	
	public Double total() {
		double sum = 0.0;
		for(OrderItem tuple : items) {
			sum += tuple.subTotal();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Date birthDate = client.getBirthDate();
		sb.append("Order moment: " + moment.format(dtf1) + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client);
		if(items.size() > 0) {
			sb.append("Order items: \n");
			for(OrderItem tuple : items) {
				sb.append(tuple.toString() + "\n");
			}
			sb.append("Total price: $");
			sb.append(String.format("%.2f", total()));
		}
		return sb.toString();
	}
	
	
}
