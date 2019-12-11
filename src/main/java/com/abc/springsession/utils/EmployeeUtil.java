package com.abc.springsession.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeeUtil {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getDateTime(){
        return formatter.format(LocalDateTime.now());
    }
}
