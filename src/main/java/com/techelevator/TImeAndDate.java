package com.techelevator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface TImeAndDate {
    public static void main(String[] args) {
        /*
         * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html
         */
        DateTimeFormatter formatter;

        /*
         * Get the current date
         */
        LocalDate date = LocalDate.now();
        System.out.println("The current date is ................: " + date);

        System.out.println("The current date ISO FORMATTED is ..: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        System.out.println("The current date CUSTOM FORMATTED is: " + date.format(formatter));

        System.out.println();

        /*
         * Get the current time
         */
        LocalTime time = LocalTime.now();
        System.out.println("The current time is ................: " + time);

        System.out.println("The current time ISO FORMATTED is ..: " + time.format(DateTimeFormatter.ISO_LOCAL_TIME));

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("The current time CUSTOM FORMATTED is: " + time.format(formatter));

        System.out.println();

        /*
         * Get the current date AND time
         */
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("The current date AND time is ................: " + dateTime);

        System.out.println("The current date AND time ISO FORMATTED is ..: " + dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        formatter = DateTimeFormatter.ofPattern("hh:mm:ss a, MMMM dd yyyy ");
        System.out.println("The current date AND time CUSTOM FORMATTED is: " + dateTime.format(formatter));
    }
}
