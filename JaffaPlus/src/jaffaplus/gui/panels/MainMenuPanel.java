package jaffaplus.gui.panels;

import jaffaplus.gui.buttons.MainMenuButton;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * GUI Panel zobrazujici hlavni menu v podobe nekolika
 * velkych tlacitek odkazujicich na podsekce programu.
 * 
 * @author Hanzik
 */
public class MainMenuPanel extends Panel {
    
    private MainMenuButton foodOrders, booking, foodMenu, suppliers, help, settings;
    
    public MainMenuPanel() {
        
        super();        
        initComponents();
    }

    private void initComponents() {
        
        initMainText();
        initButtons();
        initFooter();                
    }

    private void initMainText() {
        JLabel mainText = new JLabel("Jaffa Plus pro Restaurace");
        mainText.setFont(new Font("Calibri", Font.BOLD, 40));
        add(mainText, "push, alignx center, aligny top, wrap");
    }

    private void initButtons() {
        
        Panel buttonPanel = new Panel();
        
        foodOrders = new MainMenuButton("Objednávky", GlobalValues.PANEL_HOUSEMAP);
        booking = new MainMenuButton("Jídelní lístek", GlobalValues.PANEL_BOOKING);
        foodMenu = new MainMenuButton("Jídelní lístek", GlobalValues.PANEL_FOODMENU);
        suppliers = new MainMenuButton("Dodavatelé", GlobalValues.PANEL_SUPPLIERS);
//        help = new MainMenuButton("Nápověda", GlobalValues.PANEL_TABLE);
//        settings = new MainMenuButton("Nastavení", GlobalValues.PANEL_TABLE);
        
        foodOrders.loadIcons(Path.BUTTONS_MAINMENU_ORDERS_INACTIVE, Path.BUTTONS_MAINMENU_ORDERS_ACTIVE, Path.BUTTONS_MAINMENU_ORDERS_CLICKED);
        booking.loadIcons(Path.BUTTONS_MAINMENU_BOOKING_INACTIVE, Path.BUTTONS_MAINMENU_BOOKING_ACTIVE, Path.BUTTONS_MAINMENU_BOOKING_CLICKED);
        foodMenu.loadIcons(Path.BUTTONS_MAINMENU_FOODMENU_INACTIVE, Path.BUTTONS_MAINMENU_FOODMENU_ACTIVE, Path.BUTTONS_MAINMENU_FOODMENU_CLICKED);
        suppliers.loadIcons(Path.BUTTONS_MAINMENU_SUPPLY_INACTIVE, Path.BUTTONS_MAINMENU_SUPPLY_ACTIVE, Path.BUTTONS_MAINMENU_SUPPLY_CLICKED);
//        help.loadIcons(Path.BUTTONS_MAINMENU_HELP_INACTIVE, Path.BUTTONS_MAINMENU_HELP_ACTIVE, Path.BUTTONS_MAINMENU_HELP_CLICKED);
//        settings.loadIcons(Path.BUTTONS_MAINMENU_SETTINGS_INACTIVE, Path.BUTTONS_MAINMENU_SETTINGS_ACTIVE, Path.BUTTONS_MAINMENU_SETTINGS_CLICKED);
        
        buttonPanel.add(foodOrders);
        buttonPanel.add(booking, "wrap");
        buttonPanel.add(foodMenu);
        buttonPanel.add(suppliers, "wrap");
//        buttonPanel.add(help);
//        buttonPanel.add(settings, "wrap");
        add(buttonPanel, "al center, wrap");
    }

    private void initFooter() {
        JLabel mainText = new JLabel("Work in Progress");
        add(mainText, "push, alignx right, aligny bottom, wrap");        
    }
}
