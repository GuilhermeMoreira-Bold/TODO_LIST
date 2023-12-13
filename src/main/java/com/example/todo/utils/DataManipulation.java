package com.example.todo.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Random;

public class DataManipulation {

    public static long getDataToday(){
        Random rand = new Random();
        return rand.nextInt(1,5) * System.currentTimeMillis();
    }
    public static String getDataInString(long date){
        Date stringDate = Date.from(Instant.ofEpochMilli(date));
        SimpleDateFormat fomart = new SimpleDateFormat("dd/MM/yyy - HH:mm");
        return fomart.format(stringDate);
    }

}
