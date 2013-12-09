package jaffaplus.gui.dialogs;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Supplier;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.SupplierEditorPanel;
import jaffaplus.source.GlobalValues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lukas
 */
public class AddSupplierDialog extends Dialog {
    
    private Panel formPanel;
    private Supplier oldSupplier;
    private boolean editing;
    private JLabel errorLabel = new JLabel("");
    private JTextField nameField, addressField, idField, bankAccField, phoneNumberField;
    
       
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 400;
    
    public AddSupplierDialog(SupplierEditorPanel parentPanel, boolean editing) {
       
        this.editing = editing;
        
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        if (editing) {
            this.oldSupplier = parentPanel.getSelectedSupplier();
            fillTextFields(oldSupplier);            
        }
        
        setVisible(true);
    }

    private void initComponents() {
        add(errorLabel, "push, align center, span, wrap");
        showMessage("Vložte dodavatele: ", false);
        initFormPanel();
        initButtonPanel();
    }
    
    private void initFormPanel() {
        formPanel = new Panel();
        formPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        
        nameField = new JTextField();   
        nameField.addKeyListener(new AddSupplierDialog.TextFieldListener(nameField));
        nameField.setPreferredSize(new Dimension(220, 28));
        formPanel.add(new JLabel("Firma: "));
        formPanel.add(nameField, "span, wrap, grow");
        
        addressField = new JTextField();   
        addressField.addKeyListener(new AddSupplierDialog.TextFieldListener(addressField));    
        addressField.setPreferredSize(new Dimension(60, 28)); 
        formPanel.add(new JLabel("Adresa: "));
        formPanel.add(addressField, "span, wrap, grow");
                
        bankAccField = new JTextField();   
        bankAccField.addKeyListener(new AddSupplierDialog.TextFieldListener(bankAccField));    
        bankAccField.setPreferredSize(new Dimension(60, 28)); 
        formPanel.add(new JLabel("Účet: "));
        formPanel.add(bankAccField, "span, wrap, grow");
        
        idField = new JTextField();  
        idField.addKeyListener(new AddSupplierDialog.TextFieldListener(idField));   
        idField.setPreferredSize(new Dimension(60, 28));        
        formPanel.add(new JLabel("IČO: "));
        formPanel.add(idField, "span, wrap, grow");    
        
        phoneNumberField = new JTextField();  
        phoneNumberField.addKeyListener(new AddSupplierDialog.TextFieldListener(phoneNumberField));   
        phoneNumberField.setPreferredSize(new Dimension(60, 28));        
        formPanel.add(new JLabel("Telefon: "));
        formPanel.add(phoneNumberField, "span, wrap, grow");      
        
        
        
        add(formPanel, "push, align center, wrap");   
    }
    
    private void fillTextFields(Supplier supp) {
        nameField.setText(supp.getName());
        addressField.setText(supp.getAddress());
        idField.setText(supp.getId());
        phoneNumberField.setText(supp.getPhoneNumber());
        bankAccField.setText(supp.getBankAcc());
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
        if ( nameField.getText().length() == 0){
        errorLabel.setText("Dodavatel musí obsahovat jméno");
        nameField.setBackground(Color.red);
        }
        else{
            Supplier newSupp = new Supplier ( nameField.getText(), idField.getText(), addressField.getText(), phoneNumberField.getText(), bankAccField.getText());
            DataStorage.getInstance().getSuppliers().add(newSupp);
            if (editing && oldSupplier != null) {
                DataStorage.getInstance().getSuppliers().remove(oldSupplier);
            }
            cancel();
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
