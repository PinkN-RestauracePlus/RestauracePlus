package jaffaplus.tools;

import jaffaplus.calendar.MonthsOfYear;
import jaffaplus.collections.Booking;
import jaffaplus.gui.dialogs.DialogInputException;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class BookingFormatValidator {

    public Booking getValidBooking(JTextField nameField, JTextField tableField, JTextField numberField, JTextField dateField, JTextField hourField) throws DialogInputException {
            
        String name = nameField.getText();
        String date = dateField.getText();
        String hour = hourField.getText();
        String number = numberField.getText();
        String table = tableField.getText();
        
        int peopleNumber = getValidNumber(number, numberField);
        int tableNumber = getValidTable(table, tableField);
        int[] validDate = getValidDate(date, dateField);
        int validHour = getValidHour(hour, hourField);
        
        return new Booking(name, tableNumber, peopleNumber, validHour, validDate[0], validDate[1] - 1, validDate[2]);
    }    
    
    private int getValidNumber(String number, JTextField source) throws DialogInputException {   
        
        try {
            int validNumber = Integer.parseInt(number);
            if (validNumber < 1) {
                throw new DialogInputException("Zadaný počet osob nesmí být záporný.", source);
            } else if (validNumber > 20) {
                throw new DialogInputException("Tak velký stůl nemáme.", source);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadaný počet osob.", source);
        }
    }
    
    private int getValidTable(String table, JTextField source) throws DialogInputException {  
        
        try {
            int validNumber = Integer.parseInt(table);
            if (validNumber < 1) {
                throw new DialogInputException("Zadané číslo stolu nesmí být záporné.", source);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadané číslo stolu.", source);
        }
    }

    private int[] getValidDate(String date, JTextField source) throws DialogInputException {
        String[] dateArray = date.split("\\.");
        
        try {
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            
            if (day > MonthsOfYear.getLength(month - 1)) {
                throw new DialogInputException("Měsíc má pouze " + MonthsOfYear.getLength(month) + " dnů.", source);
            } else if (day < 1) {
                throw new DialogInputException("Měsíc nemá záporné množství dnů.", source);
            }
            
            if (month > 12) {
                throw new DialogInputException("Rok má pouze 12 měsíců.", source);
            } else if (month < 1) {
                throw new DialogInputException("Rok nemá záporný počet měsíců.", source);
            }
            
            if (year < 2013) {
                throw new DialogInputException("Chcete snad cestovat časem do minulosti?", source);
            } else if (year > 2100) {
                throw new DialogInputException("Budoucnost firmy vidíme růžově, ale čeho je moc...", source);
            }
            
            int[] validDate = new int[3];
            validDate[0] = day;
            validDate[1] = month;
            validDate[2] = year;
            return validDate;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DialogInputException("Zadávejte datum ve tvaru XX.XX.XXXX", source);
        }
    }

    private int getValidHour(String hour, JTextField source) throws DialogInputException {  
        
        try {
            int validNumber = Integer.parseInt(hour);
            if (validNumber > 23) {
                throw new DialogInputException("Den má pouze 24 hodin.", source);
            } else if (validNumber < 0) {
                throw new DialogInputException("Den nemá záporné množství hodin.", source);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Chybně zadaná hodina.", source);
        }
    }
}
