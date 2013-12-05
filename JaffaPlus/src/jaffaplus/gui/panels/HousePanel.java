package jaffaplus.gui.panels;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Table;
import jaffaplus.collections.TableList;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Panel zobrazujici seznam vsech stolu v restauraci.
 * 
 * @author Hanzik
 */
public class HousePanel extends Panel {
    
    private Panel labelPanel, tablePanel, buttonPanel;
    private TableList tableList;
    
    private TableMiniPanel selectedPanel;
    
    public HousePanel() {
        super();       
        
        tableList = DataStorage.getInstance().getTables();
        
        initComponents();
    }    

    private void initComponents() {
        initLabel();
        initTables();
        initButtonPanel();
        
        add(labelPanel, "alignx center, aligny top, wrap");
        add(tablePanel, "alignx center, aligny top, dock center, wrap");
        add(buttonPanel, "south");
    }

    private void initLabel() {
        labelPanel = new Panel();
        JLabel label = new JLabel("Vyberte stůl pro zobrazení objednávek:");
        label.setFont(new Font("Calibri", Font.BOLD, 30));
        labelPanel.add(label);        
    }
    
    private void initTables() {
        tablePanel = new Panel();
        int tablesInserted = 0;
        int tablesPerRow = 4;
        for (Table table : tableList) {
            tablesInserted++;
            if (tablesInserted % tablesPerRow == 0) {
                tablePanel.add(new TableMiniPanel(table, this), "wrap");                
            } else {
                tablePanel.add(new TableMiniPanel(table, this));                
            }
        }
    }
    
    private void initButtonPanel() {
        
        int panelWidth = 450;
        int panelHeight = 150;
        
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));        
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Button openTableButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        openTableButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        openTableButton.addMouseListener(new OpenButtonListener(openTableButton));
        
        buttonPanel.add(openTableButton);
        buttonPanel.add(goBackButton);
    }

    public TableMiniPanel getSelectedPanel() {
        return selectedPanel;
    }

    public void setSelectedPanel(TableMiniPanel selectedPanel) {
        this.selectedPanel = selectedPanel;
    }
    
    private class OpenButtonListener extends ButtonListener {
                
        private OpenButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedPanel != null) {
                PanelSwitcher.getInstance().switchToPanel(new TablePanel(selectedPanel.getTable().getTableNumber())); 
            } else {
                // hod chybu, ze musime nejprve vybrat stul
            }            
        }
    }
}
