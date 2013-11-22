package jaffaplus.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hanzik
 */
public class Month {

    private int month, year, length, startDay, endDay;
    
    public Month(int month, int year) {
        this.month = month;
        this.year = year;
        this.length = MonthsOfYear.getLength(month);
        calculateParameters();
    }    

    private void calculateParameters() {
        calculateStart();
        calculateEnd();
    }

    private void calculateStart() {
        Calendar cal =Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        Date firstDayOfMonth = cal.getTime();  

        DateFormat sdf = new SimpleDateFormat("EEEEEEEE");  
        
        System.out.println("First Day of Month: " + sdf.format(firstDayOfMonth)); 
    }

    private void calculateEnd() {
        
    }
}
