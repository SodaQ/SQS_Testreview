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
import org.junit.Before;

public class TestsToRefactor {

    private Book b1, b2, b3, b4, b5;
    Invoice i;
    Address a;
    Customer c;
    Random r;

    @Before
    public void setUp() {
        a = new Address();
        c = null;
        b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
        b2 = new Book(14.99, "sofies welt", "gaarder", 251);
        b3 = new Book(8.69, "the jungle books", "kipling", 215);
        b4 = new Book(15.99, "great expectations", "dickens", 314);
        b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);
        i = null;
        c = new Customer(a, "franz", "beispiel");
        i = new Invoice();
        i.setCustomer(c);
        r = new Random();
    }

    @Test
    public void test_addSomeBooks() {

        int maxItemOrder = 10;

        int amount = 0;

        try {

            i.setMaxItemOrder(maxItemOrder);
            amount = r.nextInt(15);

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
        }

    }

    @Test
    public void test_addManyItems_getTotalPrice() {

        final int DELIVERY_COSTS_5 = 7;
        final int DELIVERY_COSTS_10 = 5;
        int maxItemOrder = 10;

        List<Item> bookList = new ArrayList<Item>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);

        int amount = 0;

        try {
            i.setMaxItemOrder(maxItemOrder);

            amount = r.nextInt(5) + 1;
            double totalPrice = 0;

            for (Item item : bookList) {
                i.addItems(item, amount);

                if (amount < 5) {
                    totalPrice += amount * item.getPrice() + DELIVERY_COSTS_5;
                } else {
                    totalPrice += amount * item.getPrice() + DELIVERY_COSTS_10;
                }
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
        }
    }

    @Test
    public void test_itemCount_changeMaxItemOrder() {

        int maxItemOrder = 150;

        List<Item> bookList = new ArrayList<Item>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);

        int amount = 0;

        try {

            i.setMaxItemOrder(maxItemOrder);

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
        }
    }

    @Test(expected = Exception.class)
    public void test_addToManyItems() throws ToMuchItemsException {

        int maxItemOrder = 0;

        Invoice i = null;

        int amount = 1;

            i.setMaxItemOrder(maxItemOrder);

            i.addItems(b1, amount);

    }
}