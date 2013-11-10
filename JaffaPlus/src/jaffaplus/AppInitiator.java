package jaffaplus;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.ItemList;
import jaffaplus.collections.TableList;

/**
 * Tato trida nacte mnoho potrebnych veci pri startu aplikace.
 * 
 * @author Hanzik
 */
public class AppInitiator {
    
    public AppInitiator() {
        
        initObjects();
    }    
    
    
    private void initObjects() {
        
        ItemList foodMenu = new ItemList();
        foodMenu.fillList();
        TableList tableList = new TableList();
        tableList.fillList();
        
        DataStorage.getInstance().setFoodMenu(foodMenu);
        DataStorage.getInstance().setTables(tableList);
    }
}
