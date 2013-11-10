package jaffaplus.gui.panels;

import jaffaplus.collections.Item;
import jaffaplus.source.GlobalValues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class ItemPanel extends Panel {

    private boolean selected;
    private Item item;
    private FoodSelectionPanel panel;
    
    private Panel idPanel, namePanel, pricePanel;
    
    private final static int PANEL_WIDTH = 395;
    private final static int PANEL_HEIGHT = 45;
    
    public ItemPanel(Item item, FoodSelectionPanel panel) {
        this.item = item;   
        this.panel = panel;
        
        addMouseListener(new ItemPanelListener(this));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        //Sirka jednotlivych sloupcu(polozek)
        int clmn1Width = 50;
        int clmn2Width = 200;
        int clmn3Width = 50;
        
        idPanel = new Panel();
        idPanel.setPreferredSize(new Dimension(clmn1Width, PANEL_HEIGHT));
        idPanel.add(new JLabel(item.getId()));
        add(idPanel);
        
        add(new JLabel(" | "));        
        
        namePanel = new Panel();
        namePanel.setPreferredSize(new Dimension(clmn2Width, PANEL_HEIGHT));
        namePanel.add(new JLabel(item.getName()));
        add(namePanel);
        
        add(new JLabel(" | "));
        
        pricePanel = new Panel();
        pricePanel.setPreferredSize(new Dimension(clmn3Width, PANEL_HEIGHT));
        pricePanel.add(new JLabel(Integer.toString(item.getPrice()) + ",-"));
        add(pricePanel);        
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (panel.getSelectedFood()!= null) {
            panel.getSelectedFood().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        panel.setSelectedFood(this);
        changeBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        panel.setSelectedFood(null);
        changeBackground(GlobalValues.BACKGROUND_COLOR);        
    }
    
    private void changeBackground(Color color) {
        setBackground(color);
        idPanel.setBackground(color);
        namePanel.setBackground(color);
        pricePanel.setBackground(color);
    }
    
    public Item getItem() {
        return item;
    }
    
    public static int getPanelWidth() {
        return PANEL_WIDTH;
    }
    
    public static int getPanelHeight() {
        return PANEL_HEIGHT;
    }
    
    private class ItemPanelListener extends PanelListener {
        
        private ItemPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selected) {
                deselectPanel();
            } else {
                selectPanel();
            }
        }
    }
}
