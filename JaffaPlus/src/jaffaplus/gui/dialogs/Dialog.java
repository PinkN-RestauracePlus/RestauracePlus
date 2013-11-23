package jaffaplus.gui.dialogs;

import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class Dialog extends JDialog {
        
    private Button confirmButton, cancelButton;
    
    private Panel buttonPanel;    
    private Panel contentPane = new Panel();
    
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 500;
    private final int FRAME_THICKNESS = 4;
    
    private final int FRAME_LOCATION_X = 250;
    private final int FRAME_LOCATION_Y = 250;
    
    public Dialog() {
        
        setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new MigLayout());
        setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
        setModal(true);
        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setUndecorated(true);
                
        contentPane.setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR, FRAME_THICKNESS));
        contentPane.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
    }
    
    public void initButtonPanel() {
        confirmButton = new Button();
        cancelButton = new Button();
        
        confirmButton.addMouseListener(new AddButtonListener(confirmButton));
        cancelButton.addMouseListener(new CancelButtonListener(cancelButton));
        
        confirmButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        cancelButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);
        
        buttonPanel = new Panel();
        buttonPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        getContentPane().add(buttonPanel, "push, align center");        
    }
    
    public void add() {
        //do nothing unless overriden        
    }    
    
    public void cancel() {
        //do nothing unless overriden    
    }
    
    private class AddButtonListener extends ButtonListener {

        private AddButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            add();
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
