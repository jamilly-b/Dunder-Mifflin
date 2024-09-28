package com.DunderMifflin.Dunder_Mifflin.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatter {
    public static String formatDateWithTime(Calendar data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        return sdf.format(data.getTime());
    }
}
