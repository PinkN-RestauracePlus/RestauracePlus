package jaffaplus.gui.buttons;

import jaffaplus.gui.panels.PanelSwitcher;
import java.awt.Dimension;

/**
 *
 * @author Hanzik
 */
public class MainMenuButton extends Button {
    
    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 100;
    
    private int linkToPanel;
    
    public MainMenuButton(String name, int linkToPanel) {
        super(name);    
        this.linkToPanel = linkToPanel;
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
    
    @Override
    protected void executeButtonClick() {
        PanelSwitcher.getInstance().switchToPanel(linkToPanel);
    }
}
