////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

////////////////////////////////////////
// Date Manager
////////////////////////////////////////

public class DateManager {

    public static int getDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH)+1;
    }

    public static int getWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.WEEK_OF_YEAR)+1;

    }

    public static int getYear(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    public static String getUniqueWeekTag(){
        return "w_"+getWeek()+"_"+getYear();
    }

    public static String getUniqueMonthTag(){
        return "m_"+getMonth()+"_"+getYear();
    }


    public static String getUniqueWeekTag(int week, int year){
        return "w_"+week+"_"+year;
    }

    public static String getUniqueMonthTag(int month, int year){
        return "m_"+month+"_"+year;
    }

    public static String getStringMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault());
    }

    public static void main(String[] args){
        System.out.println("DateManager test.");
        System.out.println("Current day: " + getDay());
        System.out.println("Current week (of year): " + getWeek());
        System.out.println("Current month: " + getMonth());
        System.out.println("Current month (string): " + getStringMonth());
        System.out.println("Current year: " + getYear());
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////