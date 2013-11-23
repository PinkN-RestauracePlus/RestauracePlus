package jaffaplus.gui.dialogs;

import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class DialogInputException extends Exception {
    
    private JTextField exceptionSource;
    
    public DialogInputException() {
        super();
    }

    public DialogInputException(String message) {
        super(message);
    }
    
    public DialogInputException(JTextField source) {
        super();
        this.exceptionSource = source;
    }
    
    public DialogInputException(String message, JTextField source) {
        super(message);
        this.exceptionSource = source;
    }

    public JTextField getExceptionSource() {
        return exceptionSource;
    }
}
