package jaffaplus.gui.panels.calendar;

import jaffaplus.collections.Booking;
import jaffaplus.collections.BookingList;
import jaffaplus.collections.DataStorage;
import jaffaplus.gui.panels.Panel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Tento panel zobrazuje rezervace pro dany den. 
 * 
 * @author Hanzik
 */
public class BookingPanel extends Panel {
                
    private Booking selectedBooking;
    private BookingMiniPanel selectedPanel;
    private BookingList completeList = DataStorage.getInstance().getBooking();
    
    private int day, month, year;
    
    private final int PANEL_WIDTH = 300;
    private final int PANEL_HEIGHT = 600;
        
    public BookingPanel() {        
        setDefaultValues();        
    }
    
    private void displayList(BookingList list) {        
        if (list.isEmpty()) {
            displayMessage("Pro tento den není žádná rezervace.");
            return;
        }
        removeAll();        
        
        for (Booking booking : list) {
            add(new BookingMiniPanel(booking, this), "wrap");
        }
        
        revalidate();
        repaint();
    }
    
    private void displayMessage(String message) {    
        removeAll();    
        add(new JLabel(message), "wrap");
        revalidate();
        repaint();
    }
    
    public void showNewList(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        displayList(completeList.findByDate(day, month, year));
    }
    
    public void removeSelectedBooking() {
        if (selectedBooking != null) {
            completeList.remove(selectedBooking);
            selectedBooking = null;
            showNewList(day, month, year);
        }
    }
    
    public void refreshBookingForDay() {
        showNewList(day, month, year);
    }
    
    public void resetSelection() {
        displayMessage("Zvolte den, pro který chcete robrazit rezervace.");
        selectedBooking = null;
        selectedPanel = null;
    }
    
    public void setSelectedBooking(BookingMiniPanel panel) {
        if (selectedPanel != null) {
            selectedPanel.changeState();
        }
        this.selectedPanel = panel;
        this.selectedBooking = panel.getBooking();
    }

    private void setDefaultValues() {
        displayMessage("Zvolte den, pro který chcete robrazit rezervace.");
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder());
    }
}
