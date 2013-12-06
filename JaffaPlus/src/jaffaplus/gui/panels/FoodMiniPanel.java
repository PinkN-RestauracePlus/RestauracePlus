package jaffaplus.gui.panels;

import jaffaplus.collections.Item;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hanzik
 */
public class FoodMiniPanel extends Panel {
    
    private boolean selected;
    private Item item;
    private OrderPanel parentPanel;
        
    private final int PANEL_WIDTH = 350;
    private final int PANEL_HEIGHT = 40;
    private final int MARGIN = 2;
    
    public FoodMiniPanel(Item item, OrderPanel order) {
        
        this.item = item;
        this.parentPanel = order;
        
        addMouseListener(new MiniPanelListener(this));
        setLayout(new MigLayout());
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN), new LineBorder(GlobalValues.BORDER_COLOR)));
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        Font font;
        JLabel label;
        
        font = new Font("Calibri", Font.PLAIN, 12);
        label = new JLabel(item.getId());
        label.setFont(font);
        add(label);
        
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(" | " + item.getName()+ " | ");
        label.setFont(font);
        add(label);
        
        font = new Font("Calibri", Font.BOLD, 16);
        label = new JLabel(item.getPrice()+ ",-");
        label.setFont(font);
        add(label);
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (parentPanel.getSelectedFood() != null) {
            parentPanel.getSelectedFood().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        parentPanel.setSelectedFood(this);
        setBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        parentPanel.setSelectedFood(null);
        setBackground(GlobalValues.BACKGROUND_COLOR);
    }

    public Item getFood() {
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
