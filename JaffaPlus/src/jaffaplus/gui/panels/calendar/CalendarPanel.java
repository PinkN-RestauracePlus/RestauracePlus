package jaffaplus.gui.panels.calendar;

import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import javax.swing.BorderFactory;

/**
 *
 * @author Hanzik
 */
public class CalendarPanel extends Panel {

    private MonthPanel monthPanel;
    private BookingPanel bookingPanel;
    private Panel buttonPanel;
        
    public CalendarPanel() {
        initComponents();
    }

    private void initComponents() {
        initMonthPanel();
        initBookingPanel();
        initButtonPanel();
        
        add(monthPanel);
        add(bookingPanel, "wrap");
        add(buttonPanel);
    }

    private void initMonthPanel() {
        monthPanel = new MonthPanel();
    }

    private void initBookingPanel() {
        bookingPanel = new BookingPanel();
    }

    private void initButtonPanel() {
                
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button addFoodButton = new Button("+");
        Button removeFoodButton = new Button("-");
        Button payFoodButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        addFoodButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeFoodButton.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        payFoodButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

//        addFoodButton.addMouseListener(new AddButtonListener(addFoodButton));
//        removeFoodButton.addMouseListener(new RemoveButtonListener(removeFoodButton));
//        payFoodButton.addMouseListener(new DetailsButtonListener());
        
        buttonPanel.add(addFoodButton);
        buttonPanel.add(removeFoodButton);
        buttonPanel.add(payFoodButton);
        buttonPanel.add(goBackButton);
    }
    
}
