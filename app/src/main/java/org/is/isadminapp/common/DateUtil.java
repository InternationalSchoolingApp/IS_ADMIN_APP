package org.is.isadminapp.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

public interface DateUtil {
    long ONE_HOUR = 60 * 60 * 1000L;
    String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    String[] DAYS = { "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    String STANDARD_DATE_FORMAT = "MMM dd, yyyy hh:mm aa";
    String STANDARD_DATE_FORMAT_4 = "MMM dd, yyyy hh:mm a";
    String STANDARD_DATE_FORMAT_5 = "yyyy-MM-dd'T'HH:mm:ss";
    String STANDARD_DATE_FORMAT_1 = "yyyy-MM-dd";
    String STANDARD_DATE_FORMAT_2 = "MM-dd-yyyy";
    String STANDARD_DATE_FORMAT_3 = "MM/dd/yyyy";
    String STANDARD_DATE_FORMAT_7 = "dd-MM-yyyy";
    String STANDARD_DATE_FORMAT_8 = "MMM dd,yyyy";
    String STANDARD_DATE_FORMAT_ONLY = "MMM dd, yyyy";
    String STANDARD_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String STANDARD_DATE_TIME_FORMAT_1 = "dd MMM yyyy";
    String STANDARD_DATE_TIME_FORMAT_2 = "dd MMM yyyy HH:mm:ss";
    String STANDARD_DATE_TIME_FORMAT_3 = "EEE, d MMM yyyy hh:mm aaa";
    String STANDARD_DATE_TIME_FORMAT_4 = "MMM dd-yyyy hh:mm a";
    String STANDARD_DATE_TIME_FORMAT_CT = "MMM dd, yyyy hh:mm:ss";
    String STANDARD_DATE_TIME_FORMAT_5 = "yyyy-MM-dd HH:mm:ss.sss";
    String STANDARD_TIME_FORMAT_1 = "hh:mm a";
    String STANDARD_TIME_FORMAT_2 = "HH:mm:ss";
    String STANDARD_TIME_FORMAT_3 = "hh:mm aa";
    String STANDARD_TIME_FORMAT_4 = "HH:mm";
    String STANDARD_TIME_FORMAT_5 = "E, hh:mm aa";
    String STANDARD_TIME_FORMAT_6 = "E, HH:mm";
    String STANDARD_TIME_FORMAT_7 = "EEE, MMM dd yyyy";
    String STANDARD_TIME_FORMAT_8 = "MMM dd yyyy hh:mm a";
    String STANDARD_TIME_FORMAT_9 = "EEE dd MMM, yyyy hh:mm a";
    String STANDARD_DATE_TIME_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";

    static Timestamp getCurrentDate() {
        return new Timestamp(new GregorianCalendar().getTimeInMillis());
    }

    static Date getCurrentDateTime() {

        String toTimezone = "Asia/Singapore";
        Date currentUserDate = new Date();
        Date convertedUserDate = convertDateWithTimezone(new DateTime().getZone().toString(),
                toTimezone, currentUserDate,
                DateUtil.STANDARD_DATE_FORMAT_5, DateUtil.STANDARD_TIME_FORMAT_2);

        return convertedUserDate;
    }

    static Date convertToDateViaInstant(final LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date getTodayDate() {
        return Date.from(Instant.now());
    }

    static String getFormattedDate() {
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return formatter.format(new Date());
    }

    public static Date convertStringToDate(final String dateStr) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_1);
        final Date date;
        try {
            date = dateFormat.parse(dateStr);
            return date;
        } catch (final Exception exception) {
            return null;
        }
    }

    public static Date convertStringToDate(final String dateStr, String format) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        final Date date;
        try {
            date = dateFormat.parse(dateStr);
            return date;
        } catch (final Exception exception) {
            return null;
        }
    }

    public static Date convertStringToDateWithCurrentTime(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        Date resultDate = null;
        String hours = date.getHours() < 10 ? "0" + date.getHours() : "" + date.getHours();
        String minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : "" + date.getMinutes();
        String seconds = "00";
        String time = " " + hours + ":" + minutes + ":" + seconds;
        try {
            resultDate = dateFormat.parse(dateStr + time);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return null;
        }
        return resultDate;
    }

    public static String convertDateToString(final Date date) {
        if (date == null) {
            return "";
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_2);
        final String datestr;
        try {
            datestr = dateFormat.format(date);
            return datestr;
        } catch (final Exception exception) {
            return null;
        }
    }

    public static String convertDateToStringFormat(final Date date) {
        if (date == null) {
            return "";
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_ONLY);
        final String datestr;
        try {
            datestr = dateFormat.format(date);
            return datestr;
        } catch (final Exception exception) {
            return null;
        }
    }

    public static String convertDate(final Date date) {
        if (date == null) {
            return "";
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.STANDARD_DATE_FORMAT);
        final String datestr;
        try {
            datestr = dateFormat.format(date);
            return datestr;
        } catch (final Exception exception) {
            return null;
        }
    }

    static LocalDate getDateWithoutTime() {
        return LocalDate.now();
    }

    static String getStringDateFromDate(final Date date) {
        if (date == null)
            return null;
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return simpleDateformat.format(date);
    }

    static String getStringDateFromDate(final Date date, final String dateFormat) {
        if (date == null || dateFormat == null)
            return null;
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(dateFormat);
        return simpleDateformat.format(date);
    }

    static String getStringDateFromDateForPDF(final Date date) {
        if (date == null)
            return null;
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(STANDARD_DATE_FORMAT_1);
        return simpleDateformat.format(date);
    }

    static String getStringDateFromDateForId(final Date date) {
        if (date == null)
            return null;
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyMMddhhmmss");
        return simpleDateformat.format(date);
    }

    public static String getDaysNameById(String daysId) {
        String dayName = "";
        if (Integer.valueOf(daysId).equals(1)) {
            dayName = "Sunday";
        } else if (Integer.valueOf(daysId).equals(2)) {
            dayName = "Monday";
        } else if (Integer.valueOf(daysId).equals(3)) {
            dayName = "Tuesday";
        } else if (Integer.valueOf(daysId).equals(4)) {
            dayName = "Wednesday";
        } else if (Integer.valueOf(daysId).equals(5)) {
            dayName = "Thursday";
        } else if (Integer.valueOf(daysId).equals(6)) {
            dayName = "Friday";
        } else if (Integer.valueOf(daysId).equals(7)) {
            dayName = "Saturday";
        }
        return dayName;
    }

    static String getStringTimeFromDate(final Date date) {
        if (date == null)
            return null;
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(STANDARD_TIME_FORMAT_4);
        return simpleDateformat.format(date);
    }

    static LocalDate getYesterdayDateWithoutTime() {
        return LocalDate.now().minusDays(1L);
    }

    static LocalDate getTomorrowDateWithoutTime() {
        return LocalDate.now().plusDays(1L);
    }

    static LocalDate getDateAfterThreeMonths() {
        return LocalDate.now().plusMonths(3L);
    }

    static LocalDate getDateBefore7Days() {
        return LocalDate.now().minusWeeks(1L);
    }

    static LocalDate getDateBefore1Month() {
        return LocalDate.now().minusMonths(1L);
    }

    static LocalDate getFirstDayOfQuarter(final Date date) {
        final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.with(localDate.getMonth().firstMonthOfQuarter()).with(TemporalAdjusters.firstDayOfMonth());
    }

    static LocalDate getLastDayOfQuarter(final Date date) {
        final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.with(localDate.getMonth().firstMonthOfQuarter()).with(TemporalAdjusters.lastDayOfMonth());
    }

    static String getFistDateofPreviousYear() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return DateUtil.getStringDateFromDateForPDF(cal.getTime());
    }

    static String getLastDateOfPreviousYear() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return DateUtil.getStringDateFromDateForPDF(cal.getTime());
    }

    static LocalDate getFistDateofCurrentYear() {

        final LocalDate date = LocalDate.now();
        return date.with(TemporalAdjusters.firstDayOfYear());

    }

    static Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    static Date getLastDateOfCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    static Date getDateByPassingHHMM(final String hhmm) {
        final String splitted[] = hhmm.split("\\:");
        final Calendar calendar1 = new Calendar.Builder().set(Calendar.DATE, 0)
                .set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitted[0]))
                .set(Calendar.MINUTE, Integer.parseInt(splitted[1])).set(Calendar.SECOND, 0)
                .set(Calendar.MILLISECOND, 0).build();
        return calendar1.getTime();
    }

    static Date getLastSundayDateWithoutTime(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == 1) {
            // sunday
            // do nothing
        } else if (weekDay == 2)
            // monday
            cal.add(Calendar.DATE, -1);
        else if (weekDay == 3)
            // tuesday
            cal.add(Calendar.DATE, -2);
        else if (weekDay == 4)
            // wedness day
            cal.add(Calendar.DATE, -3);
        else if (weekDay == 5)
            // thursday
            cal.add(Calendar.DATE, -4);
        else if (weekDay == 6)
            // friday
            cal.add(Calendar.DATE, -5);
        else if (weekDay == 7)
            // satarday
            cal.add(Calendar.DATE, -6);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    static Date getNextSundayDateWithoutTime(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == 1) {
            // sunday
            // do nothing
        } else if (weekDay == 2) {
            // monday
            cal.add(Calendar.DATE, 6);
        } else if (weekDay == 3) {
            // tuesday
            cal.add(Calendar.DATE, 5);
        } else if (weekDay == 4) {
            // wedness day
            cal.add(Calendar.DATE, 4);
        } else if (weekDay == 5) {
            // thursday
            cal.add(Calendar.DATE, 3);
        } else if (weekDay == 6) {
            // friday
            cal.add(Calendar.DATE, 2);
        } else if (weekDay == 7) {
            // satarday
            cal.add(Calendar.DATE, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    static Date getWeekFirstDate(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DATE, -1);
        }
        // cal.add(Calendar.DATE, +6);
        Date firstDayWeek = cal.getTime();
        return firstDayWeek;
    }

    static Date getWeekLastDate(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DATE, -1);
        }
        cal.add(Calendar.DATE, +6);
        Date lastDayWeek = cal.getTime();
        return lastDayWeek;
    }

    static Date getWeekPlushDate(Date date, Integer weekDays) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, weekDays);
        // cal.add(Calendar.DATE, +6);
        Date firstDayWeek = cal.getTime();
        return firstDayWeek;
    }

    static Date getFirstDateOfMonthWithoutTime(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    static Date getFirstDateOfNextMonthWithoutTime(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    static String getFormattedDateLikeGmail(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final Date currentDate = new Date();
        final Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);
        String formattedDate = "";
        String datePostfix = "";
        if (cal.get(Calendar.DATE) == 1 || cal.get(Calendar.DATE) == 21 || cal.get(Calendar.DATE) == 31)
            datePostfix = "st";
        else if (cal.get(Calendar.DATE) == 2 || cal.get(Calendar.DATE) == 22)
            datePostfix = "nd";
        else if (cal.get(Calendar.DATE) == 3 || cal.get(Calendar.DATE) == 23)
            datePostfix = "rd";
        else
            datePostfix = "th";
        if (cal.get(Calendar.ERA) == currentCal.get(Calendar.ERA)
                && cal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR)
                && cal.get(Calendar.DAY_OF_YEAR) == currentCal.get(Calendar.DAY_OF_YEAR)) {
            if (cal.get(Calendar.HOUR_OF_DAY) > 11)
                formattedDate = (cal.get(Calendar.HOUR_OF_DAY) - 12 == 0 ? "12" : cal.get(Calendar.HOUR_OF_DAY) - 12)
                        + ":"
                        + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE))
                        + " pm";
            else
                formattedDate = (cal.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : cal.get(Calendar.HOUR_OF_DAY)) + ":"
                        + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE))
                        + " am";
        } else if (cal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR))
            formattedDate = cal.get(Calendar.DATE) + datePostfix + " "
                    + DateUtil.month[cal.get(Calendar.MONTH)];
        else
            formattedDate = cal.get(Calendar.DATE) + datePostfix + " "
                    + DateUtil.month[cal.get(Calendar.MONTH)] + " "
                    + cal.get(Calendar.YEAR);
        return formattedDate;
    }

    static boolean compareDate(final Date date) {
        final Calendar cal = Calendar.getInstance();
        final Calendar currentCal = Calendar.getInstance();
        cal.setTime(date);
        currentCal.setTime(DateUtil.getCurrentDate());
        return cal.after(currentCal);
    }

    static int getTrialLeft(final Date expDate) {
        final long timeDiff = expDate.getTime() - new Date().getTime();
        final long dateDiff = timeDiff / (DateUtil.ONE_HOUR * 24);
        return (int) dateDiff;
    }

    static String getFormattedDate(final Date date) {
        if (date == null)
            return null;
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return formatter.format(date);
    }

    static String getFormattedDateWithTime(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formattedDate = "";
        String datePostfix = "";
        String dayTime = "";
        if (cal.get(Calendar.DATE) == 1 || cal.get(Calendar.DATE) == 21 || cal.get(Calendar.DATE) == 31)
            datePostfix = "st";
        else if (cal.get(Calendar.DATE) == 2 || cal.get(Calendar.DATE) == 22)
            datePostfix = "nd";
        else if (cal.get(Calendar.DATE) == 3 || cal.get(Calendar.DATE) == 23)
            datePostfix = "rd";
        else
            datePostfix = "th";
        if (cal.get(Calendar.HOUR_OF_DAY) > 11)
            dayTime = (cal.get(Calendar.HOUR_OF_DAY) - 12 == 0 ? "12" : cal.get(Calendar.HOUR_OF_DAY) - 12) + ":"
                    + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE))
                    + " pm";
        else
            dayTime = (cal.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : cal.get(Calendar.HOUR_OF_DAY)) + ":"
                    + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE))
                    + " am";
        formattedDate = cal.get(Calendar.DATE) + datePostfix + " "
                + DateUtil.month[cal.get(Calendar.MONTH)] + " "
                + cal.get(Calendar.YEAR) + " @ " + dayTime;
        return formattedDate;
    }

    static String getDeliveryDateFromCreateDate(Date date) {
        if (date == null)
            return null;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 5);
        date = cal.getTime();
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return formatter.format(date);
    }

    static String getNextDate(final Date currnetDate, final int day, String dateFormat) {
        if (currnetDate == null)
            return null;
        final Date nextHourDate = new Date();
        nextHourDate.setTime(currnetDate.getTime() + (day * 24 * 60 * 60 * 1000));
        final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(nextHourDate);
    }

    static String getDifferenceDate(final Date currnetDate, final int day, String dateFormat) {
        if (currnetDate == null)
            return null;

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currnetDate);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(calendar.getTime());
    }

    static String getNextDate(final java.sql.Date currnetDate, final int day) {
        final Date nextHourDate = new Date();
        nextHourDate.setTime(currnetDate.getTime() + day * 24 * 60 * 60 * 1000);
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return formatter.format(nextHourDate);
    }

    static String getCurrentYear() {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(new Date());
    }

    static String getCurrentYearTwoDigit() {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("yy");
        return simpleDateformat.format(new Date());
    }

    static String convertDateToFormattedType(final Date date, final String format) {
        if (date == null || format == null)
            return null;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    static int daysBetweenTwoDate(final Date startDate, final Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24 * 365));
    }

    static boolean isValidDate(final String inDate) {
        if (inDate == null)
            return false;
        // set the format to use as a constructor argument
        final SimpleDateFormat dateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        if (inDate.trim().length() != dateFormat.toPattern().length())
            return false;
        dateFormat.setLenient(false);
        try {
            // parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (final Exception pe) {
            return false;
        }
        return true;
    }

    static int getDaysLeft(final Date createDate) {
        final long timeDiff = new Date().getTime() - createDate.getTime();
        final long dateDiff = timeDiff / (DateUtil.ONE_HOUR * 24);
        return (int) dateDiff;
    }

    static String getDateInDesiredFormatAsString(final Date date, final String format) {
        if (date == null || format == null)
            return null;
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    static String getFormattedDateLikeGmail(final Date date, final boolean withTime) {
        if (date == null)
            return null;
        if (!withTime) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            final Date currentDate = new Date();
            final Calendar currentCal = Calendar.getInstance();
            currentCal.setTime(currentDate);
            String formattedDate = "";
            String datePostfix = "";
            if (cal.get(Calendar.DATE) == 1 || cal.get(Calendar.DATE) == 21 || cal.get(Calendar.DATE) == 31)
                datePostfix = "st";
            else if (cal.get(Calendar.DATE) == 2 || cal.get(Calendar.DATE) == 22)
                datePostfix = "nd";
            else if (cal.get(Calendar.DATE) == 3 || cal.get(Calendar.DATE) == 23)
                datePostfix = "rd";
            else
                datePostfix = "th";
            if (cal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR))
                formattedDate = cal.get(Calendar.DATE) + datePostfix + " "
                        + DateUtil.month[cal.get(Calendar.MONTH)];
            else
                formattedDate = cal.get(Calendar.DATE) + datePostfix + " "
                        + DateUtil.month[cal.get(Calendar.MONTH)]
                        + " " + cal.get(Calendar.YEAR);
            return formattedDate;
        } else {
            return DateUtil.getFormattedDateLikeGmail(date);
        }
    }

    static Date changeDateFormat(String yyyy, final String m, final String dd) {
        String mmm = null;
        final int mm = Integer.parseInt(m);
        switch (mm) {
            case 1:
                mmm = "Jan";
                break;
            case 2:
                mmm = "Feb";
                break;
            case 3:
                mmm = "Mar";
                break;
            case 4:
                mmm = "Apr";
                break;
            case 5:
                mmm = "May";
                break;
            case 6:
                mmm = "Jun";
                break;
            case 7:
                mmm = "Jul";
                break;
            case 8:
                mmm = "Aug";
                break;
            case 9:
                mmm = "Sep";
                break;
            case 10:
                mmm = "Oct";
                break;
            case 11:
                mmm = "Nov";
                break;
            case 12:
                mmm = "Dec";
                break;
            default:
                break;
        }
        if (yyyy.contains("Before "))
            yyyy = yyyy.replace("Before ", "");
        else if (yyyy.contains(" or earlier "))
            yyyy = yyyy.replace(" or earlier ", "");
        final DateFormat formatter;
        Date date = new Date();

        formatter = new SimpleDateFormat("dd-MMM-yy");
        try {
            date = formatter.parse(dd + "-" + mmm + "-" + yyyy);
        } catch (final ParseException exception) {
            return null;
        }

        return date;
    }

    static Date changeDateFormat(final String dateInString) {
        String mm = null;
        final String[] loopDate = dateInString.split("-");
        if (loopDate[1].equalsIgnoreCase("01"))
            mm = "Jan";
        if (loopDate[1].equalsIgnoreCase("02"))
            mm = "Feb";
        if (loopDate[1].equalsIgnoreCase("03"))
            mm = "Mar";
        if (loopDate[1].equalsIgnoreCase("04"))
            mm = "Apr";
        if (loopDate[1].equalsIgnoreCase("05"))
            mm = "May";
        if (loopDate[1].equalsIgnoreCase("06"))
            mm = "Jun";
        if (loopDate[1].equalsIgnoreCase("07"))
            mm = "Jul";
        if (loopDate[1].equalsIgnoreCase("08"))
            mm = "Aug";
        if (loopDate[1].equalsIgnoreCase("09"))
            mm = "Sep";
        if (loopDate[1].equalsIgnoreCase("10"))
            mm = "Oct";
        if (loopDate[1].equalsIgnoreCase("11"))
            mm = "Nov";
        if (loopDate[1].equalsIgnoreCase("12"))
            mm = "Dec";
        final DateFormat formatter;
        Date date = new Date();

        formatter = new SimpleDateFormat("dd-MMM-yy");
        try {
            date = formatter.parse(loopDate[0] + "-" + mm + "-" + loopDate[2]);
        } catch (final ParseException exception) {
            return null;
        }

        return date;
    }

    static Date changeDateFormatToDateTime(final String dateInString) {
        final DateFormat formatter;
        Date date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = formatter.parse(dateInString);
        } catch (final ParseException exception) {
            return null;
        }

        return date;
    }

    static String changeDateFormatDDMMYYYY(final String dateTimeStamp) {
        String mm = null;
        final String[] loopDate = dateTimeStamp.split(" ");
        if (loopDate[1].equalsIgnoreCase("jan"))
            mm = "01";
        if (loopDate[1].equalsIgnoreCase("feb"))
            mm = "02";
        if (loopDate[1].equalsIgnoreCase("mar"))
            mm = "03";
        if (loopDate[1].equalsIgnoreCase("apr"))
            mm = "04";
        if (loopDate[1].equalsIgnoreCase("may"))
            mm = "05";
        if (loopDate[1].equalsIgnoreCase("jun"))
            mm = "06";
        if (loopDate[1].equalsIgnoreCase("jul"))
            mm = "07";
        if (loopDate[1].equalsIgnoreCase("aug"))
            mm = "08";
        if (loopDate[1].equalsIgnoreCase("sep"))
            mm = "09";
        if (loopDate[1].equalsIgnoreCase("oct"))
            mm = "10";
        if (loopDate[1].equalsIgnoreCase("nov"))
            mm = "11";
        if (loopDate[1].equalsIgnoreCase("dec"))
            mm = "12";
        return loopDate[2] + "-" + mm + "-" + loopDate[5];
    }

//    static Date changeDateFormatToDate(final String dateInString, final String dateFormat) {
//        try {
//            if (ValidatorUtil.isValid(dateInString)) {
//                return new SimpleDateFormat(dateFormat).parse(dateInString);
//            }
//            return null;
//        } catch (final Exception exception) {
//            return null;
//        }
//    }

    static String getDateFormatBack(final String strDate) {
        final String[] loopDate = strDate.split("/");
        return loopDate[2] + "-" + loopDate[1] + "-" + loopDate[0];
    }

    static String[] splitDateAndTime(final String date) {
        final StringTokenizer st = new StringTokenizer(date, " ");
        return new String[] { st.nextToken(), st.nextToken() };
    }

    static String[] splitDate(final String date) {
        final StringTokenizer st = new StringTokenizer(date, "-");
        return new String[] { st.nextToken(), st.nextToken(), st.nextToken() };
    }

    static String[] splitTime(final String time) {
        final StringTokenizer st = new StringTokenizer(time, ":");
        return new String[] { st.nextToken(), st.nextToken(), st.nextToken() };
    }

    static int getCurrentDay() {
        final Calendar currentDate = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT);
        final String dt[] = DateUtil.splitDateAndTime(formatter.format(currentDate.getTime()));
        final String ddmmyyyy[] = DateUtil.splitDate(dt[0]);
        return Integer.parseInt(ddmmyyyy[2]);
    }

    static int getCurrentMonth() {
        final Calendar currentDate = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT);
        final String dt[] = DateUtil.splitDateAndTime(formatter.format(currentDate.getTime()));
        final String ddmmyyyy[] = DateUtil.splitDate(dt[0]);
        return Integer.parseInt(ddmmyyyy[1]);
    }

    static String getCurrentMonthInString() {
        final Calendar cal = Calendar.getInstance();
        return month[cal.get(Calendar.MONTH)];
    }

    static String getMonthInString(final int mm) {
        String month = null;
        switch (mm) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sep";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
            default:
                break;
        }
        return month;
    }

    static String getFullMonthInString(final int mm) {
        String month = null;
        switch (mm) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                break;
        }
        return month;
    }

    static int getYearFromDate(final Date currentDate) {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(simpleDateformat.format(currentDate));
    }

    static int getMonthFromDate(final Date currentDate) {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return Integer.parseInt(simpleDateformat.format(currentDate));
    }

    static int getDayFromDate(final Date currentDate) {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd");
        return Integer.parseInt(simpleDateformat.format(currentDate));
    }

    static String getDateInDDMMYYYFormat(final Date currentDate) {
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(STANDARD_DATE_FORMAT_7);
        return simpleDateformat.format(currentDate);
    }

    static int getAge(final Date date) {
        final SimpleDateFormat fmt = new SimpleDateFormat(STANDARD_DATE_FORMAT_1);
        final String[] temp = DateUtil.splitDate(fmt.format(date));
        final Calendar calDOB = Calendar.getInstance();
        calDOB.set(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        final Calendar calNow = Calendar.getInstance();
        calNow.setTime(new Date());
        int ageYr = calNow.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR);
        final int ageMo = calNow.get(Calendar.MONTH) - calDOB.get(Calendar.MONTH);
        if (ageMo < 0)
            // adjust years by subtracting one
            ageYr--;
        return ageYr;
    }

    static int getCurrentMonthInNumeric() {
        final Calendar currentDate = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT);
        final String dt[] = DateUtil.splitDateAndTime(formatter.format(currentDate.getTime()));
        final String ddmmyyyy[] = DateUtil.splitDate(dt[0]);
        return Integer.parseInt(ddmmyyyy[1]);
    }

    static String getCurrentDateOnly() {
        final Calendar currentDate = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT);
        final String dt[] = DateUtil.splitDateAndTime(formatter.format(currentDate.getTime()));
        return dt[0];
    }

    static Date getNextDate(final int day, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        Date nextHourDate = cal.getTime();
        return nextHourDate;
    }

    static Date addMins(final int mins, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);
        Date nextHourDate = cal.getTime();
        return nextHourDate;
    }

    static Date getPrevtDate(final int day, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -day);
        Date prevHourDate = cal.getTime();
        return prevHourDate;
    }

    static Date getNextDate(final int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Date nextHourDate = cal.getTime();
        return nextHourDate;
    }

    static Date getNextTwoHourDate(final int nextDay) {
        final Date currnetDate = new Date();
        final Date nextHourDate = new Date();
        nextHourDate.setTime(currnetDate.getTime() + nextDay * 60 * 60 * 1000);
        return nextHourDate;
    }

    static String getDateReverse(final String cDate) {
        return cDate.substring(6, 10) + "-" + cDate.substring(3, 5) + "-" + cDate.substring(0, 2);
    }

    static boolean isSunday(final Date date) {
        boolean falg = false;
        final SimpleDateFormat f = new SimpleDateFormat("EEEE");
        falg = f.format(date).equals("Sunday");
        return falg;
    }

    static String getNextDateInMMMYYYYFormat(final int nextDay) {
        final Calendar nowCal = Calendar.getInstance();
        final int month = nowCal.get(Calendar.MONTH) + nextDay;
        final int year = nowCal.get(Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, nextDay);
        final Date dueDate = new Date(cal.getTimeInMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/yyyy");
        return dateFormat.format(dueDate);
    }

    static String getDateInString() {
        final Date dateNow = new Date();
        final SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        return dateformat.format(dateNow);
    }

    static String getDateTimeInString() {
        final Date dateNow = new Date();
        final SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHMMSS");
        return dateformat.format(dateNow);
    }

    static long getDaysBetweenTwoDate(final Calendar startDate, final Calendar endDate) {
        final Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    static long getDaysBetweenTwoDate(final Date startDate, final Date endDate) {
        return Math.round((endDate.getTime() - startDate.getTime()) / (double) (24 * 3600 * 1000));
    }

    static String getFormattedDateForUpload() {
        final Date dateNow = new Date();
        final SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateformat.format(dateNow);
    }

//    static DateDTO totalDifferenceBetweenTwoDate(final Date prevDate, final Date todayDate) {
//        final DateDTO dateDTO = DateDTO.builder().build();
//        final long diff = todayDate.getTime() - prevDate.getTime();
//        dateDTO.setDiff(diff);
//        final long diffSeconds = diff / 1000 % 60;
//        dateDTO.setDiffSeconds(diffSeconds);
//        final long diffMinutes = diff / (60 * 1000) % 60;
//        dateDTO.setDiffMinutes(diffMinutes);
//        final long diffHours = diff / (60 * 60 * 1000) % 24;
//        dateDTO.setDiffHours(diffHours);
//        final long diffDays = diff / (24 * 60 * 60 * 1000);
//        dateDTO.setDiffDays(diffDays);
//        return dateDTO;
//    }

    static long getTotalDifferenceBetweenTwoDate(final LocalDate prevDate, final LocalDate todayDate) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(prevDate, todayDate);
        return noOfDaysBetween;
    }

//    static Date changeDateFormatToDate(final String inputDate) {
//        final SimpleDateFormat inSDF = new SimpleDateFormat(STANDARD_DATE_FORMAT_3);
//        final SimpleDateFormat outSDF = new SimpleDateFormat(STANDARD_DATE_FORMAT_1);
//        if (StringUtils.isNotBlank(inputDate))
//            try {
//                final Date date = inSDF.parse(inputDate);
//                final String Date = outSDF.format(date);
//                return outSDF.parse(Date);
//            } catch (final Exception exception) {
//
//                return null;
//            }
//        return null;
//    }

    public static String getDateTimeFormatInStringMMddyyyy(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return dateformat.format(date);
    }

    static String formatStringToStringDate(String inputDateString, String from, String to) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat( from);
        Date date;
        try {
            date = inputFormatter.parse(inputDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        SimpleDateFormat outputFormatter = new SimpleDateFormat(to);
        String formattedDate = outputFormatter.format(date);
        return formattedDate;
    }

    public static String getDateFormatInStringMMddyyyy(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("MMMM dd-yyyy");
        return dateformat.format(date).replace("-", ",");
    }

    // get Mondays,Tuesdays,Wednesdays in 6 months
//    public static List<LocalDate> getDaysInMonTuesWed(YearMonth month) {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate firstMonday = currentDate.with(firstInMonth(DayOfWeek.MONDAY));
//        // LocalDate firstTuesday = currentDate.with(firstInMonth(DayOfWeek.TUESDAY));
//        // LocalDate firstWednesday =
//        // currentDate.with(firstInMonth(DayOfWeek.WEDNESDAY));
//        List<LocalDate> firstDaysOfWeeks = new ArrayList<>();
//        for (LocalDate day = currentDate.with(firstInMonth(DayOfWeek.MONDAY)); stillInCalendar(month,
//                day); day = day.plusWeeks(1)) {
//            firstDaysOfWeeks.add(day);
//        }
//        for (LocalDate day = firstMonday.with(DayOfWeek.TUESDAY); stillInCalendar(month, day); day = day
//                .plusWeeks(1)) {
//            firstDaysOfWeeks.add(day);
//        }
//        for (LocalDate day = firstMonday.with(DayOfWeek.WEDNESDAY); stillInCalendar(month, day); day = day
//                .plusWeeks(1)) {
//            firstDaysOfWeeks.add(day);
//        }
//        // for (LocalDate day = firstMonday.with(DayOfWeek.THURSDAY);
//        // stillInCalendar(month, day); day = day
//        // .plusWeeks(1)) {
//        // firstDaysOfWeeks.add(day);
//        // }
//        // for (LocalDate day = firstMonday.with(DayOfWeek.FRIDAY);
//        // stillInCalendar(month, day); day = day
//        // .plusWeeks(1)) {
//        // firstDaysOfWeeks.add(day);
//        // }
//
//        return firstDaysOfWeeks;
//    }

//    public static boolean stillInCalendar(YearMonth yearMonth, LocalDate day) {
//        return !day.isAfter(yearMonth.plusMonths(SeriConstant.TOTAL_MONTH.getactivityValue()).atEndOfMonth());
//    }

    public static String getDateFormatInStringMonthDateYear(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat(STANDARD_DATE_FORMAT_ONLY);
        return dateformat.format(date).replace("-", ",");
    }

    public static String getDateFormatInTime(Time time) {
        SimpleDateFormat dateformat = new SimpleDateFormat(STANDARD_TIME_FORMAT_4);
        return dateformat.format(time);
    }

    static String getDateInDayNameYearMonthDateTime(Date date) {
        if (date == null)
            return "";
        final SimpleDateFormat dateformat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z");
        return dateformat.format(date);
    }

    static String getInstallmentDigit(Integer position) {
        final String[] number = { "1<sup>st</sup>", "2<sup>nd</sup>", "3<sup>rd</sup>", "4<sup>th</sup>",
                "5<sup>th</sup>", "6<sup>th</sup>", "7<sup>th</sup>", "8<sup>th</sup>", "9<sup>th</sup>",
                "10<sup>th</sup>", "11<sup>th</sup>", "12<sup>th</sup>", "13<sup>th</sup>", "14<sup>th</sup>",
                "15<sup>th</sup>" };
        String result = number[position - 1];
        return result;
    }

    static String getMonthInInteger(final String mm) {
        String month = null;
        switch (mm) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                break;
        }
        return month;
    }

    static List<String> getTimeZoneList() {
        List<String> timeZones = new ArrayList<String>();
        timeZones.add("-14:00");
        timeZones.add("-13:30");
        timeZones.add("-13:00");
        timeZones.add("-12:30");
        timeZones.add("-12:00");
        timeZones.add("-11:30");
        timeZones.add("-11:00");
        timeZones.add("-10:30");
        timeZones.add("-10:00");
        timeZones.add("-09:30");
        timeZones.add("-09:00");
        timeZones.add("-08:30");
        timeZones.add("-08:00");
        timeZones.add("-07:30");
        timeZones.add("-07:00");
        timeZones.add("-06:30");
        timeZones.add("-06:00");
        timeZones.add("-05:30");
        timeZones.add("-05:00");
        timeZones.add("-04:30");
        timeZones.add("-04:00");
        timeZones.add("-03:30");
        timeZones.add("-03:00");
        timeZones.add("-02:30");
        timeZones.add("-02:00");
        timeZones.add("-01:30");
        timeZones.add("-01:00");
        timeZones.add("00:00");
        timeZones.add("01:00");
        timeZones.add("01:30");
        timeZones.add("02:00");
        timeZones.add("02:30");
        timeZones.add("03:00");
        timeZones.add("03:30");
        timeZones.add("04:00");
        timeZones.add("04:30");
        timeZones.add("05:00");
        timeZones.add("05:30");
        timeZones.add("06:00");
        timeZones.add("06:30");
        timeZones.add("07:00");
        timeZones.add("07:30");
        timeZones.add("08:00");
        timeZones.add("08:30");
        timeZones.add("09:00");
        timeZones.add("09:30");
        timeZones.add("10:00");
        timeZones.add("10:30");
        timeZones.add("11:00");
        timeZones.add("11:30");
        timeZones.add("12:00");
        timeZones.add("12:30");
        timeZones.add("13:00");
        timeZones.add("13:30");
        timeZones.add("14:00");
        return timeZones;
    }

//    static String getFinalTimeZone(String currentTimeZone, Integer offSet) {
//        String finalTimeZone = currentTimeZone;
//        if (!ValidatorUtil.isValid(offSet)) {
//
//        } else {
//            double offSetAsInt = ((offSet) / 3600) * 2;
//            int movePosition = (int) offSetAsInt;
//            int currentPostion = getTimeZoneList().indexOf(currentTimeZone);
//            int finalPosition = currentPostion + movePosition;
//            finalTimeZone = getTimeZoneList().get(finalPosition);
//        }
//        return finalTimeZone;
//    }

//    public static List<DateListDTO> getFirstLastOfMonthDates(Date startdate, Date enddate) {
//        List<DateListDTO> datesList = new ArrayList<>();
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startdate);
//        Date chooseLocalDate;
//        Date startLocalDate;
//        Date endLocalDate;
//        while (calendar.getTime().compareTo(enddate) <= 0) {
//            DateListDTO dateListDTO = new DateListDTO();
//            startLocalDate = calendar.getTime();
//            calendar.setTime(startLocalDate);
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//            if (calendar.getTime().before(enddate)) {
//                endLocalDate = calendar.getTime();
//                chooseLocalDate = calendar.getTime();
//            } else {
//                endLocalDate = calendar.getTime();
//                chooseLocalDate = enddate;
//            }
//            // endLocalDate = calendar.getTime();
//
//            calendar.setTime(endLocalDate);
//            calendar.add(Calendar.DATE, 1);
//
//            dateListDTO.setDate1(getDateInDesiredFormatAsString(startLocalDate, STANDARD_DATE_FORMAT_1));
//            dateListDTO.setDate2(getDateInDesiredFormatAsString(chooseLocalDate, STANDARD_DATE_FORMAT_1));
//            dateListDTO.setDate3(getDateInDesiredFormatAsString(endLocalDate, STANDARD_DATE_FORMAT_1));
//            datesList.add(dateListDTO);
//        }
//        return datesList;
//    }

    public static Date maximumDateAmongThreeAndTwoDates(Date firstDate, Date secondDate, Date thirdDate) {
        Date date = null;
        if (thirdDate == null) {
            if (secondDate == null) {
                date = firstDate;
            } else {
                if (firstDate.compareTo(secondDate) >= 0) {
                    date = firstDate;
                } else {
                    date = secondDate;
                }
            }
        } else {
            if (firstDate.compareTo(secondDate) >= 0 && firstDate.compareTo(thirdDate) >= 0) {
                date = firstDate;
            } else if (secondDate.compareTo(thirdDate) >= 0) {
                date = secondDate;
            } else {
                date = thirdDate;
            }
        }
        return date;
    }

    public static Date minimumDateAmongThreeAndTwoDates(Date firstDate, Date secondDate, Date thirdDate) {
        Date date = null;
        if (thirdDate == null) {
            if (firstDate.compareTo(secondDate) <= 0) {
                date = firstDate;
            } else {
                date = secondDate;
            }
        } else {
            if (firstDate.compareTo(secondDate) <= 0 && firstDate.compareTo(thirdDate) <= 0) {
                date = firstDate;
            } else if (secondDate.compareTo(thirdDate) <= 0) {
                date = secondDate;
            } else {
                date = thirdDate;
            }
        }
        return date;
    }

//    public static List<DateListDTO> getDaysBetweenDates(Date startdate, Date enddate) {
//        List<DateListDTO> datesList = new ArrayList<>();
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startdate);
//
//        while ((calendar.getTime().compareTo(enddate)) <= 0) {
//            DateListDTO datess = new DateListDTO();
//            Date result = calendar.getTime();
//            final int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//            datess.setDate1(getDateInDesiredFormatAsString(result, STANDARD_DATE_FORMAT_7));
//            datess.setDate2(getDateInDesiredFormatAsString(result, STANDARD_DATE_FORMAT_1));
//            calendar.add(Calendar.DATE, 1);
//            datess.setDayWeekVal(weekDay);
//            if (weekDay == 1) {
//                // sunday
//                datess.setDayWeek("Sunday");
//            } else if (weekDay == 2) {
//                // monday
//                datess.setDayWeek("Monday");
//            } else if (weekDay == 3) {
//                // tuesday
//                datess.setDayWeek("Tuesday");
//            } else if (weekDay == 4) {
//                // wedness day
//                datess.setDayWeek("Wednesday");
//            } else if (weekDay == 5) {
//                // thursday
//                datess.setDayWeek("Thursday");
//            } else if (weekDay == 6) {
//                // friday
//                datess.setDayWeek("Friday");
//            } else if (weekDay == 7) {
//                // satarday
//                datess.setDayWeek("Saturday");
//            }
//            datesList.add(datess);
//        }
//        return datesList;
//    }

    public static boolean checkWeekDayByDate(Date startdate, Integer weekDayId) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        int weekOfYear = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDayId == weekOfYear) {
            return true;
        }
        return false;
    }

//    public static boolean getWeekDayAvailableBetweenDates(Date startdate, Date enddate, Integer weekDayId) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startdate);
//
//        while (calendar.getTime().compareTo(enddate) <= 0) {
//            DateListDTO datess = new DateListDTO();
//            Date result = calendar.getTime();
//            final int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//            datess.setDate1(getDateInDesiredFormatAsString(result, STANDARD_DATE_FORMAT_7));
//            datess.setDate2(getDateInDesiredFormatAsString(result, STANDARD_DATE_FORMAT_1));
//            calendar.add(Calendar.DATE, 1);
//            datess.setDayWeekVal(weekDay);
//            if (weekDay == weekDayId) {
//                // sunday
//                return true;
//            } else if (weekDay == weekDayId) {
//                // monday
//                return true;
//            } else if (weekDay == weekDayId) {
//                // tuesday
//                return true;
//            } else if (weekDay == weekDayId) {
//                // wedness day
//                return true;
//            } else if (weekDay == weekDayId) {
//                // thursday
//                return true;
//            } else if (weekDay == weekDayId) {
//                // friday
//                return true;
//            } else if (weekDay == weekDayId) {
//                // satarday
//                return true;
//            }
//
//        }
//        return false;
//    }

    static String getDateInDayMonthYear(Date date) {
        if (date == null)
            return "";
        final SimpleDateFormat dateformat = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT_1);
        return dateformat.format(date);
    }

    static String getDateInDayNameYearMonthDateTimeNew(Date date) {
        if (date == null)
            return "";
        final SimpleDateFormat dateformat = new SimpleDateFormat("E dd MMM yyyy HH:mm:ss z");
        return dateformat.format(date);
    }

    static Date offsetTimeZone(Date date, String fromTZ, String toTZ) {

        // Construct FROM and TO TimeZone instances
        TimeZone fromTimeZone = TimeZone.getTimeZone(fromTZ);
        TimeZone toTimeZone = TimeZone.getTimeZone(toTZ);

        // Get a Calendar instance using the default time zone and locale.
        Calendar calendar = Calendar.getInstance();

        // Set the calendar's time with the given date
        calendar.setTimeZone(fromTimeZone);
        calendar.setTime(date);

        // FROM TimeZone to UTC
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);

        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        // UTC to TO TimeZone
        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());

        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }
        return calendar.getTime();
    }

    public static String getWelcomeTimeOfDay() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            return "Good morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            return "Good afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            return "Good evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            return "Good evening";
        }
        return "";
    }

    static Date getDateInDesiredFormatAsDate(final Date date, final String format) {
        try {
            if (date == null || format == null)
                return null;
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            final Date newDate = dateFormat.parse(getDateInDesiredFormatAsString(date, STANDARD_DATE_TIME_FORMAT));
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Date getNextDateAsDate(final Date date, final int day) {
        final Date nextHourDate = new Date();
        nextHourDate.setTime(date.getTime() + day * 24 * 60 * 60 * 1000);
        return nextHourDate;
    }

    static String convet24hrTo12hr(String _24HourTime) {
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat(STANDARD_TIME_FORMAT_4);
            SimpleDateFormat _12HourSDF = new SimpleDateFormat(STANDARD_TIME_FORMAT_1);
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            return _12HourSDF.format(_24HourDt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _24HourTime;
    }

    static String convertHrsMintSecondInHrsFormat(Double durationInSecond) {
        String plsic="";
        if(durationInSecond<0){
            durationInSecond=(-durationInSecond);
            plsic="- ";
        }
        if (durationInSecond > 0) {
            Double hours = durationInSecond / 3600;
            Double minutes = (durationInSecond % 3600) / 60;
            Double seconds = durationInSecond % 60;

            String hrsminsec=	String.format("%02d:%02d:%02d",hours.intValue() ,minutes.intValue(),seconds.intValue());
            return plsic+hrsminsec;
        } else {
            return "00:00:00";
        }

    }

    static String convertHrsMintSecond(Double durationInSecond) {
        if (durationInSecond > 0) {
            Double hours = durationInSecond / 3600;
            Double minutes = (durationInSecond % 3600) / 60;
            Double seconds = durationInSecond % 60;

            return hours.intValue() + "h " + minutes.intValue() + "m";
        } else {
            return "0h 0m";
        }

    }

    static double convertHrsMint(Integer durationInSecond) {
        double hoursmin = 0.0d;
        if (durationInSecond > 0) {
            int hours = durationInSecond / 3600;
            int minutes = (durationInSecond % 3600) / 60;
            hoursmin = Double.valueOf(hours + "." + minutes);
        }
        return hoursmin;

    }

//    static ConvertTimezoneResponse convertDateWithTimezone(String timezoneFrom, String timezoneTo, String dateToConvert,
//                                                           String dateFormat, String returnDateFormat, String returnTimeFormat) {
//        dateToConvert = dateToConvert.replaceAll(" ", "T");
//        if (timezoneFrom.equalsIgnoreCase(timezoneTo)) {
//            DateTimeZone dateTimeZoneFrom = DateTimeZone.forID(timezoneFrom);
//            DateTime dateTimeFrom = new DateTime(dateToConvert, dateTimeZoneFrom);
//            boolean isDst = !dateTimeZoneFrom.isStandardOffset(dateTimeFrom.getMillis());
//            ConvertTimezoneResponse response = new ConvertTimezoneResponse();
//                    response.setConvertedDate(dateTimeFrom.toDate());
//                    response.setDstEnabled(isDst);
//
//
//            response.setConvertedDateString(dateTimeFrom.toString(DateTimeFormat.forPattern(returnDateFormat)));
//            response.setConvertedTime(dateTimeFrom.toString(DateTimeFormat.forPattern(returnTimeFormat)));
//
//            // System.out.println(
//            // "timezoneFrom "+timezoneFrom
//            // +", timezoneTo: "+timezoneTo
//            // +", dateToConvert: "+dateToConvert
//            // +", response: "+response
//            // );
//            return response;
//        }
//        DateTimeZone dateTimeZoneFrom = DateTimeZone.forID(timezoneFrom);
//        DateTime dateTimeFrom = new DateTime(dateToConvert, dateTimeZoneFrom);
//        DateTimeZone dateTimeZoneTo = DateTimeZone.forID(timezoneTo);
//        DateTime dateTimeTo = dateTimeFrom.withZone(dateTimeZoneTo);
//
//        Date dateInTo = dateTimeTo.toLocalDateTime().toDate();
//
//        boolean isDst = !dateTimeZoneTo.isStandardOffset(dateTimeTo.getMillis());
//        ConvertTimezoneResponse response = new ConvertTimezoneResponse(dateInTo, isDst, dateTimeTo.toString(DateTimeFormat.forPattern(returnDateFormat)), dateTimeTo.toString(DateTimeFormat.forPattern(returnTimeFormat)));
//
//        return response;
//    }

    static Date convertDateWithTimezone(String timezoneFrom, String timezoneTo, Date dateToConvert,
                                                           String returnDateFormat, String returnTimeFormat) {
        if (timezoneFrom.equalsIgnoreCase(timezoneTo)) {
            DateTimeZone dateTimeZoneFrom = DateTimeZone.forID(timezoneFrom);
            DateTime dtFrom = new DateTime(dateToConvert);
            DateTime dateTimeFrom = dtFrom.withZone(dateTimeZoneFrom);
            return dateTimeFrom.toDate();
        }
        DateTimeZone dateTimeZoneFrom = DateTimeZone.forID(timezoneFrom);
        DateTime dtFrom = new DateTime(dateToConvert);
        DateTime dateTimeFrom = dtFrom.withZone(dateTimeZoneFrom);


        DateTimeZone dateTimeZoneTo = DateTimeZone.forID(timezoneTo);
        DateTime dateTimeTo = dateTimeFrom.withZone(dateTimeZoneTo);

        Date dateInTo = dateTimeTo.toLocalDateTime().toDate();

        return dateInTo;
    }

//    static ConvertTimezoneResponse convert(ConvertTimezoneRequest convertTimezoneRequest) {
//        // LocalDateTime localDateTimeFrom =
//        // LocalDateTime.of(convertTimezoneRequest.getYear(),
//        // convertTimezoneRequest.getMonth(), convertTimezoneRequest.getDate(),
//        // convertTimezoneRequest.getHours(),
//        // convertTimezoneRequest.getMinutes());
//        // Date fromDate = Date
//        // .from(localDateTimeFrom.atZone(ZoneId.of(convertTimezoneRequest.getTimezoneFrom())).toInstant());
//        String fromDate = convertTimezoneRequest.getYear()
//                + "-" + convertTimezoneRequest.getMonth()
//                + "-" + convertTimezoneRequest.getDate()
//                + " " + convertTimezoneRequest.getHours() + ":" + convertTimezoneRequest.getMinutes() + ":00";
//        return convertDateWithTimezone(convertTimezoneRequest.getTimezoneFrom(), convertTimezoneRequest.getTimezoneTo(),
//                fromDate, STANDARD_DATE_TIME_FORMAT, STANDARD_DATE_TIME_FORMAT_4, STANDARD_TIME_FORMAT_2);
//    }

    static Integer getDayId(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    static Date getFirstDateOfMonth(Date todate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(todate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    static Date getLastDateOfMonth(Date todate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(todate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Integer calculateDaysInDateDifference(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Integer.valueOf((int) ChronoUnit.DAYS.between(localDate1, localDate2));
    }

}