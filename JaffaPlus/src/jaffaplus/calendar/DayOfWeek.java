package jaffaplus.calendar;

/**
 *
 * @author Hanzik
 */
public enum DayOfWeek {
    
    MONDAY(1, "PO"),
    TUESDAY(2, "ÚT"),
    WEDNESDAY(3, "ST"),
    THURSDAY(4, "ČT"),
    FRIDAY(5, "PÁ"),
    SATURDAY(6, "SO"),
    SUNDAY(7, "NE");
    
    private int day;
    private String acronym;
    
    private DayOfWeek(int day, String acronym) {
        this.day = day;
        this.acronym = acronym;
    }
    
    public static DayOfWeek getDay(int day) {
        return DayOfWeek.values()[day];
    }
    
    public static String getAcronym(int day) {
        return getDay(day).acronym;
    }
}