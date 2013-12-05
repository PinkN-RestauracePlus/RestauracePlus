package jaffaplus.collections;

/**
 * Trida Booking reprezentuje rezervaci stolu; pro kazdou rezervaci je potreba
 * jmeno, cislo stolu, datum a cas.
 * 
 * @author Hanzik
 */
public class Booking {
    
    private String name;
    private int table, personCount, hour, day, month, year;
    
    public Booking(String name, int table, int personCount, int hour, int day, int month, int year) {
        this.name = name;
        this.table = table;
        this.personCount = personCount;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public boolean isBookedForDay(int day, int month, int year) {
        if (this.day == day) {
            if (this.month == month) {
                if (this.year == year) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * @return - Vraci jmeno, na kterou je rezervace zamluvena.
     */
    public String getName() {
        return name;
    }

    public int getTable() {
        return table;
    }

    /**
     * @return - Vraci pocet osob, na kterou je rezervace zamluvena.
     */
    public int getPersonCount() {
        return personCount;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    
    public String getDate() {
        return day + "." + (month + 1) + "." + year;
    }

    @Override
    public String toString() {
        return "Booking{" + "name=" + name + ", hour=" + hour + ", day=" + day + ", month=" + month + ", year=" + year + '}';
    }
}
