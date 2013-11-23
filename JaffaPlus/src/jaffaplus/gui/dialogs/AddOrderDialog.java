package jaffaplus.gui.dialogs;

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
public class AddOrderDialog extends Dialog {
   
    private Table table;
    private TablePanel panel;
   
    private JTextField orderNameField;
    private Button confirmButton, cancelButton;
    
    private final int FRAME_WIDTH = 250;
    private final int FRAME_HEIGHT = 180;
    
    public AddOrderDialog(Table table, TablePanel panel) {
        
        super();
        this.table = table;
        this.panel = panel;
        setLocationRelativeTo(panel);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        setVisible(true);
    }

    private void initComponents() {
        initFormPanel();
        initButtonPanel();
    }
    
    private void initFormPanel() {
        
        int textFieldWidth = 150;
        int textFieldHeight = 24;
        
        Panel textFieldPanel = new Panel();
        textFieldPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        
        orderNameField = new JTextField();
        orderNameField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        
        textFieldPanel.add(new JLabel("Název účtu: "));
        textFieldPanel.add(orderNameField);
        
        getContentPane().add(textFieldPanel, "alignx center, wrap");        
    }
    
    @Override
    public void add() {
        int maxLength = 20;
        String orderName = orderNameField.getText();
        
        if (orderName.length() > maxLength) {
            orderName = orderName.substring(0, maxLength);            
        }
        Order order = new Order(orderName);
        table.addOrder(order);
        
        if (panel != null) {
            panel.repaint();
        }
    }
    
    @Override
    public void cancel() {
        dispose();
    }
}
