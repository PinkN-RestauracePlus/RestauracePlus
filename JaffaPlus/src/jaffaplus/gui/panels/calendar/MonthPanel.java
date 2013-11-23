package jaffaplus.gui.panels.calendar;

import jaffaplus.calendar.DayOfWeek;
import jaffaplus.calendar.Month;
import jaffaplus.gui.panels.Panel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.BorderFactory;

/**
 *
 * @author Hanzik
 */
public class MonthPanel extends Panel {
    
    private Month month;
    private DayPanel selectedPanel;
    private CalendarPanel calendarPanel;
    
    private final int DAYS_PER_WEEK = 7;
    private final int ROWS = 5;
    
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 350;
    
    public MonthPanel(CalendarPanel calendarPanel) {
        
        this.calendarPanel = calendarPanel;        
        this.month = new Month(Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
                
        buildPanel();
    }
    
    public MonthPanel(Month month, CalendarPanel calendarPanel) {
        
        this.calendarPanel = calendarPanel;        
        this.month = month;
        
        buildPanel();
    }
    
    /** Zobrazi dny kalendarniho mesice. */
    private void displayDays() {
        int daysDisplayed = 0;
        
        /** Zobrazi jmena dnu. */
        for (int i = 0; i < 7; i++) {
            add(new DayPanel(DayOfWeek.getAcronym(i)));
            daysDisplayed++;
        }
        /** Vyplni prazdne ctverce az po prvni den v mesici. */
        for (int i = 1; i < month.getStartDay(); i++) {
            add(new DayPanel(""));            
            daysDisplayed++;
        }
        /** Vypise dny v mesici. */
        for (int i = 0; i < month.getLength(); i++) {
            add(new DayPanel(i + 1, this));   
            daysDisplayed++;         
        }
        /** Vyplni zbytek posledniho radku. */
        for (int i = 0; (daysDisplayed % DAYS_PER_WEEK) != 0; i++) {
            add(new DayPanel(""));     
            daysDisplayed++;
        }
    }
    
    private void buildPanel() {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(0, DAYS_PER_WEEK));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        displayDays();                
    }
    
    public void changeBookingForDay(DayPanel panel) {
        if (selectedPanel != null) {
            selectedPanel.changeState();
        }
        
        selectedPanel = panel;
        calendarPanel.changeBookingForDay(panel.getDay());
    }

    public Month getMonth() {
        return month;
    }
    
    public int getYear() {
        return month.getYear();
    }
    
    public void changeMonth(Month month) {
        this.month = month;        
        
        removeAll();
        displayDays();
        revalidate();
        repaint();
    }
    
    public Month getPreviousMonth() {
        return month.getPreviousMonth();
    }
    
    public Month getNextMonth() {
        return month.getNextMonth();
    }
}
