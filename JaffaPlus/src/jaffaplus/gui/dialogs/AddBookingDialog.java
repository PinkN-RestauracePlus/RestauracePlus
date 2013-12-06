package jaffaplus.gui.dialogs;

import jaffaplus.calendar.MonthsOfYear;
import jaffaplus.collections.Booking;
import jaffaplus.collections.DataStorage;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.calendar.BookingPanel;
import jaffaplus.source.GlobalValues;
import jaffaplus.tools.BookingFormatValidator;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Dialogove okno pro pridani rezervace.
 * 
 * @author Hanzik
 */
public class AddBookingDialog extends Dialog {
   
    private BookingPanel panel;
    private Booking oldBooking;
    private boolean editing;
    private JLabel errorLabel = new JLabel("");
    private JTextField nameField, dateField, hourField, tableField, numberField;
    
    private Panel formPanel;
    
    private BookingFormatValidator formatValidator;
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 280;
    
    public AddBookingDialog(BookingPanel panel, boolean editing) {
        
        super();
        this.panel = panel;
        this.editing = editing;
        this.formatValidator = new BookingFormatValidator();
        
        setLocationRelativeTo(panel);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        if (editing) {
            this.oldBooking = panel.getSelectedBooking();
            fillTextFields(panel.getSelectedBooking());            
        }
        
        setVisible(true);
    }    

    private void initComponents() {
        add(errorLabel, "push, align center, wrap");
        showMessage("Vložte rezervaci: ", false);
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
        
        dateField = new JTextField();   
        dateField.addKeyListener(new TextFieldListener(dateField));    
        dateField.setPreferredSize(new Dimension(80, 24)); 
        formPanel.add(new JLabel("Datum: "));
        formPanel.add(dateField);
                
        numberField = new JTextField();  
        numberField.addKeyListener(new TextFieldListener(numberField));   
        numberField.setPreferredSize(new Dimension(50, 24));        
        formPanel.add(new JLabel("Počet osob: "));
        formPanel.add(numberField, "wrap, push");
                
        hourField = new JTextField();   
        hourField.addKeyListener(new TextFieldListener(hourField));   
        hourField.setPreferredSize(new Dimension(80, 24));      
        formPanel.add(new JLabel("Hodina: "));
        formPanel.add(hourField);
        
        tableField = new JTextField();  
        tableField.addKeyListener(new TextFieldListener(tableField));   
        tableField.setPreferredSize(new Dimension(50, 24));        
        formPanel.add(new JLabel("Stůl: "));
        formPanel.add(tableField, "wrap");
        
        add(formPanel, "push, align center, wrap");        
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
            Booking newBooking = formatValidator.getValidBooking(nameField, tableField, numberField, dateField, hourField);
            DataStorage.getInstance().getBooking().add(newBooking);
            if (editing && oldBooking != null) {
                DataStorage.getInstance().getBooking().remove(oldBooking);
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
    
    private void fillTextFields(Booking booking) {
        nameField.setText(booking.getName());
        dateField.setText(booking.getDate());
        hourField.setText(Integer.toString(booking.getHour()));
        tableField.setText(Integer.toString(booking.getTable()));
        numberField.setText(Integer.toString(booking.getPersonCount()));
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
