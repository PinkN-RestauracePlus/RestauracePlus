package jaffaplus.gui.panels;

import jaffaplus.collections.Supplier;
import jaffaplus.gui.dialogs.AddSupplierDialog;
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
 * @author Lukas
 */
public class SuppliersMiniPanel extends Panel {
    
    private boolean selected;
    private Supplier supp;
    private SupplierEditorPanel parentPanel;
    
    private Panel namePanel, addressPanel, phoneNumberPanel;
        
    private final int PANEL_WIDTH = 700;
    private final int PANEL_HEIGHT = 40;
    
    public SuppliersMiniPanel(Supplier supp, SupplierEditorPanel panel) {
        
        this.supp = supp;
        this.parentPanel = panel;
        
        addMouseListener(new SuppliersMiniPanel.MiniPanelListener(this));
        setLayout(new MigLayout());
        
        setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        initComponents();
    }

    private void initComponents() {
        Font font;
        JLabel label;
        
        int clmn1Width = 200;
        int clmn2Width = 300;
        int clmn3Width = 100;
              
                       
        namePanel = new Panel();
        namePanel.setMinimumSize(new Dimension(clmn1Width, PANEL_HEIGHT - 10));
        namePanel.setMaximumSize(new Dimension(clmn2Width, PANEL_HEIGHT - 10));
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(supp.getName());
        label.setFont(font);
        namePanel.add(label);
        add(namePanel);
                        
        addressPanel = new Panel();
        addressPanel.setMinimumSize(new Dimension(clmn2Width, PANEL_HEIGHT - 10));
        addressPanel.setMaximumSize(new Dimension(clmn2Width, PANEL_HEIGHT - 10));
        addressPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, GlobalValues.BORDER_COLOR));
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(supp.getAddress());
        label.setFont(font);  
        addressPanel.add(label);
        add(addressPanel);
        
        phoneNumberPanel = new Panel();
        phoneNumberPanel.setMinimumSize(new Dimension(clmn3Width, PANEL_HEIGHT - 10));
        phoneNumberPanel.setMaximumSize(new Dimension(clmn3Width, PANEL_HEIGHT - 10));
        font = new Font("Calibri", Font.PLAIN, 16);
        label = new JLabel(supp.getPhoneNumber());
        label.setFont(font);  
        phoneNumberPanel.add(label);
        add(phoneNumberPanel);
    }
    
    private void changeBackground(Color color) {
        setBackground(color);
        namePanel.setBackground(color);
        addressPanel.setBackground(color);
        phoneNumberPanel.setBackground(color);
    }
    
    @Override
    public void selectPanel() {
        //Zrusi vyber posledniho zvoleneho panelu
        if (parentPanel.getSelectedSupplierPanel() != null) {
            parentPanel.getSelectedSupplierPanel().deselectPanel();
        }
        //Vybere tento panel
        selected = true;
        parentPanel.setSelectedSupplierPanel(this);
        changeBackground(GlobalValues.BACKGROUND_COLOR_SELECTED);
    }
    
    @Override
    public void deselectPanel() {
        selected = false;
        parentPanel.setSelectedSupplierPanel(null);
        changeBackground(GlobalValues.BACKGROUND_COLOR);
    }

    public Supplier getSupplier() {
        return supp;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
    private void showInfo(){
        AddSupplierDialog dialog = new AddSupplierDialog(parentPanel, true);
    }
    private class MiniPanelListener extends PanelListener {
        
        private MiniPanelListener(Panel panel) {
            super(panel);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if( e.getClickCount() == 2 ){
                 showInfo();
            }
            if (selected) {
                deselectPanel();
            } else {
                selectPanel();
            }
        }
        
    }
}
