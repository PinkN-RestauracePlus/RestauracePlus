package jaffaplus.gui.panels;

import jaffaplus.collections.Item;
import jaffaplus.collections.Order;
import jaffaplus.gui.dialogs.AddFoodFromMenuDialog;
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
public class OrderPanel extends Panel {
    
    private Panel foodPanel, infoPanel, buttonPanel;
    
    private JLabel totalPriceLabel;
    
    private Order order;
    private OrderPanel thisPanel;
    private FoodMiniPanel selectedFood;
    
    public OrderPanel(Order order) {
        this.order = order;
        thisPanel = this;
        
        initComponents();
    }

    private void initComponents() {
        initOrdersPanel();
        initInfoPanel();
        initButtonPanel();
        
        add(foodPanel, "dock west");
        add(infoPanel, "north");
        add(buttonPanel, "south");
    }

    private void initOrdersPanel() {
        
        int panelWidth = 350;
        int panelHeight = 800;
        
        foodPanel = new Panel();
        foodPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));    
        foodPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(GlobalValues.BORDER_COLOR))); 
           
    }

    private void initInfoPanel() {
        
        int panelWidth = 450;
        int panelHeight = 750;
        
        infoPanel = new Panel();
        infoPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        infoPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(GlobalValues.BORDER_COLOR))); 
        
        JLabel titleLabel = new JLabel("Objednávka č.: " + order.getOrderNumber());
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        infoPanel.add(titleLabel, "wrap");        
        infoPanel.add(new JLabel("Název: " + order.getOrderName()), "wrap");    
        
        totalPriceLabel = new JLabel("Celková cena: " + order.getTotalPrice() + ",-");
        infoPanel.add(totalPriceLabel, "wrap");
    }

    private void initButtonPanel() {
        
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button addFoodButton = new Button("+");
        Button removeFoodButton = new Button("-");
        Button payFoodButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_TABLE);
        
        addFoodButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeFoodButton.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        payFoodButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        addFoodButton.addMouseListener(new AddButtonListener(addFoodButton));
        removeFoodButton.addMouseListener(new RemoveButtonListener(removeFoodButton));
//        payFoodButton.addMouseListener(new DetailsButtonListener());
        
        buttonPanel.add(addFoodButton);
        buttonPanel.add(removeFoodButton);
        buttonPanel.add(payFoodButton);
        buttonPanel.add(goBackButton);
    }
    
    @Override
    public void repaint() {
        repaintFoodPanel();
        repaintInfoPanel();
    }
    
    private void repaintFoodPanel() {
        if (foodPanel == null) {
            return;
        }
        
        foodPanel.removeAll();
        for (Item item : order.getItemList()) {
            foodPanel.add(new FoodMiniPanel(item, this), "wrap");
        }
        foodPanel.repaint();
        foodPanel.revalidate();
    }
    
    private void repaintInfoPanel() {
        if (foodPanel == null) {
            return;
        }
        totalPriceLabel.setText("Celková cena: " + Integer.toString(order.getTotalPrice()) + ",-");
    }


    public FoodMiniPanel getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(FoodMiniPanel selectedFood) {
        this.selectedFood = selectedFood;
    }
    
    private class AddButtonListener extends ButtonListener {
                
        private AddButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            AddFoodFromMenuDialog dialog = new AddFoodFromMenuDialog(order, thisPanel);
            if (selectedFood != null) {
                selectedFood.deselectPanel();
            }
        }
    }
    
    
    private class RemoveButtonListener extends ButtonListener {
                
        private RemoveButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedFood != null) {
                order.getItemList().remove(selectedFood.getFood());
                selectedFood = null;
                repaint();
            }
        }
    }
}
