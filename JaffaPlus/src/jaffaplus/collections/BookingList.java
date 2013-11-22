package jaffaplus.collections;

import java.util.ArrayList;

/**
 *
 * @author Hanzik
 */
public class BookingList {
    
    private ArrayList<Booking> list = new ArrayList<>();
    
    public void add(Booking booking) {
        list.add(booking);
    }
    
    public BookingList findByDate(int day, int month, int year) {
        BookingList listOfDay = new BookingList();
        
        for (Booking booking : list) {
            if (booking.isBookedForDay(day, month, year)) {
                listOfDay.add(booking);
            }
        }
        
        return listOfDay;        
    }
    
    
    public void remove(Booking booking) {
        list.remove(booking);
    }
    
    /* For testing purposes only */
    public void fillList() {
        //not yet needed
    }
}
