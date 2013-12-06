package jaffaplus.source;

import java.awt.Color;

/**
 *
 * @author Hanzik
 */
public class GlobalValues {

    /* Vlastnosti aplikace */
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 720;
    public static final int SCROLL_SPEED = 10;
    
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR_DIALOG = new Color(245, 245, 245);
    public static final Color BACKGROUND_COLOR_DIALOG_DARK = new Color(230, 230, 230);
    public static final Color BACKGROUND_COLOR_SELECTED = Color.GREEN;
    public static final Color BORDER_COLOR = Color.BLACK;
    public static final Color ERROR_COLOR = Color.RED;
    public static final Color FONT_COLOR = Color.BLACK;
    public static final String NAME = "Jaffa Plus - WoP";
    
    /* Identifikatory panelu */
    public static final int PANEL_MAINMENU = 1001;
    public static final int PANEL_HOUSEMAP = 1002;
    public static final int PANEL_TABLE = 1003;
    public static final int PANEL_ORDER = 1004;   
    public static final int PANEL_BOOKING = 1005; 
    public static final int PANEL_FOODMENU = 1006;   
    public static final int PANEL_SUPPLIERS = 1007;   
    
    /* Identifikatory validatoru formatu */
    public static final int VALIDATOR_BOOKING = 2001;
    public static final int VALIDATOR_FOOD = 2002;  
}
