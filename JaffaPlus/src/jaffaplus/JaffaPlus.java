package jaffaplus;

import jaffaplus.gui.panels.MainMenuPanel;
import jaffaplus.gui.panels.PanelSwitcher;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is where all stories begin.
 * 
 * @author Hanzik
 */

public class JaffaPlus extends JFrame {
        
    public static void main(String[] args) {
        JaffaPlus frame = new JaffaPlus();
        AppInitiator initiator = new AppInitiator();
        frame.init();
    }

    private void init() { 
        PanelSwitcher.getInstance().setMainFrame(this);
        MainMenuPanel mainMenu = new MainMenuPanel();
        getContentPane().add(mainMenu);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(GlobalValues.WINDOW_WIDTH, GlobalValues.WINDOW_HEIGHT));
        setTitle(GlobalValues.NAME);
        setVisible(true);
    }
}