package jaffaplus.gui.panels.menueditor;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Item;
import jaffaplus.collections.ItemList;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.dialogs.AddFoodDialog;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import jaffaplus.tools.SearchTool;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class MenuEditorPanel extends Panel {
    
    private ItemList completeList = DataStorage.getInstance().getFoodMenu();    
    private Panel labelPanel, itemsPanel, bottomPanel;        
    private MenuItemMiniPanel selectedItemPanel;
    
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
        searchField.addKeyListener(new SearchFieldListener(completeList));
        
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

    private void resetSelection() {
        selectedItemPanel = null;
    }
    
    private void refresh() {
        displayItemsFromList(completeList);
    }
    
    public MenuItemMiniPanel getSelectedItemPanel() {
        return selectedItemPanel;
    }
    
    public Item getSelectedItem() {
        return selectedItemPanel.getItem();
    }

    public void setSelectedItemPanel(MenuItemMiniPanel selectedItem) {
        this.selectedItemPanel = selectedItem;
    }
    
    private void createDialogWindow(boolean editing) {
        //If editing existing booking
        if (editing) {
            AddFoodDialog dialog = new AddFoodDialog(this, editing);           
        } else {
            AddFoodDialog dialog = new AddFoodDialog(this, editing);            
        }
    }
    
    private void removeSelectedItem() {
        completeList.remove(selectedItemPanel.getItem());
    }
    
    private class AddBookingButtonListener extends ButtonListener {
            
        private AddBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            createDialogWindow(false);
            resetSelection();
            refresh();
        }
    }
    
    private class EditBookingButtonListener extends ButtonListener {
            
        private EditBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            /* Editujeme pouze pokud mame vybranou polozku. */
            if (selectedItemPanel != null) {
                createDialogWindow(true);
                resetSelection();
                refresh();
            }
        }
    }
    
    private class RemoveBookingButtonListener extends ButtonListener {
        
        private RemoveBookingButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            removeSelectedItem();
            refresh();
        }
    }
    
    private class SearchFieldListener implements KeyListener {
        
        private double lastInput = 0;
        private double DELAY = 400;
        
        private SearchTool tool = new SearchTool();
        
        @Override
        public void keyTyped(KeyEvent e) {
            //do nothing
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //do nothing
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
            
            
            tool 
        }
        
    }
}
