package jaffaplus.collections;

import jaffaplus.gui.panels.TablePanel;

/**
 *
 * @author Hanzik
 */
public class Table {
    
    private int tableNumber;
    private int capacity;
    
    private OrderList orderList;
    private TablePanel panel;
    
    public Table(int tableNumber, int capacity) {
        orderList = new OrderList();
        
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }
    
    public void addOrder(Order order) {
        orderList.add(order);
    }
    
    public void removeOrder(Order order) {
        orderList.remove(order);
        
        if (panel != null) {
            panel.repaint();
        }
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public TablePanel getPanel() {
        return panel;
    }

    public void setPanel(TablePanel panel) {
        this.panel = panel;
    }
}
