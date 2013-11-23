package jaffaplus.gui.panels.calendar;

import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.PanelListener;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Hanzik
 */
public class DayPanel extends Panel {
    
    private boolean enabled, selected;
    private int day;
    
    private MonthPanel monthPanel;
    
    private final int PANEL_WIDTH = 50;
    private final int PANEL_HEIGHT = 50;
    
    public DayPanel(int day, MonthPanel monthPanel) {
        
        this.day = day;
        this.monthPanel = monthPanel;
        this.enabled = true;
        
        addMouseListener(new DayPanelListener(this));
        setDefaultValues();
        initComponents();
    }
    
    public DayPanel(String dayName) {
        
        this.enabled = false;
        setDefaultValues();
        add(new JLabel(dayName, JLabel.CENTER));
    }
    
    private void setDefaultValues() {
        
        if (enabled) {
            setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);            
        } else {
            setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG_DARK);                
        }
        
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));        
    }

    private void initComponents() {
        add(new JLabel(Integer.toString(day)));
    }
    
    public void changeState() {
        if (selected) {
            selected = false;
            setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        } else {
            selected = true;
            setBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
        }
    }
    
    private void reloadPanels() {
        monthPanel.changeBookingForDay(this);
    }

    public int getDay() {
        return day;
    }
    
    private class DayPanelListener extends PanelListener {
        
        private DayPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            changeState();
            reloadPanels();
        }           
    }
}
