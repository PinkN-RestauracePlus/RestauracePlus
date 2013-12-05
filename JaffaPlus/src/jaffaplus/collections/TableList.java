package jaffaplus.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Hanzik
 */
public class TableList implements Iterable<Table> {
    
    private ArrayList<Table> list = new ArrayList<>();
    
    public void add(Table table) {
        list.add(table);
    }
    
    public Table getByNumber(int number) {
        for (Table table : list) {
            if (table.getTableNumber() == number) {
                return table;
            }
        }
        return null;
    }
    
    public void remove(Table table) {
        list.remove(table);
    }
    
    @Override
    public Iterator<Table> iterator() {
        return list.iterator();
    }
    
    /* For testing purposes only */
    public void fillList() {
        Random rand = new Random();
        for (int i = 1; i < 15; i++) {
            add(new Table(i, rand.nextInt(10) + 5));            
        }
    }
}
