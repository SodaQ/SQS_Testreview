package bsp4_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import bsp4_1.model.Address;
import bsp4_1.model.Book;
import bsp4_1.model.Customer;
import bsp4_1.model.Invoice;
import bsp4_1.model.Item;
import bsp4_1.model.ToMuchItemsException;

public class TestsToRefactor {

	@Test
	public void test_addSomeBooks() {

		Address a = null;
		Customer c = null;
		final int DELIVERY_COSTS_5 = 7;
		final int DELIVERY_COSTS_10 = 5;
		Book b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
		Book b2 = new Book(14.99, "sofies welt", "gaarder", 251);
		Book b3 = new Book(8.69, "the jungle books", "kipling", 215);
		Book b4 = new Book(15.99, "great expectations", "dickens", 314);
		Book b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);
		int maxItemOrder = 10;

		Invoice i = null;
		int amount = 0;

		try {
			a = new Address();
			c = new Customer(a, "franz", "beispiel");
			i = new Invoice();
			i.setCustomer(c);
			i.setMaxItemOrder(maxItemOrder);

			Random r = new Random();
			amount = r.nextInt(15) + 1;

			i.addItems(b1, amount);

			assertEquals(i.getItemCount(), amount);
			assertEquals(i.getCustomer().getFirstName(), "franz");
			assertEquals(i.getCustomer().getLastName(), "beispiel");
			assertEquals(i.getCustomer().getAddress(), a);
			assertEquals(i.getMaxItemOrder(), maxItemOrder);

		} catch (Exception e) {
			if (amount < 10) {
				assertFalse("dieser fehler hätte nicht auftreten sollen", true);
			} else {
				assertTrue("es wurden zuviele Items hinzugefügt", true);
			}
		} finally {
			a = null;
			c = null;
			b1 = null;
			b2 = null;
			b3 = null;
			b4 = null;
			b5 = null;
			i = null;
			amount = 0;
		}

	}

	@Test
	public void test_addManyItems_getTotalPrice() {

		final int DELIVERY_COSTS_5 = 7;
		final int DELIVERY_COSTS_10 = 5;
		Address a = null;
		Customer c = null;
		int maxItemOrder = 10;
		Book b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
		Book b2 = new Book(14.99, "sofies welt", "gaarder", 251);
		Book b3 = new Book(8.69, "the jungle books", "kipling", 215);
		Book b4 = new Book(15.99, "great expectations", "dickens", 314);
		Book b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);

		List<Item> bookList = new ArrayList<Item>();
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);

		Invoice i = null;
		int amount = 0;

		try {
			a = new Address();
			c = new Customer(a, "franz", "beispiel");

			i = new Invoice();
			i.setCustomer(c);
			i.setMaxItemOrder(maxItemOrder);

			Random r = new Random();
			amount = r.nextInt(5) + 1;
			double totalPrice = 0;

			for (Item item : bookList) {
				i.addItems(item, amount);

				if (amount < 5)
					totalPrice += amount * item.getPrice() + DELIVERY_COSTS_5;
				else
					totalPrice += amount * item.getPrice() + DELIVERY_COSTS_10;
			}

			if (totalPrice == i.getTotalPrice()) {
				assertTrue(true);
			}

		} catch (Exception e) {
			if (amount < 10) {
				assertFalse("dieser fehler hätte nicht auftreten dürfen", true);
			} else {
				assertTrue("es wurden zuviele Items hinzugefügt", true);
			}
		} finally {
			a = null;
			c = null;
			b1 = null;
			b2 = null;
			b3 = null;
			b4 = null;
			b5 = null;
			i = null;
			amount = 0;
		}
	}

	@Test
	public void test_itemCount_changeMaxItemOrder() {

		final int DELIVERY_COSTS_5 = 7;
		final int DELIVERY_COSTS_10 = 5;
		Address a = null;
		Customer c = null;
		Book b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
		Book b2 = new Book(14.99, "sofies welt", "gaarder", 251);
		Book b3 = new Book(8.69, "the jungle books", "kipling", 215);
		Book b4 = new Book(15.99, "great expectations", "dickens", 314);
		Book b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);
		int maxItemOrder = 150;

		List<Item> bookList = new ArrayList<Item>();
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);

		Invoice i = null;

		int amount = 0;

		try {
			a = new Address();
			c = new Customer(a, "franz", "beispiel");

			i = new Invoice();
			i.setCustomer(c);
			i.setMaxItemOrder(maxItemOrder);

			Random r = new Random();
			amount = r.nextInt(30) + 1;
			int itemCount = 0;
			for (Item item : bookList) {
				i.addItems(item, amount);
				itemCount += amount;
			}

			if (itemCount == i.getItemCount()) {
				assertTrue(true);
			} else {
				assertFalse(true);
			}

		} catch (Exception e) {
			assertFalse(true);
		} finally {
			a = null;
			c = null;
			b1 = null;
			b2 = null;
			b3 = null;
			b4 = null;
			b5 = null;
			i = null;
			amount = 0;
		}
	}

	@Test(expected = Exception.class)
	public void test_addToManyItems() throws ToMuchItemsException {

		final int DELIVERY_COSTS_5 = 7;
		final int DELIVERY_COSTS_10 = 5;
		Address a = null;
		Customer c = null;
		Book b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
		Book b2 = new Book(14.99, "sofies welt", "gaarder", 251);
		Book b3 = new Book(8.69, "the jungle books", "kipling", 215);
		Book b4 = new Book(15.99, "great expectations", "dickens", 314);
		Book b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);
		int maxItemOrder = 0;

		Invoice i = null;

		int amount = 1;

		try {
			a = new Address();
			c = new Customer(a, "franz", "beispiel");

			i = new Invoice();
			i.setCustomer(c);
			i.setMaxItemOrder(maxItemOrder);

			i.addItems(b1, amount);

		} finally {
			a = null;
			c = null;
			b1 = null;
			b2 = null;
			b3 = null;
			b4 = null;
			b5 = null;
			i = null;
			amount = 0;
		}
	}
}
