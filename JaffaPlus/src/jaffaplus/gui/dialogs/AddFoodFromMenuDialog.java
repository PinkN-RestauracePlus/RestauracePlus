package jaffaplus.gui.dialogs;

import jaffaplus.collections.DataStorage;
import jaffaplus.collections.ItemList;
import jaffaplus.collections.Order;
import jaffaplus.gui.panels.FoodSelectionPanel;
import jaffaplus.gui.panels.OrderPanel;
import jaffaplus.gui.panels.Panel;
import jaffaplus.source.GlobalValues;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Hanzik
 */
public class AddFoodFromMenuDialog extends Dialog {
   
    private Order order;
    private OrderPanel panel;
    
    private Panel searchPanel;
    private FoodSelectionPanel foodSelectionPanel;
    
    private JTextField searchField;
    private JScrollPane scrollPane;
        
    private final int FRAME_WIDTH = 420;
    private final int FRAME_HEIGHT = 550;
    
    public AddFoodFromMenuDialog(Order order, OrderPanel panel) {
        
        super();
        this.order = order;
        this.panel = panel;
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        initComponents();
        
        setVisible(true);
    }

    private void initComponents() {
        
        initSearchPanel();
        initFoodSelectionPanel();
        initButtonPanel();
    }
    
    private void initSearchPanel() {     
        
        int SEARCH_WIDTH = 400;
        int SEARCH_FIELD_WIDTH = 300;
        int SEARCH_HEIGHT = 20;
        
        searchPanel = new Panel();
        searchPanel.setBackground(GlobalValues.BACKGROUND_COLOR_DIALOG);
        searchPanel.setPreferredSize(new Dimension(SEARCH_WIDTH, SEARCH_HEIGHT));
        
        JLabel searchLabel = new JLabel("Hledat: ");
        searchField = new JTextField();        
        searchField.setPreferredSize(new Dimension(SEARCH_FIELD_WIDTH, SEARCH_HEIGHT));   
        searchField.addKeyListener(new SearchFieldListener());     
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, "wrap");
    }
    
    private void initFoodSelectionPanel() {        
        
        int SELECTION_WIDTH = 400;
        int SELECTION_HEIGHT = 400;
        
        foodSelectionPanel = new FoodSelectionPanel(DataStorage.getInstance().getFoodMenu());
                
        scrollPane = new JScrollPane(foodSelectionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(GlobalValues.SCROLL_SPEED);    
//        scrollPane.setVerticalScrollBar(...);             //prepared for custom scroll bar visuals
        scrollPane.setPreferredSize(new Dimension(SELECTION_WIDTH, SELECTION_HEIGHT));
        scrollPane.setBorder(BorderFactory.createLineBorder(GlobalValues.BORDER_COLOR));
                              
        getContentPane().add(scrollPane, "alignx center, wrap");        
    }
    
    @Override
    public void add() {
        //Pokud je vybrane jidlo, pridej ho na seznam
        if (foodSelectionPanel.getSelectedFood() != null) {
            order.addItem(foodSelectionPanel.getSelectedFood().getItem());
        }
        
        if (panel != null) {
            panel.repaint();
        } else {
            System.out.println("null");
        }
    }    
    
    @Override
    public void cancel() {
        dispose();
    }
    
    /* Zapne vyhledavani az pote, co od posledniho stisku klavesy ubehne
     * doba DELAY. */
    private class SearchFieldListener extends KeyAdapter {
        
        private long DELAY = 400;
        
        private final java.util.Timer timer = new java.util.Timer(true);
        private TimerTask searchTask;
        private ItemList completeList = DataStorage.getInstance().getFoodMenu();
        
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
                        foodSelectionPanel.displayItemsFromList(completeList);                        
                    } else if (key.length() == 1) {
                        foodSelectionPanel.displayItemsFromList(completeList.getByFirstLetter(key.toLowerCase().charAt(0)));                        
                    } else {
                        foodSelectionPanel.displayItemsFromList(completeList.getBySubstring(key.toLowerCase()));
                    }
                }
            };
            timer.schedule(searchTask, DELAY);
        }        
    }
}