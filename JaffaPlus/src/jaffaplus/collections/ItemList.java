package jaffaplus.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hanzik
 */
public class ItemList implements Iterable<Item> {

    private ArrayList<Item> list = new ArrayList<>();
    private int totalPrice = 0;

    public void add(Item item) {
        totalPrice += item.getPrice();
        list.add(item);
    }

    public Item getByID(String id) {
        for (Item item : list) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public Item getByName(String name) {
        for (Item item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void remove(Item item) {
        totalPrice -= item.getPrice();
        list.remove(item);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    /* For testing purposes only */
    public void fillList() {
        add(new Item("Hovězí guláš", "M12", 80));
        add(new Item("Segedýnský guláš", "M13", 70));
        add(new Item("Svíčková na smetaně", "M14", 85));
        add(new Item("Pekingské kuře", "M15", 95));
        add(new Item("Mohagonský klokan", "G01", 150));
        add(new Item("Steak gigantaura", "G03", 145));
//        add(new Item("Hloupá žirafa", "G05", 299));
//        add(new Item("Polévka Gaspačo", "HOT1", 49));
//        add(new Item("Bobika", "B02", 75));
//        add(new Item("Hafburger", "B03", 35));
//        add(new Item("Pravý chotěbořský koňopes", "B04", 50));
    }

    @Override
    public String toString() {
        return "ItemList{" + "list=" + list + '}';
    }
    
    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }
}
