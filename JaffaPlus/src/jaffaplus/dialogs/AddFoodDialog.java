package jaffaplus.dialogs;

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
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class AddFoodDialog extends JDialog {
   
    private Order order;
    private OrderPanel panel;
    
    private Panel contentPane = new Panel();
    private Panel searchPanel, buttonPanel;
    private FoodSelectionPanel foodSelectionPanel;
    
    private JScrollPane scrollPane;
    
    private JTextField orderNameField;
    private Button confirmButton, cancelButton;
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 500;
    private final int FRAME_THICKNESS = 4;
    
    public AddFoodDialog(Order order, OrderPanel panel) {
        
        this.order = order;
        this.panel = panel;
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(250, 200);
        setModal(true);
        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setUndecorated(true);
        
        initComponents();
        
        setVisible(true);
    }

    private void initComponents() {
        contentPane.setLayout(new MigLayout());
        contentPane.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        contentPane.setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR, FRAME_THICKNESS));
        
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
                              
        contentPane.add(scrollPane, "alignx center, wrap");        
    }
    
    private void initButtonPanel() {
        confirmButton = new Button();
        cancelButton = new Button();
        
        confirmButton.addMouseListener(new AddButtonListener(confirmButton));
        cancelButton.addMouseListener(new CancelButtonListener(cancelButton));
        
        confirmButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        cancelButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);
        
        buttonPanel = new Panel();
        buttonPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        contentPane.add(buttonPanel, "alignx center");        
    }
    
    public void addFood() {
        //Pokud je vybrane jidlo, pridej ho na seznam
        if (foodSelectionPanel.getSelectedFood() != null) {
            order.getItemList().add(foodSelectionPanel.getSelectedFood().getItem());
        }
        
        if (panel != null) {
            panel.repaint();
        } else {
            System.out.println("null");
        }
    }    
    
    public void cancel() {
        dispose();
    }
    
    private class AddButtonListener extends ButtonListener {

        private AddButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            addFood();
        }
    }
    
    private class CancelButtonListener extends ButtonListener {
        
        private CancelButtonListener(Button button) {
            super(button);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            cancel();
        }
    }
}