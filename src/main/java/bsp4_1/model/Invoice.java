package bsp4_1.model;

import java.util.HashMap;

public class Invoice {

	private HashMap<Item, Integer> items = new HashMap<Item, Integer>();

	private Customer customer;

	private int maxItemOrder = 10;

	public Invoice() {

	}

	private int deliveryCosts;

	public void addItems(Item item, int amount) throws ToMuchItemsException {
		checkAmount(item, amount);
		if (items.containsKey(item)) {
			items.put(item, items.get(item) + amount);
		} else {
			items.put(item, amount);
		}
		if (getItemCount() > 5) {
			deliveryCosts = 5;
		} else {
			deliveryCosts = 7;
		}
	}

	private void checkAmount(Item item, int amount) throws ToMuchItemsException {
		if (amount > maxItemOrder || (items.get(item) != null && items.get(item) + amount > maxItemOrder)) {
			throw new ToMuchItemsException();
		}
	}

	public double getTotalPrice() {
		double count = 0;
		for (Item item : items.keySet()) {
			count += item.getPrice() * items.get(item);
		}
		return count + deliveryCosts;
	}

	public HashMap<Item, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Item, Integer> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getItemCount() {

		int itemCount = 0;

		for (Integer i : items.values()) {
			itemCount += i;
		}

		return itemCount;
	}

	public void setMaxItemOrder(int maxItemOrder) {
		this.maxItemOrder = maxItemOrder;
	}

	public int getMaxItemOrder() {
		return maxItemOrder;
	}
}