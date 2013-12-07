package jaffaplus.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Lukas
 */
public class SuppliersList implements Iterable<Supplier> {

    private ArrayList<Supplier> list = new ArrayList<>();
    

    public void add(Supplier supp) {
        list.add(supp);
    }

    public Supplier getByID(String id) {
        for (Supplier supp : list) {
            if (supp.getId().equals(id)) {
                return supp;
            }
        }
        return null;
    }

    public Supplier getByName(String name) {
        for (Supplier supp : list) {
            if (supp.getName().equals(name)) {
                return supp;
            }
        }
        return null;
    }
    
    public SuppliersList getByFirstLetter(char firstLetter) {
        SuppliersList newlist = new SuppliersList();
        String key = String.valueOf(firstLetter);
        
        for (Supplier supp : list) {
            if (supp.getName().toLowerCase().startsWith(key)) {
                newlist.add(supp);
            }
        }
        return newlist;
    }
    
    public SuppliersList getBySubstring(String name) {
        SuppliersList newlist = new SuppliersList();
        for (Supplier supp : list) {
            if (supp.getName().toLowerCase().contains(name)) {
                newlist.add(supp);
            }
        }
        return newlist;
    }

    public void sortByName() {
        Collections.sort(list, new SuppliersList.NameComparator());
    }
    
    public void remove(Supplier supp) {
        
        list.remove(supp);
    }

    

    /* For testing purposes only */
    public void fillList() {
        add(new Supplier("Potraviny Goliáš", "984571", "Před Vodárnou 5, Praha 7, 17000", "847562147/0300"));
        add(new Supplier("Nápoje Jelínek", "48750", "U přejeté srnky 12, Praha 2, 12000", "94752014/0100"));
        
        sortByName();
    }

    @Override
    public String toString() {
        return "SuppliersList{" + "list=" + list + '}';
    }
    
    @Override
    public Iterator<Supplier> iterator() {
        return list.iterator();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /* Comparator that sorts supps by name. */
    private class NameComparator implements Comparator<Supplier> {
        
        @Override
        public int compare(Supplier i1, Supplier i2) {
            String name1 = i1.getName().toLowerCase();
            String name2 = i2.getName().toLowerCase();
            return name1.compareTo(name2);
        }
    }
}
