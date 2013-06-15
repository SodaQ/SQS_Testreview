package bsp4_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
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
    private Invoice i;
    private Address a;
    private Customer c;
    private List<Item> bookList = new ArrayList<Item>();
    private int amount;
   
    final int DELIVERY_COSTS_5 = 5;
    final int DELIVERY_COSTS_7 = 7;

    @Before
    public void setUp() {
        a = new Address();
        b1 = new Book(12.5, "das leben des max mustermann", "mustermann", 125);
        b2 = new Book(14.99, "sofies welt", "gaarder", 251);
        b3 = new Book(8.69, "the jungle books", "kipling", 215);
        b4 = new Book(15.99, "great expectations", "dickens", 314);
        b5 = new Book(12.99, "küsschen, küsschen", "dahl", 145);
        c = new Customer(a, "franz", "beispiel");
        i = new Invoice();
        i.setCustomer(c);
        amount = 0;
        
        //BookList    
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
    }

    @Test
    public void test_addSomeBooks_withSuccess() {

        int maxItemOrder = 10;
        amount = 9;
        try {

            i.setMaxItemOrder(maxItemOrder);

            i.addItems(b1, amount);

            assertEquals(i.getItemCount(), amount);
            assertEquals(i.getCustomer().getFirstName(), "franz");
            assertEquals(i.getCustomer().getLastName(), "beispiel");
            assertEquals(i.getCustomer().getAddress(), a);
            assertEquals(i.getMaxItemOrder(), maxItemOrder);

        } catch (Exception e) {
             assertFalse("dieser fehler hätte nicht auftreten sollen", true);
        }

    }

    @Test
    public void test_addManyItems_getTotalPriceWithDilivery7() {
        int maxItemOrder = 10;
        amount = 2;

        try {
            i.setMaxItemOrder(maxItemOrder);

            int totalPrice = 0;

            for (Item item : bookList) {
                i.addItems(item, amount);
                totalPrice += amount * item.getPrice() + DELIVERY_COSTS_7;
            }

            if (totalPrice == i.getTotalPrice()) {
                assertTrue(true);
            }

        } catch (Exception e) {
            assertFalse("dieser fehler hätte nicht auftreten dürfen", true);
        }
    }
    
        @Test
    public void test_addManyItems_getTotalPriceWithDilivery5() {
        int maxItemOrder = 10;
        amount = 8;

        try {
            i.setMaxItemOrder(maxItemOrder);

            int totalPrice = 0;

            for (Item item : bookList) {
                i.addItems(item, amount);
                totalPrice += amount * item.getPrice() + DELIVERY_COSTS_5;
            }

            if (totalPrice == i.getTotalPrice()) {
                assertTrue(true);
            }

        } catch (Exception e) {
            assertFalse("dieser fehler hätte nicht auftreten dürfen", true);
        }
    }

    @Test
    public void test_addManyItems_getItemCount_withSuccess() {

        int maxItemOrder = 150;
        amount = 30;

        try {

            i.setMaxItemOrder(maxItemOrder);
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
       amount = 1;
       i.setMaxItemOrder(maxItemOrder);
       i.addItems(b1, amount);

    }
}