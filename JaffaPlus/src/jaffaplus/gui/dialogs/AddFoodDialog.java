package jaffaplus.gui.dialogs;

import jaffaplus.collections.Item;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class AddFoodDialog extends Dialog {
    
    private Panel formPanel;
    private JTextField nameField, priceField, idField;
    
    public AddFoodDialog() {
        initComponents();
    }

    private void initComponents() {
        initFormPanel();
        initButtonPanel();
    }
    
    private void initFormPanel() {
        formPanel = new Panel();
        formPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        
        nameField = new JTextField();   
        nameField.addKeyListener(new TextFieldListener(nameField));
        nameField.setPreferredSize(new Dimension(180, 24));
        formPanel.add(new JLabel("Jméno: "));
        formPanel.add(nameField, "wrap, grow");
        
        priceField = new JTextField();   
        priceField.addKeyListener(new TextFieldListener(priceField));    
        priceField.setPreferredSize(new Dimension(80, 24)); 
        formPanel.add(new JLabel("Datum: "));
        formPanel.add(priceField);
                
        idField = new JTextField();  
        idField.addKeyListener(new TextFieldListener(idField));   
        idField.setPreferredSize(new Dimension(50, 24));        
        formPanel.add(new JLabel("Počet osob: "));
        formPanel.add(idField, "wrap, push");
                
        
        add(formPanel, "push, align center, wrap");   
    }
    
    private void fillTextFields(Item item) {
        nameField.setText(item.getName());
        priceField.setText(Integer.toString(item.getPrice()));
        idField.setText(item.getId());
    }
    
    private class TextFieldListener implements KeyListener {

        private JTextField owner;
        
        private TextFieldListener(JTextField owner) {
            this.owner = owner;
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            //do nothing
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //do nothing
        }

        @Override
        public void keyReleased(KeyEvent e) {
            owner.setBackground(GlobalValues.BACKGROUND_COLOR);
        }        
    }
}
