package jaffaplus.collections;

/**
 *
 * @author Lukas
 */
public class Supplier {
    
    private String name;
    private String id;
    private String address;
    private String bankAcc;
    
    
       
    public Supplier(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    public Supplier(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }
    
    public Supplier(String name, String id, String address, String bankAcc) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.bankAcc = bankAcc;
    }
    
       
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

   
    

    
   
}