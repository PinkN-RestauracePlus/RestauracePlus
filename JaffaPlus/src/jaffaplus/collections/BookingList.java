package jaffaplus.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Hanzik
 */
public class BookingList implements Iterable<Booking> {
    
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
        listOfDay.sortByTime();
        return listOfDay;        
    }    

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    private void sortByTime() {
        Collections.sort(list, new HourComparator());
    }
    
    public void remove(Booking booking) {
        list.remove(booking);
    }
    
    @Override
    public Iterator<Booking> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return "BookingList{" + "list=" + list + '}';
    }
    
    /* For testing purposes only */
    public void fillList() {
        add(new Booking("Jimmy Tulipán", 2, 12, 15, 25, 11, 2013));
        add(new Booking("Jirka Polička", 3, 10, 17, 25, 11, 2013));
        add(new Booking("George Orwell", 5, 8, 20, 25, 11, 2013));
        add(new Booking("Edward Kenwey", 1, 2, 12, 25, 11, 2013));
        add(new Booking("Leeroy Jenkins", 10, 1, 8, 25, 11, 2013));
        add(new Booking("Pěstební dělnice", 2, 12, 15, 26, 11, 2013));
        add(new Booking("Pan Demeter", 2, 12, 15, 2, 12, 2013));
        add(new Booking("Cookie Monster", 2, 12, 15, 26, 1, 2014));
    }
    
    /* Comparator that sorts bookings from earliest hour to latest.
     * Most usefull for bookings of one day.
     */
    private class HourComparator implements Comparator<Booking> {
        
        @Override
        public int compare(Booking b1, Booking b2) {
            int hour1 = b1.getHour();
            int hour2 = b2.getHour();
            if (hour1 < hour2) {
                return -1;
            } else if (hour1 == hour2) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
