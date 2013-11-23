package jaffaplus.calendar;

import java.util.Calendar;

/**
 * Instance mesice, ktera drzi informace o delce mesice, jmene prvniho dne apod.
 * 
 * @author Hanzik
 */
public class Month {

    private int month, year, length, startDay, endDay;
    private String name;
    
    public Month(int month, int year) {
        this.month = month;
        this.year = year;
        this.length = MonthsOfYear.getLength(month);
        this.name = MonthsOfYear.getName(month);
        calculateParameters();
    }    

    /**
     * Vypocita jmeno prvniho dne v mesici a jmeno posledniho dne.
     */
    private void calculateParameters() {
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, month);        
        cal.set(Calendar.YEAR, year);
        
        startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        /** Calendar zacina tyden v nedeli, nase cislovani zacina pondelim. */
        if (startDay == 0) {
            startDay = 7;
        }
        
        cal.set(Calendar.DAY_OF_MONTH, length);
        
        endDay = cal.get(Calendar.DAY_OF_WEEK);

        cal.set(Calendar.DAY_OF_MONTH, 1);
    }
    
    /** Vrati instanci predchoziho mesice. */
    public Month getPreviousMonth() {
        if (month != 0) {
            return new Month(month - 1, year);
        }
        return new Month(11, year - 1);
    }
    
    /** Vrati instanci dalsiho mesice. */
    public Month getNextMonth() {
        if (month != 11) {
            return new Month(month + 1, year);
        }
        return new Month(0, year + 1);
    }

    public int getLength() {
        return length;
    }

    public int getStartDay() {
        return startDay;
    }

    public String getName() {
        return name;
    }

    public int getMonthNumber() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return name + " " + year;
    }
}
