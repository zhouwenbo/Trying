package com.fheebiy.trying.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lenovo on 15-2-6.
 */
public class CommTest {


    public static void main(String[] args) {
        String str = getDateStr("2018-09-14 17:46:25");
        System.out.print(str);

    }


    public static String getDateStr(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
