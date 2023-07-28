package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

    List<Inventory> items = new ArrayList<>();
    private BigDecimal moneyUsed = BigDecimal.valueOf(0.00);
    private BigDecimal moneyAdded = BigDecimal.valueOf(0.00);
    private final BigDecimal ONE_DOLLAR = BigDecimal.valueOf(1.00);
    private final BigDecimal FIVE_DOLLAR = BigDecimal.valueOf(5.00);
    private final BigDecimal TEN_DOLLAR = BigDecimal.valueOf(10.00);
    private BigDecimal remainingBalance = BigDecimal.valueOf(0.00);
    private int itemQuantity = 5;



    public static void main(String[] args) throws FileNotFoundException {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();

    }

    public void run() throws FileNotFoundException {

        this.loadInventory();
        this.mainMenu();
        this.purchaseMenu();
    }

//TODO: main menu method
    public void mainMenu() {
        while (true) {
            System.out.println("(1) Display Vending Machine Items\n" +
                    "(2) Purchase\n" +
                    "(3) Exit\n");
            int numberSelection = this.getUserInput();

            if (numberSelection == 1) {
                for (Inventory item : items) {
                    System.out.println("Item: " + item.getItemName() + "     Quantity: " + item.getItemQuantity());
                }
            } else if (numberSelection == 2) {
                this.purchaseMenu();
            } else if (numberSelection == 3) {
                System.out.println("Thank you! Enjoy your snacks!");
                return;
            }
        }
    }

//TODO: load inventory method
    public void loadInventory() throws FileNotFoundException {
        String filePath = "main.csv";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\,");
            if (parts.length == 4) {
                String itemCode = parts[0];
                String itemName = parts[1];
                double itemPrice = Double.parseDouble(parts[2]);
                String itemType = parts[3];
                items.add(new Items(itemCode, itemName, itemPrice, itemType, itemQuantity));
            }

        }

    }

//TODO: purchase menu method
    public void purchaseMenu() {

        while (true) {
            System.out.println("Current Money Provided: " + moneyAdded + "\n");
            System.out.println("(1) Feed Money\n" +
                    "(2) Select Product\n" +
                    "(3) Finish Transaction\n");
            int numberSelection = this.getUserInput();
            if (numberSelection == 1) {
                this.moneyInAccount();
            } else if (numberSelection == 2) {
                this.selectProduct();
            } else if (numberSelection == 3) {
                this.finishTransaction();
                return;
            }
        }
    }

//TODO: get user input method
    public int getUserInput() {
        Scanner userInput = new Scanner(System.in);
        int numberSelection = Integer.parseInt(userInput.nextLine());
        return numberSelection;
    }

//TODO: money in account method
    public BigDecimal moneyInAccount() {
        while (true) {
            System.out.println("Current Money Provided: " + moneyAdded + "\n");
            System.out.println("How much would you like to add?\n" +
                    "(1) $1.00\n" + "(2) $5.00\n" + "(3) $10.00\n" + "(4) Back to purchase menu");
            int numberSelection = this.getUserInput();
            if (numberSelection == 1) {
                moneyAdded = moneyAdded.add(ONE_DOLLAR);
            } else if (numberSelection == 2) {
                moneyAdded = moneyAdded.add(FIVE_DOLLAR);
            } else if (numberSelection == 3) {
                moneyAdded = moneyAdded.add(TEN_DOLLAR);
            } else if (numberSelection == 4) {
                return moneyAdded;

            }
        }
    }

//TODO: select product method
    public void selectProduct() {
        while (true) {
            System.out.println("Which type of item would you like?\n" + "(1) Drink\n"
                    + "(2) Candy\n" + "(3) Munchy\n" + "(4) Gum");
            int numberSelection = this.getUserInput();
            if (numberSelection == 1) {
                List<Inventory> drinksList = items.subList(1,4);
                System.out.println(drinksList);
                this.itemPurchase();
            }   else if (numberSelection == 2) {
                    List<Inventory> candyList = items.subList(4,8);
                    System.out.println(candyList);
                this.itemPurchase();
            }   else if (numberSelection == 3) {
                        List<Inventory> munchyList = items.subList(8,12);
                        System.out.println(munchyList);
                this.itemPurchase();
            }   else if (numberSelection == 4) {
                            List<Inventory> gumList = items.subList(12,16);
                            System.out.println(gumList);
                this.itemPurchase();

            }

        }

    }

//TODO: purchasing item method
    private void itemPurchase(){

        for (Inventory item : items) {
            System.out.println("Item Code: " + item.getItemCode() + " Item: " + item.getItemName()+ "     Quantity: " + item.getItemQuantity());
            int userSelection = this.getUserInput();
            moneyUsed.add(item.getItemPrice());
            remainingBalance = moneyAdded.subtract(moneyUsed);
            this.itemQuantity = item.getItemQuantity() - 1;
            System.out.println(this.itemQuantity);
        }
    }
//TODO: finish transaction method
    private void finishTransaction() {
        System.out.println(remainingBalance);
    }
}
