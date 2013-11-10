package jaffaplus.gui.buttons;

import jaffaplus.gui.panels.PanelSwitcher;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Z této třídy bude dědit většina používaných tlačítek, velká část grafického
 * zpracování všech tlačítek je identická.
 * 
 * @author Hanzik
 */
public class Button extends JLabel {

    private final int DEFAULT_WIDTH = 100;
    private final int DEFAULT_HEIGHT = 100;
     
    private int linkToPanel;
    private String buttonName = "";
    private ImageIcon inactiveIcon, activeIcon, clickedIcon;    
    
    private class EmptyClass{}
    private Class loadClass = new EmptyClass().getClass();
    
    public Button() {        
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));   
        addMouseListener(new ButtonListener(this));
    }
    
    public Button(String label) {
        
        this.buttonName = label;
        
        setName(buttonName);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));   
        addMouseListener(new ButtonListener(this));
    }
    
    public Button(String name, int linkToPanel) {
        this(name);
        this.linkToPanel = linkToPanel;
    }
    
    protected void executeButtonClick() {
        if (linkToPanel != 0) {
            PanelSwitcher.getInstance().switchToPanel(linkToPanel);
        } else {
            performAction();
        }
    }
    
    private void performAction() {
        //do nothing unless overwritten
    }
    
    public void loadIcons(String inactiveSource, String activeSource, String clickedSource) {
        inactiveIcon = new ImageIcon(loadClass.getResource(inactiveSource));
        activeIcon = new ImageIcon(loadClass.getResource(activeSource));
        clickedIcon = new ImageIcon(loadClass.getResource(clickedSource));
        
        setInactiveIcon();
    }

    protected void setInactiveIcon() {
        setIcon(inactiveIcon);
    }  

    protected void setActiveIcon() {
        setIcon(activeIcon);
    }  

    protected void setClickedIcon() {
        if (isIconLoaded(clickedIcon)) {
            setIcon(clickedIcon);
        }
    }
    
    private boolean isIconLoaded(ImageIcon icon) {
        if (icon == null) {
            setText(buttonName);
            return false;
        }
        return true;
    }
}