package jaffaplus.gui.panels.calendar;

import jaffaplus.calendar.Month;
import jaffaplus.collections.Booking;
import jaffaplus.collections.BookingList;
import jaffaplus.collections.DataStorage;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.dialogs.AddBookingDialog;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class CalendarPanel extends Panel {

    private BookingList completeList = DataStorage.getInstance().getBooking();
        
    private JLabel mainText, monthDisplayed;
    
    private BookingPanel bookingPanel;
    private MonthPanel monthPanel;
    private Panel buttonPanel1, buttonPanel2;
        
    public CalendarPanel() {
        initComponents();
    }

    private void initComponents() {
        initTitleText();
        initMonthPanel();
        initBookingPanel();
        initButtonPanel1();
        initButtonPanel2();
        
        add(mainText, "alignx center, wrap");
        add(monthPanel);
        add(bookingPanel, "wrap, spany 3");
        add(buttonPanel1, "wrap");
        add(buttonPanel2);
    }


    private void initTitleText() {
        mainText = new JLabel("Rezervace stolů");
        mainText.setFont(new Font("Calibri", Font.BOLD, 40));
    }
    
    private void initMonthPanel() {
        monthPanel = new MonthPanel(this);
    }

    private void initBookingPanel() {
        bookingPanel = new BookingPanel();
    }

    private void initButtonPanel1() {
                
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel1 = new Panel();
        buttonPanel1.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button previousMonth = new Button("+");
        monthDisplayed = new JLabel(monthPanel.getMonth().toString());
        Button nextMonth = new Button("-");
        
        previousMonth.loadIcons(Path.BUTTONS_CONTROL_PREVIOUS_INACTIVE, Path.BUTTONS_CONTROL_PREVIOUS_ACTIVE, Path.BUTTONS_CONTROL_PREVIOUS_CLICKED);
        nextMonth.loadIcons(Path.BUTTONS_CONTROL_NEXT_INACTIVE, Path.BUTTONS_CONTROL_NEXT_ACTIVE, Path.BUTTONS_CONTROL_NEXT_CLICKED);
       
        previousMonth.addMouseListener(new PreviousButtonListener(previousMonth));
        nextMonth.addMouseListener(new NextButtonListener(nextMonth));
        
        buttonPanel1.add(previousMonth);
        buttonPanel1.add(monthDisplayed, "width max(100)");
        buttonPanel1.add(nextMonth);
    }

    private void initButtonPanel2() {
                
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel2 = new Panel();
        buttonPanel2.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button addBooking = new Button("+");
        Button removeBooking = new Button("-");
        Button editBooking = new Button("Editovat");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        addBooking.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeBooking.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        editBooking.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        addBooking.addMouseListener(new AddBookingButtonListener(addBooking));        
        removeBooking.addMouseListener(new RemoveBookingButtonListener(removeBooking));
        editBooking.addMouseListener(new EditBookingButtonListener(editBooking));
        
        buttonPanel2.add(addBooking);
        buttonPanel2.add(removeBooking);
        buttonPanel2.add(editBooking);
        buttonPanel2.add(goBackButton);
    }
    
    private void changeMonth(Month month) {
        monthPanel.changeMonth(month);
        bookingPanel.resetSelection();
        monthDisplayed.setText(month.toString());
    }
    
    private Month getPreviousMonth() {
        return monthPanel.getPreviousMonth();
    }
    
    private Month getNextMonth() {
        return monthPanel.getNextMonth();
    }

    public void changeBookingForDay(int day) {
        bookingPanel.showNewList(day, monthPanel.getMonth().getMonthNumber(), monthPanel.getYear());
    }
    
    private void createDialogWindow(boolean editing) {
        //If editing existing booking
        if (editing) {
            AddBookingDialog dialog = new AddBookingDialog(bookingPanel, editing);           
        } else {
            AddBookingDialog dialog = new AddBookingDialog(bookingPanel, editing);            
        }
    }
        
    private void removeSelectedBooking() {
        bookingPanel.removeSelectedBooking();
    }
    
    private class PreviousButtonListener extends ButtonListener {
            
        private PreviousButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            changeMonth(getPreviousMonth());
        }
    }
    
    private class NextButtonListener extends ButtonListener {
            
        private NextButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            changeMonth(getNextMonth());
        }
    }
    
    private class AddBookingButtonListener extends ButtonListener {
            
        private AddBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            createDialogWindow(false);
            bookingPanel.resetSelection();
            bookingPanel.refreshBookingForDay();
        }
    }
    
    private class EditBookingButtonListener extends ButtonListener {
            
        private EditBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (bookingPanel.isBookingSelected()) {
                createDialogWindow(true);     
                bookingPanel.resetSelection();
                bookingPanel.refreshBookingForDay();
            } else {
                //vypis chybu, ze je nejprve treba vybrat rezervaci
            }
        }
    }
    
    private class RemoveBookingButtonListener extends ButtonListener {
            
        private RemoveBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            removeSelectedBooking();
        }
    }
}
