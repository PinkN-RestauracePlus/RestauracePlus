package jaffaplus.gui.dialogs;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Item;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.MenuEditorPanel;
import jaffaplus.source.GlobalValues;
import jaffaplus.tools.FoodFormatValidator;
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
    private Item oldItem;
    private boolean editing;
    private JLabel errorLabel = new JLabel("");
    private JTextField nameField, priceField, idField;
    
    private FoodFormatValidator formatValidator;
    
    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 220;
    
    public AddFoodDialog(MenuEditorPanel parentPanel, boolean editing) {
        this.formatValidator = new FoodFormatValidator();
        this.editing = editing;
        
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        if (editing) {
            this.oldItem = parentPanel.getSelectedItem();
            fillTextFields(oldItem);            
        }
        
        setVisible(true);
    }

    private void initComponents() {
        add(errorLabel, "push, align center, span, wrap");
        showMessage("Vložte položku: ", false);
        initFormPanel();
        initButtonPanel();
    }
    
    private void initFormPanel() {
        formPanel = new Panel();
        formPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        
        nameField = new JTextField();   
        nameField.addKeyListener(new TextFieldListener(nameField));
        nameField.setPreferredSize(new Dimension(220, 28));
        formPanel.add(new JLabel("Název: "));
        formPanel.add(nameField, "span, wrap, grow");
        
        priceField = new JTextField();   
        priceField.addKeyListener(new TextFieldListener(priceField));    
        priceField.setPreferredSize(new Dimension(60, 28)); 
        formPanel.add(new JLabel("Cena: "));
        formPanel.add(priceField, "alignx left");
                
        idField = new JTextField();  
        idField.addKeyListener(new TextFieldListener(idField));   
        idField.setPreferredSize(new Dimension(60, 28));        
        formPanel.add(new JLabel("ID: "));
        formPanel.add(idField, "wrap, push");                
        
        add(formPanel, "push, align center, wrap");   
    }
    
    private void fillTextFields(Item item) {
        nameField.setText(item.getName());
        priceField.setText(Integer.toString(item.getPrice()));
        idField.setText(item.getId());
    }
    
    private void showMessage(String message, boolean isError) {
        if (isError) {
            errorLabel.setForeground(GlobalValues.ERROR_COLOR);            
        } else {
            errorLabel.setForeground(GlobalValues.FONT_COLOR);
        }
        errorLabel.setText(message);
    }
    
    @Override
    public void add() {        
        try {            
            Item newItem = formatValidator.getValidItem(nameField, priceField, idField);
            DataStorage.getInstance().getFoodMenu().add(newItem);
            if (editing && oldItem != null) {
                DataStorage.getInstance().getFoodMenu().remove(oldItem);
            }
            cancel();
        } catch (DialogInputException ex) {
            ex.getExceptionSource().setBackground(GlobalValues.ERROR_COLOR);
            showMessage(ex.getMessage(), true);
        }
    }    
    
    @Override
    public void cancel() {
        dispose();
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
