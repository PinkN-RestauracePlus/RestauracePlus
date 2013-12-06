package jaffaplus.gui.panels;

import jaffaplus.JaffaPlus;
import jaffaplus.gui.panels.calendar.CalendarPanel;
import jaffaplus.gui.panels.menueditor.MenuEditorPanel;
import jaffaplus.source.GlobalValues;

/**
 * PanelSwitcher umi vykreslit novy panel za stary; vyuzivan bude predevsim
 * pri stisku tlacitek, ktere maji prechazet mezi jednotlivymi obrazovkami.
 * 
 * @author Hanzik
 */
public class PanelSwitcher {
   
    private JaffaPlus mainFrame;
    
    private static final PanelSwitcher INSTANCE = new PanelSwitcher();
        
    private PanelSwitcher() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PanelSwitcher getInstance() {
        return INSTANCE;
    }
    
    public void switchToPanel(int panelName) {
        mainFrame.getContentPane().removeAll();
        
        switch(panelName) {
            case GlobalValues.PANEL_MAINMENU: {
                mainFrame.getContentPane().add(new MainMenuPanel());
                break;
            }
            case GlobalValues.PANEL_HOUSEMAP: {
                mainFrame.getContentPane().add(new HousePanel());
                break;
            }
            case GlobalValues.PANEL_TABLE: {
                mainFrame.getContentPane().add(new TablePanel());
                break;
            }
            case GlobalValues.PANEL_BOOKING: {
                mainFrame.getContentPane().add(new CalendarPanel());
                break;
            }
            case GlobalValues.PANEL_FOODMENU: {
                mainFrame.getContentPane().add(new MenuEditorPanel());
                break;
            }
            case GlobalValues.PANEL_SUPPLIERS: {
                //here is where we show suppliers panel
//                mainFrame.getContentPane().add(new CalendarPanel());
                break;
            }
        }       
        mainFrame.repaint();
        mainFrame.revalidate();
    }
    
    public void switchToPanel(Panel panel) {
        mainFrame.getContentPane().removeAll();
        
        mainFrame.getContentPane().add(panel);
        
        mainFrame.repaint();
        mainFrame.revalidate();
    }

    public void setMainFrame(JaffaPlus mainFrame) {
        this.mainFrame = mainFrame;
    }
}
