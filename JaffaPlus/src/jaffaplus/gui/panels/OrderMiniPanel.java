package jaffaplus.gui.panels;

import jaffaplus.collections.Order;
import jaffaplus.source.GlobalValues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class OrderMiniPanel extends Panel {
    
    private boolean selected;
    private Order order;
    private TablePanel table;
    
    private Panel idPanel, namePanel, pricePanel;
        
    private final int PANEL_WIDTH = 350;
    private final int PANEL_HEIGHT = 45;
    private final int MARGIN = 2;
    
    public OrderMiniPanel(Order order, TablePanel table) {
        
        this.order = order;
        this.table = table;
        
        addMouseListener(new MiniPanelListener(this));
        setLayout(new MigLayout());
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        Font font;
        JLabel label;
        
        int clmn1Width = 30;
        int clmn2Width = 250;
        int clmn3Width = 100;
        
        idPanel = new Panel();
        idPanel.setPreferredSize(new Dimension(clmn1Width, PANEL_HEIGHT));
        font = new Font("Calibri", Font.PLAIN, 14);
        label = new JLabel("ID: " + order.getOrderNumber());
        label.setFont(font);
        idPanel.add(label);
        add(idPanel);
        
        add(new JLabel("|"));   
        
        namePanel = new Panel();
        namePanel.setPreferredSize(new Dimension(clmn2Width, PANEL_HEIGHT));
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(order.getOrderName());
        label.setFont(font);
        namePanel.add(label);
        add(namePanel);
                
        add(new JLabel("|"));   
        
        pricePanel = new Panel();
        pricePanel.setPreferredSize(new Dimension(clmn3Width, PANEL_HEIGHT));
        font = new Font("Calibri", Font.BOLD, 16);
        label = new JLabel(order.getTotalPrice() + ",-");
        label.setFont(font);        
        pricePanel.add(label);
        add(pricePanel);
    }
    
    private void changeBackground(Color color) {
        setBackground(color);
        idPanel.setBackground(color);
        namePanel.setBackground(color);
        pricePanel.setBackground(color);        
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (table.getSelectedOrder() != null) {
            table.getSelectedOrder().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        table.setSelectedOrder(this);
        changeBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        table.setSelectedOrder(null);
        changeBackground(GlobalValues.BACKGROUND_COLOR);
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public boolean isSelected() {
        return selected;
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
                PanelSwitcher.getInstance().switchToPanel(new OrderPanel(order)); 
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
