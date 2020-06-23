package com.coordinator.core.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralHelper {
    public static String FirstOrDefault(String firstChoice, String secondChoice) {
        return firstChoice == null ? secondChoice : firstChoice;
    }

    public static Integer FirstOrDefault(Integer firstChoice, Integer secondChoice) {
        return firstChoice == null ? secondChoice : firstChoice;
    }

    public static Date CastStringToDate(String stringDate) throws ClassCastException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(stringDate);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch(Exception e) {
            throw new ClassCastException(String.format("Unable to convert string %s to date", stringDate));
        }
    }
}
