package com.toast.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tutu on 2017/1/11.
 */

public class TDateUtils {

    private static final String[] SUNDAY_WEEKS = new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private static final String[] MONDAY_WEEKS = new String[]{"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private static final String[] SATURDAY_WEEKS = new String[]{"SAT", "SUN", "MON", "TUE", "WED", "THU", "FRI"};

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int mapDayOfWeekInMonth(int sundayPosition, int mappingWeek) {
        if (mappingWeek <= 0 || mappingWeek == TMonth.SUNDAY_OF_WEEK) {
            return sundayPosition;
        } else {
            String sundayPositionDesc = SUNDAY_WEEKS[sundayPosition - 1];
            if (mappingWeek == TMonth.MONDAY_OF_WEEK) { // monday is the start day of a week
                for (int i = 0; i < MONDAY_WEEKS.length; i++) {
                    if (sundayPositionDesc.equals(MONDAY_WEEKS[i])) {
                        return i + 1;
                    }
                }
            } else if (mappingWeek == TMonth.SATURDAY_OF_WEEK) { // saturday is the start day of a week
                for (int i = 0; i < SATURDAY_WEEKS.length; i++) {
                    if (sundayPositionDesc.equals(SATURDAY_WEEKS[i])) {
                        return i + 1;
                    }
                }
            }
        }
        return sundayPosition;
    }

    /**
     * the first day of month
     *
     * @param year
     * @param month
     * @return week position
     */
    public static int getDayOfWeekInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayCountOfMonth(int year, int month) {
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) { // leap year
            arr[1] = 29;
        }
        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public static TMonth prevMonth(int year, int month) {
        if (month == 1) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        return new TMonth(year, month);
    }

    public static TMonth nextMonth(int year, int month) {
        if (month == 12) {
            year += 1;
            month = 1;
        } else {
            month += 1;
        }
        return new TMonth(year, month);
    }

    public static boolean isPrevMonthDay(int year, int month, int otherYear, int otherMonth) {
        if ((year - otherYear) == 1 && otherMonth == 12) {
            return true;
        }
        if (otherYear != year) {
            return false;
        }
        if (otherMonth + 1 != month) {
            return false;
        }
        return true;
    }

    public static boolean isMonthDay(int year, int month, int otherYear, int otherMonth) {
        if (otherYear != year) {
            return false;
        }
        if (otherMonth != month) {
            return false;
        }
        return true;
    }

    public static boolean isNextMonthDay(int year, int month, int otherYear, int otherMonth) {
        if ((otherYear - year) == 1 && otherMonth == 1) {
            return true;
        }
        if (otherYear != year) {
            return false;
        }
        if (otherMonth - 1 != month) {
            return false;
        }
        return true;
    }

    public static boolean isToday(int year, int month, int day) {
        return year == getCurrentYear() && month == getCurrentMonth() && day == getCurrentDay();
    }

    /**
     * calculate day count
     *
     * @param startYear  start year
     * @param startMonth start month
     * @param startDay   start day
     * @param endYear    end year
     * @param endMonth   end month
     * @param endDay     end day
     * @return day count
     */
    public static int countDays(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        Calendar startC = Calendar.getInstance();
        startC.set(Calendar.YEAR, startYear);
        startC.set(Calendar.MONTH, startMonth - 1);
        startC.set(Calendar.DAY_OF_MONTH, startDay);
        Calendar endC = Calendar.getInstance();
        endC.set(Calendar.YEAR, endYear);
        endC.set(Calendar.MONTH, endMonth - 1);
        endC.set(Calendar.DAY_OF_MONTH, endDay);
        return (int) ((endC.getTimeInMillis() - startC.getTimeInMillis()) / 86400000 + 1);
    }

    public static List<TMonth> generateMonths(int startYear, int endYear) {
        return generateMonths(startYear, 1, endYear, 12, TMonth.SUNDAY_OF_WEEK);
    }

    public static List<TMonth> generateMonths(int startYear, int endYear, @TMonth.WeekType int weekType) {
        return generateMonths(startYear, 1, endYear, 12, weekType);
    }

    public static List<TMonth> generateMonths(int startYear, int startMonth, int endYear, int endMonth) {
        return generateMonths(startYear, startMonth, endYear, endMonth, TMonth.SUNDAY_OF_WEEK);
    }

    public static List<TMonth> generateMonths(int startYear, int startMonth, int endYear, int endMonth, @TMonth
            .WeekType int weekType) {

        if (startYear <= 0 || endYear <= 0 || startMonth <= 0 || endMonth <= 0 || startMonth > 12 || endMonth > 12) {
            throw new IllegalArgumentException("Invalid startYear、startMonth、endYear or endMonth");
        }

        if (startYear > endYear) {
            throw new IllegalArgumentException("startYear must less than endYear");
        }

        if (startYear == endYear && startMonth > endMonth) {
            throw new IllegalArgumentException("startMonth must less than endMonth when startYear equal to endYear");
        }

        List<TMonth> data = new ArrayList<>();
        if (startYear == endYear) {
            for (int i = startMonth; i <= endMonth; i++) {
                data.add(new TMonth(startYear, i, weekType));
            }
        } else {
            for (int i = startMonth; i <= 12; i++) {
                data.add(new TMonth(startYear, i, weekType));
            }
            while (endYear - startYear > 1) {
                startYear++;
                for (int i = 1; i <= 12; i++) {
                    data.add(new TMonth(startYear, i, weekType));
                }
            }
            for (int i = 1; i <= endMonth; i++) {
                data.add(new TMonth(endYear, i, weekType));
            }
        }
        return data;
    }
}

