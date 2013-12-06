package jaffaplus.tools;

import jaffaplus.collections.Item;
import jaffaplus.gui.dialogs.DialogInputException;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class FoodFormatValidator {

    public Item getValidItem(JTextField nameField, JTextField priceField, JTextField idField) throws DialogInputException {
            
        String name = nameField.getText();
        String price = priceField.getText();
        String id = idField.getText();
        
        int priceNumber = getValidPrice(price, priceField);
        
        return new Item(name, id, priceNumber);
    }    
    
    private int getValidPrice(String price, JTextField source) throws DialogInputException {   
        
        try {
            int validNumber = Integer.parseInt(price);
            if (validNumber < 1) {
                throw new DialogInputException("Cena musí být nezáporná.", source);
            } else if (validNumber > 9999) {
                throw new DialogInputException("Snažíte se snad někoho okrást?", source);
            }
            return validNumber;
        } catch (NumberFormatException e) {
            throw new DialogInputException("Cena muže být pouze celé číslo.", source);
        }
    }
}
