package jaffaplus.gui.dialogs;

import jaffaplus.calendar.MonthsOfYear;
import jaffaplus.collections.Booking;
import jaffaplus.collections.DataStorage;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.calendar.BookingPanel;
import jaffaplus.source.GlobalValues;
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
    
    private JLabel errorLabel = new JLabel("");
    private JTextField nameField, dateField, hourField, tableField, numberField;
    
    private Panel formPanel;
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 280;
    
    public AddBookingDialog(BookingPanel panel) {
        
        super();
        this.panel = panel;
        setLocationRelativeTo(panel);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
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
        String name = nameField.getText();
        String date = dateField.getText();
        String hour = hourField.getText();
        String number = numberField.getText();
        String table = tableField.getText();
        
        try {            
            Booking newBooking = getValidBooking(name, table, number, date, hour);
            DataStorage.getInstance().getBooking().add(newBooking);
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
    
    private Booking getValidBooking(String name, String table, String number, String date, String hour) throws DialogInputException {
        int peopleNumber = getValidNumber(number);
        int tableNumber = getValidTable(table);
        int[] validDate = getValidDate(date);
        int validHour = getValidHour(hour);
        
        return new Booking(name, tableNumber, peopleNumber, validHour, validDate[0], validDate[1] - 1, validDate[2]);
    }

    private int getValidNumber(String number) throws DialogInputException {   
        
        try {
            int validNumber = Integer.parseInt(number);
            if (validNumber < 1) {
                throw new DialogInputException("Zadaný počet osob nesmí být záporný.", numberField);
            } else if (validNumber > 20) {
                throw new DialogInputException("Tak velký stůl nemáme.", numberField);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadaný počet osob.", numberField);
        }
    }

    private int getValidTable(String table) throws DialogInputException {  
        
        try {
            int validNumber = Integer.parseInt(table);
            if (validNumber < 1) {
                throw new DialogInputException("Zadané číslo stolu nesmí být záporné.", tableField);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadané číslo stolu.", tableField);
        }
    }

    private int[] getValidDate(String date) throws DialogInputException {
        String[] dateArray = date.split("\\.");
        
        try {
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            
            if (day > MonthsOfYear.getLength(month - 1)) {
                throw new DialogInputException("Měsíc má pouze " + MonthsOfYear.getLength(month) + " dnů.", dateField);
            } else if (day < 1) {
                throw new DialogInputException("Měsíc nemá záporné množství dnů.", dateField);
            }
            
            if (month > 12) {
                throw new DialogInputException("Rok má pouze 12 měsíců.", dateField);
            } else if (month < 1) {
                throw new DialogInputException("Rok nemá záporný počet měsíců.", dateField);
            }
            
            if (year < 2013) {
                throw new DialogInputException("Chcete snad cestovat časem do minulosti?", dateField);
            } else if (year > 2100) {
                throw new DialogInputException("Budoucnost firmy vidíme růžově, ale čeho je moc...", dateField);
            }
            
            int[] validDate = new int[3];
            validDate[0] = day;
            validDate[1] = month;
            validDate[2] = year;
            return validDate;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DialogInputException("Zadávejte datum ve tvaru XX.XX.XXXX", dateField);
        }
    }

    private int getValidHour(String hour) throws DialogInputException {  
        
        try {
            int validNumber = Integer.parseInt(hour);
            if (validNumber > 23) {
                throw new DialogInputException("Den má pouze 24 hodin.", hourField);
            } else if (validNumber < 0) {
                throw new DialogInputException("Den nemá záporné množství hodin.", hourField);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadaná hodina.", hourField);
        }
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
