package jaffaplus.gui.dialogs;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Order;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.panels.FoodSelectionPanel;
import jaffaplus.gui.panels.OrderPanel;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Hanzik
 */
public class AddFoodDialog extends Dialog {
   
    private Order order;
    private OrderPanel panel;
    
    private Panel searchPanel, buttonPanel;
    private FoodSelectionPanel foodSelectionPanel;
    
    private JScrollPane scrollPane;
    
    private JTextField orderNameField;
    private Button confirmButton, cancelButton;
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 500;
    
    public AddFoodDialog(Order order, OrderPanel panel) {
        
        super();
        this.order = order;
        this.panel = panel;
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        setVisible(true);
    }

    private void initComponents() {
        
        initSearchPanel();
        initFoodSelectionPanel();
        initButtonPanel();
    }
    
    private void initSearchPanel() {     
        
        int SEARCH_WIDTH = 400;
        int SEARCH_FIELD_WIDTH = 300;
        int SEARCH_HEIGHT = 20;
        
        searchPanel = new Panel();
        searchPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        searchPanel.setPreferredSize(new Dimension(SEARCH_WIDTH, SEARCH_HEIGHT));
        
        JLabel searchLabel = new JLabel("Hledat: ");
        JTextField searchField = new JTextField();
        
        searchField.setPreferredSize(new Dimension(SEARCH_FIELD_WIDTH, SEARCH_HEIGHT));        
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, "wrap");
    }
    
    private void initFoodSelectionPanel() {        
        
        int SELECTION_WIDTH = 400;
        int SELECTION_HEIGHT = 350;
        
        foodSelectionPanel = new FoodSelectionPanel(DataStorage.getInstance().getFoodMenu());
                
        scrollPane = new JScrollPane(foodSelectionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(GlobalValues.SCROLL_SPEED);    
//        scrollPane.setVerticalScrollBar(...);             //prepared for custom scroll bar visuals
        scrollPane.setPreferredSize(new Dimension(SELECTION_WIDTH, SELECTION_HEIGHT));
        scrollPane.setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
                              
        getContentPane().add(scrollPane, "alignx center, wrap");        
    }
    
    @Override
    public void add() {
        //Pokud je vybrane jidlo, pridej ho na seznam
        if (foodSelectionPanel.getSelectedFood() != null) {
            order.addItem(foodSelectionPanel.getSelectedFood().getItem());
        }
        
        if (panel != null) {
            panel.repaint();
        } else {
            System.out.println("null");
        }
    }    
    
    @Override
    public void cancel() {
        dispose();
    }
}