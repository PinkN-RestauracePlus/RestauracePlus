package jaffaplus.gui.panels.calendar;

import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class DayPanel extends Panel {
    
    private int day;
    
    private final int PANEL_WIDTH = 50;
    private final int PANEL_HEIGHT = 50;
    
    public DayPanel(int day) {
        
        this.day = day;
        
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        add(new JLabel(Integer.toString(day)));
    }
}
