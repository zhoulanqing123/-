package com.ruoyi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    //根据日期取得星期几
    public static String getWeek(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.SIMPLIFIED_CHINESE);

        String week = sdf.format(date);

        return week;

    }
}
