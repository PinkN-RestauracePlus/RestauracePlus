package jaffaplus.gui.panels.calendar;

import jaffaplus.collections.Booking;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.PanelListener;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class BookingMiniPanel extends Panel {
    
    private boolean selected;
    private Booking booking;
    private BookingPanel bookingParentPanel;
    
    private final int PANEL_WIDTH = 300;
    private final int PANEL_HEIGHT = 50;
    
    
    public BookingMiniPanel(Booking booking, BookingPanel panel) {
        
        this.booking = booking;        
        this.bookingParentPanel = panel;
        
        addMouseListener(new BookingPanelListener(this));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        
        initComponents();
    }

    private void initComponents() {
        initLeftLabel();
        initRightLabel(); 
    }

    private void initLeftLabel() {
        String text1;
        text1 = "<html>" + booking.getName() + "<br>" +
                booking.getHour() + ":00" + "</html>";
        JLabel label1 = new JLabel(text1); 
        add(label1, "width 200"); 
    }

    private void initRightLabel() {
        String firstLine = "";
        
        switch (booking.getPersonCount()) {
            case 1: {
                firstLine = "1 osoba";
                break;
            } case 2:
            case 3:
            case 4: {
                firstLine = booking.getPersonCount() + " osoby";
                break;
            } default: {
                firstLine = booking.getPersonCount() + " osob";
                break;
            }
        }
        
        String text2 = "<html>" + firstLine + "<br>" +
                "Stůl " + booking.getTable() + "</html>";
        JLabel label2 = new JLabel(text2);    
        add(label2, "width 100");
    }
    
    public void changeState() {
        if (selected) {
            selected = false;
            setBackground(GlobalValues.BACKGROUND_COLOR);
        } else {
            selected = true;
            setBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
            bookingParentPanel.setSelectedBooking(this);
        }        
    }

    public Booking getBooking() {
        return booking;
    }
    
    private class BookingPanelListener extends PanelListener {
        
        private BookingPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            changeState();
        }
    }
}
