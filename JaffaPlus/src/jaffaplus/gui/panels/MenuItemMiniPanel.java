package jaffaplus.gui.panels;

import jaffaplus.collections.Item;
import jaffaplus.gui.panels.Panel;
import jaffaplus.gui.panels.PanelListener;
import jaffaplus.source.GlobalValues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class MenuItemMiniPanel extends Panel {
    
    private boolean selected;
    private Item item;
    private MenuEditorPanel parentPanel;
    
    private Panel idPanel, namePanel, pricePanel;
        
    private final int PANEL_WIDTH = 600;
    private final int PANEL_HEIGHT = 40;
    
    public MenuItemMiniPanel(Item item, MenuEditorPanel panel) {
        
        this.item = item;
        this.parentPanel = panel;
        
        addMouseListener(new MiniPanelListener(this));
        setLayout(new MigLayout());
        
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        Font font;
        JLabel label;
        
        int clmn1Width = 50;
        int clmn2Width = 450;
        int clmn3Width = 50;
        
        idPanel = new Panel();
        idPanel.setMinimumSize(new Dimension(clmn1Width, PANEL_HEIGHT - 10));
        idPanel.setMaximumSize(new Dimension(clmn1Width, PANEL_HEIGHT - 10));
        font = new Font("Calibri", Font.PLAIN, 12);
        label = new JLabel(item.getId());
        label.setFont(font);
        idPanel.add(label);
        add(idPanel);
                
        namePanel = new Panel();
        namePanel.setMinimumSize(new Dimension(clmn2Width, PANEL_HEIGHT - 10));
        namePanel.setMaximumSize(new Dimension(clmn2Width, PANEL_HEIGHT - 10));
        namePanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, GlobalValues.BORDER_COLOR));
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(item.getName());
        label.setFont(font);
        namePanel.add(label);
        add(namePanel);
                        
        pricePanel = new Panel();
        pricePanel.setMinimumSize(new Dimension(clmn3Width, PANEL_HEIGHT - 10));
        pricePanel.setMaximumSize(new Dimension(clmn3Width, PANEL_HEIGHT - 10));
        font = new Font("Calibri", Font.BOLD, 16);
        label = new JLabel(item.getPrice()+ ",-");
        label.setFont(font);  
        pricePanel.add(label);
        add(pricePanel);
    }
    
    private void changeBackground(Color color) {
        setBackground(color);
        idPanel.setBackground(color);
        namePanel.setBackground(color);
        pricePanel.setBackground(color);
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (parentPanel.getSelectedItemPanel() != null) {
            parentPanel.getSelectedItemPanel().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        parentPanel.setSelectedItemPanel(this);
        changeBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        parentPanel.setSelectedItemPanel(null);
        changeBackground(GlobalValues.BACKGROUND_COLOR);
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
    
    private class MiniPanelListener extends PanelListener {
        
        private MiniPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            
            if (selected) {
                deselectPanel();
            } else {
                selectPanel();
            }
        }
    }
}
