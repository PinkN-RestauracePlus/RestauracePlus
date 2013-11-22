package jaffaplus.calendar;

/**
 *
 * @author Hanzik
 */
public enum MonthsOfYear {
    
    JANUARY(1, 31, "LEDEN"),
    FEBRUARY(2, 28, "ÚNOR"),
    MARCH(3, 31, "BŘEZEN"),
    APRIL(4, 30, "DUBEN"),
    MAY(5, 31, "KVĚTEN"),
    JUNE(6, 30, "ČERVEN"),
    JULY(7, 31, "ČERVENEC"),
    AUGUST(8, 31, "SRPEN"),
    SEPTEMBER(9, 30, "ZÁŘÍ"),
    OCTOBER(10, 31, "ŘÍJEN"),
    DECEMBER(11, 30, "LISTOPAD"),
    NOVEMBER(12, 31, "PROSINEC");
    
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
