package jaffaplus.gui.panels.menueditor;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Item;
import jaffaplus.collections.ItemList;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class MenuEditorPanel extends Panel {
    
    private ItemList completeList = DataStorage.getInstance().getFoodMenu();    
    private Panel labelPanel, itemsPanel, searchPanel, bottomPanel;        
    private MenuItemMiniPanel selectedItem;
    
    public MenuEditorPanel() {
        super();       
        
        initComponents();
    }    

    private void initComponents() {
        initLabel();
        initItems();
        initSearchField();
        initButtons();
        
        add(labelPanel, "alignx center, aligny top, span, wrap");
        add(itemsPanel, "align center, span, wrap");
        add(bottomPanel, "push, alignx center, aligny bottom");
    }

    private void initLabel() {
        labelPanel = new Panel();
        JLabel label = new JLabel("Editor jídelního lístku");
        label.setFont(new Font("Calibri", Font.BOLD, 30));
        labelPanel.add(label);        
    }

    private void initItems() {
        itemsPanel = new Panel();
        displayItemsFromList(completeList);
    }
    
    private void initSearchField() {     
        
        int width = 300;
        int height = 40;
        
        bottomPanel = new Panel();
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(width, height));
        searchField.setFont(new Font("Calibri", Font.PLAIN, 20));
//        searchField.addKeyListener(new SearchFieldListener(completeList));
        
        bottomPanel.add(searchField);
    }        
    
    private void initButtons() {
        
        Button addItemButton = new Button("+");
        Button removeItemButton = new Button("-");
        Button openItemButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        addItemButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeItemButton.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        openItemButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        addItemButton.addMouseListener(new AddBookingButtonListener(addItemButton));        
        removeItemButton.addMouseListener(new RemoveBookingButtonListener(removeItemButton));
        openItemButton.addMouseListener(new EditBookingButtonListener(openItemButton));
        
        bottomPanel.add(addItemButton);
        bottomPanel.add(removeItemButton);
        bottomPanel.add(openItemButton);
        bottomPanel.add(goBackButton);
    }
    
    private void displayItemsFromList(ItemList list) {
        itemsPanel.removeAll();
        
        list.sortByName();
        for (Item item : list) {
            itemsPanel.add(new MenuItemMiniPanel(item, this), "wrap");
        }
        
        itemsPanel.repaint();
        itemsPanel.revalidate();
    }

    public MenuItemMiniPanel getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(MenuItemMiniPanel selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    private class AddBookingButtonListener extends ButtonListener {
            
        private AddBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            //to do
        }
    }
    
    private class EditBookingButtonListener extends ButtonListener {
            
        private EditBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            //to do
        }
    }
    
    private class RemoveBookingButtonListener extends ButtonListener {
            
        private RemoveBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            //to do
        }
    }
}
