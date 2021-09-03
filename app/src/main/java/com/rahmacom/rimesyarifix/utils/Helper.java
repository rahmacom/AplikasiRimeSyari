package com.rahmacom.rimesyarifix.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static List<Integer> convertToList(int[] array) {
        Integer[] ints = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            ints[i] = array[i];
        }

        List<Integer> integers = Arrays.asList(ints);

        return integers;
    }

    public static int[] convertToIntArray(List<Integer> collection) {
        int[] intArray = new int[collection.size()];

        for (int i = 0; i < collection.size(); i++) {
            intArray[i] = collection.get(i);
        }

        return intArray;
    }
}
