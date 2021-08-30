package com.rahmacom.rimesyarifix.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Helper {
    public static String convertToRP(long harga) {

        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(new Locale("id", "ID"));
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();

        symbols.setGroupingSeparator('.');
        format.setDecimalFormatSymbols(symbols);

        return "Rp " + format.format(harga);
    }

    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return timestamp.getTime();
    }
}
