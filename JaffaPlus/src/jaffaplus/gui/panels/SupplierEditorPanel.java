package jaffaplus.gui.panels;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.Supplier;
import jaffaplus.collections.SuppliersList;
import jaffaplus.gui.buttons.Button;
import jaffaplus.gui.buttons.ButtonListener;
import jaffaplus.gui.dialogs.AddSupplierDialog;
import jaffaplus.source.GlobalValues;
import jaffaplus.source.Path;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lukas
 */
public class SupplierEditorPanel extends Panel {
    
    private SuppliersList completeList = DataStorage.getInstance().getSuppliers();
    private Panel labelPanel, suppliersPanel, bottomPanel;        
    private SuppliersMiniPanel selectedSupplierPanel;
    
    private JTextField searchField;
    
    public SupplierEditorPanel() {
        super();       
        
        initComponents();
    }    

    private void initComponents() {
        
        initLabel();
        initSuppliers();
        initSearchField();
        initButtons();
        
        add(labelPanel, "alignx center, aligny top, span, wrap");
        add(suppliersPanel, "align center, span, wrap");
        add(bottomPanel, "push, alignx center, aligny bottom");
    }

    private void initLabel() {
        labelPanel = new Panel();
        JLabel label = new JLabel("Editor dodavatelů");
        label.setFont(new Font("Calibri", Font.BOLD, 30));
        labelPanel.add(label, "alignx center, aligny top, span, wrap");
        
    }

    private void initSuppliers() {
        suppliersPanel = new Panel();
        displaySuppliersFromList(completeList);
    }
    
    private void initSearchField() {     
        
        int width = 300;
        int height = 40;
        
        bottomPanel = new Panel();
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(width, height));
        searchField.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchField.addKeyListener(new SupplierEditorPanel.SearchFieldListener());
        
        bottomPanel.add(searchField);
    }        
    
    private void initButtons() {
        
        Button addSupplierButton = new Button("+");
        Button removeSupplierButton = new Button("-");
        Button openSupplierButton = new Button("Detaily");
        Button goBackButton = new Button("Zpět", GlobalValues.PANEL_MAINMENU);
        
        addSupplierButton.loadIcons(Path.BUTTONS_CONTROL_ADD_INACTIVE, Path.BUTTONS_CONTROL_ADD_ACTIVE, Path.BUTTONS_CONTROL_ADD_CLICKED);
        removeSupplierButton.loadIcons(Path.BUTTONS_CONTROL_REMOVE_INACTIVE, Path.BUTTONS_CONTROL_REMOVE_ACTIVE, Path.BUTTONS_CONTROL_REMOVE_CLICKED);
        openSupplierButton.loadIcons(Path.BUTTONS_CONTROL_DETAIL_INACTIVE, Path.BUTTONS_CONTROL_DETAIL_ACTIVE, Path.BUTTONS_CONTROL_DETAIL_CLICKED);
        goBackButton.loadIcons(Path.BUTTONS_CONTROL_GOBACK_INACTIVE, Path.BUTTONS_CONTROL_GOBACK_ACTIVE, Path.BUTTONS_CONTROL_GOBACK_CLICKED);

        addSupplierButton.addMouseListener(new SupplierEditorPanel.AddSupplierButtonListener(addSupplierButton));        
        removeSupplierButton.addMouseListener(new SupplierEditorPanel.RemoveSupplierButtonListener(removeSupplierButton));
        openSupplierButton.addMouseListener(new SupplierEditorPanel.EditSupplierButtonListener(openSupplierButton));
        
        bottomPanel.add(addSupplierButton);
        bottomPanel.add(removeSupplierButton);
        bottomPanel.add(openSupplierButton);
        bottomPanel.add(goBackButton);
    }
   
    private void displaySuppliersFromList(SuppliersList list) {
        suppliersPanel.removeAll();
        
        list.sortByName();
        if(list.isEmpty() ){
            suppliersPanel.add( new JLabel("Nenalezeny žádné záznamy."));
        }
        else{
            for (Supplier supplier : list) {
                suppliersPanel.add(new SuppliersMiniPanel(supplier, this), "wrap");
            }
        }
        suppliersPanel.repaint();
        suppliersPanel.revalidate();
    }

    private void resetSelection() {
        selectedSupplierPanel = null;
        searchField.setText("");
    }
    
    private void refresh() {
        displaySuppliersFromList(completeList);
    }
    
    public SuppliersMiniPanel getSelectedSupplierPanel() {
        return selectedSupplierPanel;
    }
    
    public Supplier getSelectedSupplier() {
        return selectedSupplierPanel.getSupplier();
    }

    public void setSelectedSupplierPanel(SuppliersMiniPanel selectedSupplier) {
        this.selectedSupplierPanel = selectedSupplier;
    }
    
   private void createDialogWindow(boolean editing) {
        
        if (editing) {
            AddSupplierDialog dialog = new AddSupplierDialog(this, editing);           
        } else {
            AddSupplierDialog dialog = new AddSupplierDialog(this, editing);            
        }
    }
    
    private void removeSelectedSupplier() {
        completeList.remove(selectedSupplierPanel.getSupplier());
    }
    
    private class AddSupplierButtonListener extends ButtonListener {
            
        private AddSupplierButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            createDialogWindow(false);
            resetSelection();
            refresh();
        }
    }
    
    private class EditSupplierButtonListener extends ButtonListener {
            
        private EditSupplierButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            /* Editujeme pouze pokud mame vybranou polozku. */
            if (selectedSupplierPanel != null) {
               createDialogWindow(true);
                resetSelection();
                refresh();
            }
        }
    }
    
    /* Pri kliknuti odstrani zvolenou polozku ze seznamu. */
    private class RemoveSupplierButtonListener extends ButtonListener {
        
        private RemoveSupplierButtonListener(Button button) {
            super(button);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedSupplierPanel != null) {
            removeSelectedSupplier();
            refresh();
        }}
    }
    
    /* Zapne vyhledavani az pote, co od posledniho stisku klavesy ubehne
     * doba DELAY. */
    private class SearchFieldListener extends KeyAdapter {
        
        private long DELAY = 400;
        
        private final Timer timer = new Timer(true);
        private TimerTask searchTask;
                
        @Override
        public void keyReleased(KeyEvent e) { 
            if(searchTask != null) {
                searchTask.cancel();
            }
            searchTask = new TimerTask() {
                @Override
                public void run() {
                    String key = searchField.getText();
                    if (key.length() == 0) {
                        displaySuppliersFromList(completeList);                        
                    } else if (key.length() == 1) {
                        displaySuppliersFromList(completeList.getByFirstLetter(key.toLowerCase().charAt(0)));                        
                    } else {
                        displaySuppliersFromList(completeList.getBySubstring(key.toLowerCase()));
                    }
                }
            };
            timer.schedule(searchTask, DELAY);
        }        
    }
    
    
    
}
