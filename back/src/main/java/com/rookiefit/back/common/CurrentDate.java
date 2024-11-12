package com.rookiefit.back.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CurrentDate {
    public static String currentDateString() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = today.format(dateFormatter);
        return formattedDate;
    }
}
