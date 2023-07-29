package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TImeAndDate {
    LocalDateTime getDateTime();

    public static void main(String[] args) {
        DateTimeFormatter formatter;
        LocalDateTime dateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy - hh:mm:ss a");
        System.out.println(dateTime.format(formatter));
    }

        public default void vendingMachineLog() throws FileNotFoundException {
            String filePath = "log.csv";
            File file = new File(filePath);
            PrintWriter vendingMachineLog = new PrintWriter(file);

        }


    }
