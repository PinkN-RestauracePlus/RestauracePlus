package jaffaplus.calendar;

/**
 *
 * @author Hanzik
 */
public enum MonthsOfYear {
    
    JANUARY(0, 31, "LEDEN"),
    FEBRUARY(1, 28, "ÚNOR"),
    MARCH(2, 31, "BŘEZEN"),
    APRIL(3, 30, "DUBEN"),
    MAY(4, 31, "KVĚTEN"),
    JUNE(5, 30, "ČERVEN"),
    JULY(6, 31, "ČERVENEC"),
    AUGUST(7, 31, "SRPEN"),
    SEPTEMBER(8, 30, "ZÁŘÍ"),
    OCTOBER(9, 31, "ŘÍJEN"),
    DECEMBER(10, 30, "LISTOPAD"),
    NOVEMBER(11, 31, "PROSINEC");
    
    private int month;
    private int length;
    private String name;
    
    private MonthsOfYear(int month, int length, String name) {
        this.month = month;
        this.length = length;
        this.name = name;
    }
    
    public static MonthsOfYear getMonth(int month) {
        return MonthsOfYear.values()[month];
    }
    
    public static int getLength(int month) {
        return getMonth(month).length;
    }
    
    public static String getName(int month) {
        return getMonth(month).name;
    }
}
