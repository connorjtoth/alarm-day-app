package com.example.connor.alarmday;

/*
 * File: AlarmDay.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 */

/* Dependencies */
import java.util.Calendar;


/* class AlarmDay
 * Calculates the number of days between today and the next Alarm Day
 * Note: there is no public constructor and only one public static method
 */
class AlarmDay {

    /* private constructor */
    private AlarmDay() {}


    /* boolean isLeapYear
     * Returns whether the given year was a leap year.
     * Precondition: year > 0
     * Postcondition: returns true if year is a leap year, and false if year
     * is not a leap year.
     */
    private static boolean isLeapYear ( int year ) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }


    /* int daysInMonth
     * Returns the number of days in a given month of a given year.
     * Precondition: year > 0, month is valid value for Calendar.MONTH
     */
    private static int daysInMonth ( int month, int year ) {
        switch ( month ) {
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;

            case Calendar.FEBRUARY:
                return ( !isLeapYear(year) ) ? 28 : 29;

            default:
                return 31;
        }
    }


    /* int daysUntil
     * Returns int number of days until the next Alarm Day.
     */
    public static int daysUntil ( ) {
        Calendar today = Calendar.getInstance( );
        return daysToNextWed(today);
    }


    /* int diffWed ( int dayOfWeek )
     * Returns the number of days required to add to C before its DAY_OF_WEEK
     * variable is WEDNESDAY.
     * Precondition: dayOfWeek is a valid Calendar.DAY_OF_WEEK
     */
    private static int diffWed ( int dayOfWeek ) {
        switch ( dayOfWeek ) {
            case Calendar.THURSDAY: return 6;
            case Calendar.FRIDAY: return 5;
            case Calendar.SATURDAY: return 4;
            case Calendar.SUNDAY: return 3;
            case Calendar.MONDAY: return 2;
            case Calendar.TUESDAY: return 1;
            case Calendar.WEDNESDAY: return 0;
            default: return -1;
        }

    }


    /* bool goToNextMonth
       Returns whether the first Wednesday of the MONTH in which date is has
       already occurred. If it has, then we must go to the next month to find
        the next alarm day because it has already happened during this month.
     */
    private static boolean goToNextMonth( Calendar day ) {
        int upperBound = ((day.get(Calendar.DAY_OF_WEEK) + 3) % 7) + 1;
        if (day.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY &&
                day.get(Calendar.DAY_OF_MONTH) == 1) {
            return false;
        }

        return day.get(Calendar.DAY_OF_MONTH) >= upperBound;

        /*switch (day.get(Calendar.DAY_OF_WEEK))
        case Calendar.WEDNESDAY:
            if (day.get(Calendar.DAY_OF_MONTH) == 1) return true;
            break;
        case Calendar.THURSDAY:
            if (day.get(Calendar.DAY_OF_MONTH) < 2) return false;
            break;
        case Calendar.FRIDAY:
            if ( day.get(Calendar.DAY_OF_MONTH) < 3 ) return false;
            break;
        case Calendar.SATURDAY:
            if ( day.get(Calendar.DAY_OF_MONTH) < 4 ) return false;
            break;
        case Calendar.SUNDAY:
            if ( day.get(Calendar.DAY_OF_MONTH) < 5 ) return false;
            break;
        case Calendar.MONDAY:
            if ( day.get(Calendar.DAY_OF_MONTH) < 6 ) return false;
            break;
        case Calendar.TUESDAY:
            if ( day.get(Calendar.DAY_OF_MONTH) < 7) return false;
    */
    }

    /* int daysToNextWed
     * Returns the days needed to add to today to be the first Wednesday of
     * the next month.
     * Preconditions: Optional argument flag forces to move to the next month
     */
    private static int daysToNextWed ( Calendar today ) {
        return daysToNextWed(today, false);
    }

    private static int daysToNextWed ( Calendar today, boolean flag ) {
        Calendar temp = (Calendar) today.clone();
        int rval = 0;
        int daysLeft = 0;

        /* move to the first day of the next month */
        if (goToNextMonth(temp) || flag) {
            daysLeft += daysInMonth(today.get(Calendar.MONTH), today.get(Calendar.YEAR)) - today.get(Calendar.DAY_OF_MONTH) + 1;
            temp.add(Calendar.DAY_OF_MONTH, daysLeft);
            rval += daysLeft;
            daysLeft = 0;
        }

        /* find the first wednesday from the first day of the month */
        daysLeft += diffWed( temp.get(Calendar.DAY_OF_WEEK) );
        temp.add(Calendar.DAY_OF_MONTH, daysLeft);
        rval += daysLeft;
        daysLeft = 0;

        /* this will prevent alarm days when school is not in session */
        while ( temp.get(Calendar.MONTH) == Calendar.JANUARY ) {
            daysLeft = daysToNextWed(temp, true);
            temp.add(Calendar.DAY_OF_MONTH, daysLeft);
            rval += daysLeft;
            daysLeft = 0;
        }
        return rval;
    }
}
