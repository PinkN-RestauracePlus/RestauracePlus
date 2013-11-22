package jaffaplus.gui.panels.calendar;

import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import javax.swing.BorderFactory;

/**
 *
 * @author Hanzik
 */
public class BookingPanel extends Panel {
    
    private final int PANEL_WIDTH = 300;
    private final int PANEL_HEIGHT = 400;
        
    public BookingPanel() {
        
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        
        initComponents();
    }

    private void initComponents() {
        
    }

}
