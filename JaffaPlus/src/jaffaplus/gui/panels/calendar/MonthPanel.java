package jaffaplus.gui.panels.calendar;

import jaffaplus.calendar.DayOfWeek;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class MonthPanel extends Panel {
    
    private int month;
    
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 400;
    
    public MonthPanel() {
        
        month = Calendar.getInstance().get(Calendar.MONTH);
        
        buildPanel();
    }
    
    public MonthPanel(int month) {
        
        this.month = month;
        
        buildPanel();
    }

    private void displayDays() {
        for (int i = 0; i < 7; i++) {
            add(new JLabel (DayOfWeek.getAcronym(i)));
        }
    }
    
    private void buildPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        
        displayDays();
        
        
    }

}
