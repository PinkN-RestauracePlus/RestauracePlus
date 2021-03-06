package jaffaplus;

import jaffaplus.collections.BookingList;
import jaffaplus.collections.DataStorage;
import jaffaplus.collections.ItemList;
import jaffaplus.collections.SuppliersList;
import jaffaplus.collections.TableList;

/**
 * Tato trida nacte mnoho potrebnych veci pri startu aplikace.
 * 
 * @author Hanzik
 */
public class AppInitiator {
    
    public AppInitiator() {        
        initCollections();
    }        
    
    private void initCollections() {
        
        ItemList foodMenu = new ItemList();
        foodMenu.fillList();
        TableList tableList = new TableList();
        tableList.fillList();
        BookingList bookingList = new BookingList();
        bookingList.fillList();
        SuppliersList suppliers = new SuppliersList();
        suppliers.fillList();
                
        DataStorage.getInstance().setFoodMenu(foodMenu);
        DataStorage.getInstance().setTables(tableList);
        DataStorage.getInstance().setBooking(bookingList);
        DataStorage.getInstance().setSuppliers(suppliers);
    }
}
