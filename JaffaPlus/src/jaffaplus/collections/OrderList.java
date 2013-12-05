package jaffaplus.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hanzik
 */
public class OrderList implements Iterable<Order> {
    
    private ArrayList<Order> list = new ArrayList<>();
    
    public void add(Order order) {
        list.add(order);
    }
    
    public Order getByOrderNumber(int id) {
        for (Order order : list) {
            if (order.getOrderNumber()== id) {
                return order;
            }
        }
        return null;
    }
    
    public int size() {
        return list.size();
    }
    
    public void remove(Order order) {
        list.remove(order);
    }

    @Override
    public Iterator<Order> iterator() {
        return list.iterator();
    }
}
