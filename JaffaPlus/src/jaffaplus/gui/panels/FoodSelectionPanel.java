package jaffaplus.gui.panels;

import jaffaplus.collections.Item;
import jaffaplus.collections.ItemList;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class FoodSelectionPanel extends Panel {

    private ItemList list;
    
    private ItemPanel foodSelected;    
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 350;
    
    public FoodSelectionPanel(ItemList list) {
        this.list = list;
        
        setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        
        add(new JLabel("Seznam jídel"), "wrap");
        
        showFoodFromList();
    }

    private void showFoodFromList() {
        removeAll();
        
        int itemsAdded = 0;
        for (Item item : list) {
            ItemPanel panel = new ItemPanel(item, this);
            add(panel, "wrap");
            itemsAdded++;
        }
        calculatePanelHeight(itemsAdded);
        
        repaint();
        revalidate();
    }
    
    private void calculatePanelHeight(int itemsAdded) {
        setPreferredSize(new Dimension(ItemPanel.getPanelWidth(), ItemPanel.getPanelHeight() * itemsAdded));
    }    

    public ItemPanel getSelectedFood() {
        return foodSelected;
    }

    public void setSelectedFood(ItemPanel foodSelected) {
        this.foodSelected = foodSelected;
    }
}
