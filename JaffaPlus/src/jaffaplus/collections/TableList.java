package jaffaplus.collections;

import java.util.ArrayList;

/**
 *
 * @author Hanzik
 */
public class TableList {
    
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
    
    /* For testing purposes only */
    public void fillList() {
        for (int i = 0; i < 10; i++) {
            add(new Table(i, 10));
        }
    }
}
