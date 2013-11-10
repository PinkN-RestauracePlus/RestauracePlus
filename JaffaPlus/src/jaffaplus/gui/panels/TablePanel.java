package jaffaplus.gui.panels;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Order;
import jaffaplus.collections.Table;
import jaffaplus.dialogs.AddOrderDialog;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Hanzik
 */
public class TablePanel extends Panel {
    
    private Panel ordersPanel, infoPanel, buttonPanel;
    
    private Table table;
    private TablePanel thisPanel;
    private OrderMiniPanel selectedOrder;
    
    /* Constructor for testing purposes only! */
    public TablePanel() {
        this(1);
    }
    
    public TablePanel(int tableNumber) {
        super();
        thisPanel = this;        
        table = DataStorage.getInstance().getTables().getByNumber(tableNumber);
        table.setPanel(this);
        
        initComponents();
    }    

    private void initComponents() {
        initOrdersPanel();
        initInfoPanel();
        initButtonPanel();
        
        add(ordersPanel, "dock west");
        add(infoPanel, "north");
        add(buttonPanel, "south");
    }

    private void initOrdersPanel() {
        
        int panelWidth = 400;
        int panelHeight = 800;
        
        ordersPanel = new Panel();
        ordersPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));    
        ordersPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(GlobalValues.BORDER_COLOR))); 
           
    }

    private void initInfoPanel() {
        
        int panelWidth = 450;
        int panelHeight = 750;
        
        infoPanel = new Panel();
        infoPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        infoPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(GlobalValues.BORDER_COLOR))); 
        
        JLabel titleLabel = new JLabel("Stůl č. " + table.getTableNumber());
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        infoPanel.add(titleLabel, "wrap");        
        
        infoPanel.add(new JLabel("Kapacita: " + table.getCapacity()), "wrap");
    }

    private void initButtonPanel() {
        
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button addOrderButton = new Button("+");
        Button removeOrderButton = new Button("-");
        Button openOrderButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        addOrderButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeOrderButton.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        openOrderButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        addOrderButton.addMouseListener(new AddButtonListener(addOrderButton));
        removeOrderButton.addMouseListener(new RemoveButtonListener(removeOrderButton));
        openOrderButton.addMouseListener(new DetailsButtonListener(openOrderButton));
        
        buttonPanel.add(addOrderButton);
        buttonPanel.add(removeOrderButton);
        buttonPanel.add(openOrderButton);
        buttonPanel.add(goBackButton);
    }
    
    @Override
    public void repaint() {
        repaintOrdersPanel();
    }
    
    private void repaintOrdersPanel() {
        if (ordersPanel == null) {
            return;
        }
        
        ordersPanel.removeAll();
        for (Order order : table.getOrderList()) {
            ordersPanel.add(new OrderMiniPanel(order, this), "wrap");
        }
        ordersPanel.repaint();
        ordersPanel.revalidate();
    }

    public OrderMiniPanel getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(OrderMiniPanel selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
    
    private class AddButtonListener extends ButtonListener {
                
        private AddButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            AddOrderDialog dialog = new AddOrderDialog(table, thisPanel);
            if (selectedOrder != null) {
                selectedOrder.deselectPanel();
            }
        }
    }
    
    private class RemoveButtonListener extends ButtonListener {        
                
        private RemoveButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedOrder != null) {
                table.removeOrder(selectedOrder.getOrder());   // to be edited
                selectedOrder = null;
            } else {
                //vyhod chybu, ze nejprve je treba vybrat objednavku
            }
        }
    }
    
    private class DetailsButtonListener extends ButtonListener {     
                
        private DetailsButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedOrder != null) {
                PanelSwitcher.getInstance().switchToPanel(new OrderPanel(selectedOrder.getOrder())); 
            } else {
                // hod chybu, ze musime nejprve vybrat objednavku
            }
        }
    }
}
