package jaffaplus.gui.panels;

import jaffaplus.collections.Table;
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
public class TableMiniPanel extends Panel {
    
    private boolean selected;
    
    private Table table;
    private HousePanel parent;

    private final int PANEL_WIDTH = 180;
    private final int PANEL_HEIGHT = 80;
    
    public TableMiniPanel(Table table, HousePanel parent) {
        this.table = table;
        this.parent = parent;
        
        addMouseListener(new MiniPanelListener(this));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }
    
    private void initComponents() {
        add(new JLabel("Stůl č. " + table.getTableNumber()), "wrap");
        add(new JLabel("Kapacita: " + table.getCapacity()), "wrap"); 
        add(new JLabel("Aktivních objednávek: " + table.getOrderList().size()));         
    }
    
    private void changeBackground(Color color) {
        setBackground(color);      
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (parent.getSelectedPanel() != null) {
            parent.getSelectedPanel().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        parent.setSelectedPanel(this);
        changeBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        parent.setSelectedPanel(null);
        changeBackground(GlobalValues.BACKGROUND_COLOR);
    }    

    public Table getTable() {
        return table;
    }
        
    private class MiniPanelListener extends PanelListener {

        private double lastClicked = 0;
        private double DELAY = 500;
        
        private MiniPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            double currentClick = System.currentTimeMillis();
            
            if (currentClick - lastClicked < DELAY) {
                PanelSwitcher.getInstance().switchToPanel(new TablePanel(table.getTableNumber())); 
            }
            lastClicked = currentClick;
                        
            if (selected) {
                deselectPanel();
            } else {
                selectPanel();
            }
        }
    }    
}
