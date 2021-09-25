package com.rahmacom.rimesyarifix.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
        Integer[] integers = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            integers[i] = array[i];
        }

        return Arrays.asList(integers);
    }

    public static int[] convertToIntArray(List<Integer> collection) {
        int[] intArray = new int[collection.size()];

        for (int i = 0; i < collection.size(); i++) {
            intArray[i] = collection.get(i);
        }

        return intArray;
    }

    public static String toDate(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date parse = null;

        try {
            parse =  format.parse(timestamp);
            format.applyPattern("E, dd-MM-yyyy");
            return format.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File bitmapToFile(Bitmap bmp, Activity activity) {
        ContextWrapper wrapper = new ContextWrapper(activity);

        File file = getOutputDir(activity);
        file = new File(file, UUID.randomUUID() + ".jpg");

        try {
            OutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static File getOutputDir(Activity activity) {
        File mediaDir = null;

        for (File dir : activity.getExternalMediaDirs()) {
            if (dir != null && dir.exists()) {
                mediaDir = dir;
                break;
            } else {
                mediaDir = activity.getFilesDir();
            }
        }

        return mediaDir;
    }

    public static String getContentFromUri(Uri uri, Context context) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor.moveToFirst()) {
            int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }

        return result;
    }
}
