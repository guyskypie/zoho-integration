package outcastfoods.integration.zoho.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateUtils {


    /**
     * @param dateIn yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getDateOneDayBefore(String dateIn) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.US);

        LocalDate localDate = format.parse(dateIn).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        localDate = localDate.minusDays(1);

        String dateBeforeInclusive = format.format(Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        return dateBeforeInclusive;

    }

    /**
     *
     * @param dateIn yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getDateOneDayAfter(String dateIn) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.US);

        LocalDate localDate = format.parse(dateIn).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        localDate = localDate.plusDays(1);

        String dateAfterInclusive = format.format(Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        return dateAfterInclusive;

    }

    /**
     * @return
     * @throws ParseException
     */
    public static String getDateOneDayAfterCurrentDate() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.US);

        LocalDate localDate = LocalDate.now();

        localDate = localDate.plusDays(1);

        String datePlusOneDay = format.format(Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        return datePlusOneDay;

    }


    /**
     * @return
     * @throws ParseException
     */
    public static String getTodaysDate() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.US);

        LocalDate localDate = LocalDate.now();

        String datePlusOneDay = format.format(Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        return datePlusOneDay;

    }
}
