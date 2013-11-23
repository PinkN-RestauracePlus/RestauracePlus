package jaffaplus.collections;

/**
 * May be useful later.
 * 
 * @author Hanzik
 */
public class DataStorage {
    
    private BookingList booking;
    private ItemList foodMenu;
    private TableList tables;
        
    private static final DataStorage INSTANCE = new DataStorage();
        
    private DataStorage() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static DataStorage getInstance() {
        return INSTANCE;
    }

    public ItemList getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(ItemList foodMenu) {
        this.foodMenu = foodMenu;
    }

    public TableList getTables() {
        return tables;
    }

    public void setTables(TableList tables) {
        this.tables = tables;
    }

    public BookingList getBooking() {
        return booking;
    }

    public void setBooking(BookingList booking) {
        this.booking = booking;
    }
}
