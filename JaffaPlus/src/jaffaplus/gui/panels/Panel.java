package jaffaplus.gui.panels;

import jaffaplus.source.GlobalValues;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class Panel extends JPanel {
    
    public Panel() {
        setLayout(new MigLayout());
        setBackground(GlobalValues.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder());
    }
    
    public void selectPanel() {
        //do nothing unless overwritten
    }
    
    public void deselectPanel() {
        //do nothing unless overwritten
    }
    
    public boolean isSelected() {
        //do nothing unless overwritten
        return false;
    }
}
