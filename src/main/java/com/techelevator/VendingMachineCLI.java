package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

    List<Inventory> items = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();

    }

    public void run() throws FileNotFoundException {

        System.out.println("(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n");

        Scanner userInput = new Scanner(System.in);
        int numberSelection = Integer.parseInt(userInput.nextLine());

        File mainItems = new File("main.csv");

        try (Scanner filepath = new Scanner(mainItems)) {
            boolean isOneSelected = false;
            if (!isOneSelected) {
                System.out.println(items);
            }
        }


    }

}
