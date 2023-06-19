package by.itclass.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String OUTPUT_PATTERN = "dd.MM.yyyy HH:mm";
    public static String format(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
