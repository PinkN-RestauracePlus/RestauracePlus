package jaffaplus.collections;

/**
 *
 * @author Hanzik
 */
public class Item {
    
    private String name;
    private String id;
    private int price;
    private int classification;
    
    public Item(String name) {
        this.name = name;
    }
    
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public Item(String name, String id, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }
}
