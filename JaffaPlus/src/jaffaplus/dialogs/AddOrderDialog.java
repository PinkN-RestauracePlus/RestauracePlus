package jaffaplus.dialogs;

import jaffaplus.collections.Order;
import jaffaplus.collections.Table;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.TablePanel;
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
public class AddOrderDialog extends JDialog {
   
    private Table table;
    private TablePanel panel;
    
    private Panel contentPane = new Panel();
   
    private JTextField orderNameField;
    private Button confirmButton, cancelButton;
    
    private final int FRAME_WIDTH = 250;
    private final int FRAME_HEIGHT = 180;
    private final int FRAME_THICKNESS = 4;
    
    public AddOrderDialog(Table table, TablePanel panel) {
        
        this.table = table;
        this.panel = panel;
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(panel);
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
        
        int textFieldWidth = 150;
        int textFieldHeight = 24;
        
        Panel textFieldPanel = new Panel();
        textFieldPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        
        orderNameField = new JTextField();
        orderNameField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        
        textFieldPanel.add(new JLabel("Název účtu: "));
        textFieldPanel.add(orderNameField);
        
        contentPane.add(textFieldPanel, "alignx center, wrap");
        
        confirmButton = new Button();
        cancelButton = new Button();
        
        confirmButton.addMouseListener(new AddButtonListener(confirmButton));
        cancelButton.addMouseListener(new CancelButtonListener(cancelButton));
        
        confirmButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        cancelButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);
        
        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        contentPane.add(buttonPanel, "alignx center");
    }
    
    public void addOrder() {
        int maxLength = 20;
        String orderName = orderNameField.getText();
        
        if (orderName.length() > maxLength) {
            orderName = orderName.substring(0, maxLength);            
        }
        Order order = new Order(orderName);
        table.addOrder(order);
        
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
            addOrder();
            cancel();
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
