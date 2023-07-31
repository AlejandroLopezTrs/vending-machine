package com.techelevator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingMachineLog {

    public static void writeTransactionLog(BigDecimal amountDepositedSpent, BigDecimal newBalance) {
        try {
            String filePath = "Log.txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDateTime = now.format(formatter);
            printWriter.printf("\n" + formattedDateTime + " FEED MONEY: $" + amountDepositedSpent + " | $" + newBalance);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to transaction log file: " + e.getMessage());
        }
    }

    public static void writeTransactionLogProductPurchase(String itemName, String itemCode, BigDecimal amountDepositedSpent, BigDecimal newBalance) {
        try {
            String filePath = "Log.txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDateTime = now.format(formatter);
            printWriter.printf("\n" + formattedDateTime + "  PURCHASE: " + itemCode + " | " + itemName + " | $" + amountDepositedSpent + " | $" + newBalance);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to transaction log file: " + e.getMessage());
        }
    }

   public static void writeTransactionLogGiveChange(BigDecimal amountDepositedSpent, BigDecimal newBalance) {
        try {
            String filePath = "Log.txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDateTime = now.format(formatter);
            printWriter.printf("\n" + formattedDateTime + " GIVE CHANGE: $" + amountDepositedSpent + " | $" + newBalance);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to transaction log file: " + e.getMessage());
        }
    }
}
