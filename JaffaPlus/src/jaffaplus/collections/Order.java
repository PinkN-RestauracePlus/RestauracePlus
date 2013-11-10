package jaffaplus.collections;

import java.util.Random;

/**
 *
 * @author Hanzik
 */
public class Order {
    
    private String orderName;
    private int orderNumber;
    private ItemList itemList;
    
    private final int orderNumberSize = 100000;
    
    public Order(String name) {
        
        this.orderName = name;
        itemList = new ItemList();
        
        generateOrderNumber();
    }
        
    public void addItem(Item item) {
        itemList.add(item);
    }
    
    private void generateOrderNumber() {
        Random rand = new Random();
        orderNumber = orderNumberSize + rand.nextInt(orderNumberSize - 1);        
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    }
    
    public int getTotalPrice() {
        return itemList.getTotalPrice();
    }
}
