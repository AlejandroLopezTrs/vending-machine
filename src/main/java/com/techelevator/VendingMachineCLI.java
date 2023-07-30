package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VendingMachineCLI implements TImeAndDate {

    List<Inventory> items = new ArrayList<>();
    private BigDecimal moneyUsed = BigDecimal.valueOf(0.00);
    private BigDecimal moneyAdded = BigDecimal.valueOf(0.00);
    private final BigDecimal ONE_DOLLAR = BigDecimal.valueOf(1.00).setScale(2);
    private final BigDecimal FIVE_DOLLAR = BigDecimal.valueOf(5.00).setScale(2);
    private final BigDecimal TEN_DOLLAR = BigDecimal.valueOf(10.00).setScale(2);
    private BigDecimal remainingBalance = BigDecimal.valueOf(0.00).setScale(2);
    private int itemQuantity = 5;

    public BigDecimal getMoneyUsed() {
        return moneyUsed;
    }

    public BigDecimal getMoneyAdded() {
        return moneyAdded;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public static void main(String[] args) throws FileNotFoundException {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();

    }

    public void run() throws FileNotFoundException {

        this.loadInventory();
        this.vendingMachineLog();
        this.mainMenu();
        this.makeChange(moneyAdded);
    }

    //TODO: main menu method
    public void mainMenu() {
        try {
            while (true) {
                System.out.println("\n(1) Display Vending Machine Items\n" +
                        "(2) Purchase\n" +
                        "(3) Exit\n");
                int numberSelection = this.getUserInput();

                if (numberSelection == 1) {
                    for (Inventory item : items) {
                        System.out.println("Item: " + item.getItemName() + " | Quantity: " + item.getItemQuantity());
                    }
                } else if (numberSelection == 2) {
                    this.purchaseMenu();
                } else if (numberSelection == 3) {
                    break;
                } else if (numberSelection < 1 || numberSelection > 3) {
                    System.out.println("Invalid number selection: Please enter a number between 1 and 3 and try again.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid selection: Please enter a number and try again.");
        }
    }

    //TODO: print all successful transactions
    private void writeTransactionLog(BigDecimal amountDepositedSpent, BigDecimal newBalance) {
        try {
            String filePath = "Log.txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDateTime = now.format(formatter);
            printWriter.printf("\n" + formattedDateTime +  " FEED MONEY: $" + amountDepositedSpent + " | $" + newBalance);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to transaction log file: " + e.getMessage());
        }
    }
    private void writeTransactionLogProductPurchase(String itemName, String itemCode, BigDecimal amountDepositedSpent, BigDecimal newBalance) {
        try {
            String filePath = "Log.txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDateTime = now.format(formatter);
            printWriter.printf("\n" + formattedDateTime +  "  PURCHASE: " + itemCode + " | " + itemName + " | $" + amountDepositedSpent + " | $" + newBalance);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to transaction log file: " + e.getMessage());
        }
    }
    private void writeTransactionLogGiveChange(BigDecimal amountDepositedSpent, BigDecimal newBalance) {
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
            try {
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
                    this.finishTransaction(moneyAdded);
                    return;
                } else if (numberSelection < 1 || numberSelection > 3 ) {
                    System.out.println("Invalid selection: Please enter a number between 1 and 3.");                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: Please enter a number and try again.");
            }
        }
    }
    //TODO: get user input method
    public int getUserInput() {
        Scanner userInput = new Scanner(System.in);
        int numberSelection = Integer.parseInt(userInput.nextLine());
        return numberSelection;
    }

    //TODO: get user input method for purchase selection
    public String getUserInputPurchase() {
        Scanner userInput = new Scanner(System.in);
        String userInputItemCode = (userInput.nextLine());
        return userInputItemCode;
    }


    //TODO: money in account method
    public BigDecimal moneyInAccount() {
        while (true) {
            try {
                System.out.println("Current Money Provided: $ " + moneyAdded + "\n");
                System.out.println("How much would you like to add?\n" +
                        "(1) $1.00\n" + "(2) $5.00\n" + "(3) $10.00\n" + "(4) Back to purchase menu");
                int numberSelection = this.getUserInput();
                if (numberSelection == 1) {
                    moneyAdded = moneyAdded.add(ONE_DOLLAR);

                    writeTransactionLog(ONE_DOLLAR , moneyAdded);

                } else if (numberSelection == 2) {
                    moneyAdded = moneyAdded.add(FIVE_DOLLAR);

                    writeTransactionLog(FIVE_DOLLAR , moneyAdded);

                } else if (numberSelection == 3) {
                    moneyAdded = moneyAdded.add(TEN_DOLLAR);
                    writeTransactionLog(TEN_DOLLAR , moneyAdded);

                } else if (numberSelection == 4) {
                    return moneyAdded;
                } else if
                (numberSelection < 1 || numberSelection > 4) {
                    System.out.println("Invalid number selection: Please enter a number between 1 and 4 and try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid selection: Please enter a number and try again.");
            }
        }
            }



    //TODO: select product method2
    public void selectProduct() {
        while (true) {
            try {

                System.out.println("Which type of item would you like?\n" + "(1) Drink\n"
                        + "(2) Candy\n" + "(3) Munchy\n" + "(4) Gum\n" + "(5) Return to purchase menu");
                int numberSelection = this.getUserInput();
                if (numberSelection == 1) {
                    List<Inventory> drinksList = items.subList(0, 4);
                    System.out.println(drinksList);
                    this.itemPurchase();
                } else if (numberSelection == 2) {
                    List<Inventory> candyList = items.subList(4, 8);
                    System.out.println(candyList);
                    this.itemPurchase();
                } else if (numberSelection == 3) {
                    List<Inventory> munchyList = items.subList(8, 12);
                    System.out.println(munchyList);
                    this.itemPurchase();
                } else if (numberSelection == 4) {
                    List<Inventory> gumList = items.subList(12, 16);
                    System.out.println(gumList);
                    this.itemPurchase();
                } else if (numberSelection == 5) {
                    return;
                } else if (numberSelection >= 0 || numberSelection > 5) {
                    System.out.println("Invalid number selection: Please enter a number between 1 and 5 and try again.");
                }

            } catch (NumberFormatException e){
                System.out.println("Invalid selection: Please enter a number and try again.");
        }
        }

    }


    //TODO: purchasing item method
    private void itemPurchase() {

        Map<String, String> itemTypeMessage = new HashMap<>();
        itemTypeMessage.put("Drink", "Glug Glug, Yum!");
        itemTypeMessage.put("Candy", "Yummy Yummy, So Sweet!");
        itemTypeMessage.put("Munchy", "Crunch Crunch, Yum!");
        itemTypeMessage.put("Gum", "Chew Chew, Yum!");


        Inventory selectedItem;
        String userInputItemCode;
        int purchaseItemCount = 0;
        while (true) {
            System.out.println("Please enter the item code you would like to purchase.");
            userInputItemCode = this.getUserInputPurchase();
            selectedItem = null;
            for (Inventory item : items) {
                if (item.getItemCode().equals(userInputItemCode)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem == null) {
                System.out.println("Invalid Item Code: Please try again.");
                continue;
            }
            if(selectedItem.getItemQuantity() <= 0){
                System.out.println("Out of Stock");
                continue;
            }
            BigDecimal itemPriceAdjustment = this.adjustPrice(purchaseItemCount);
            BigDecimal itemPrice = selectedItem.getItemPrice().add(itemPriceAdjustment);
            BigDecimal remainingBalance = moneyAdded.subtract(itemPrice);


            if (remainingBalance.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Insufficient Funds. Please return to purchase menu to add more funds.");
                return;
            }
            purchaseItemCount++;
            selectedItem.setItemQuantity(selectedItem.getItemQuantity() - 1);
            String itemCategory = selectedItem.getItemType();
            String message = itemTypeMessage.get(itemCategory);
            String itemName = selectedItem.getItemName();
            writeTransactionLogProductPurchase(itemName, userInputItemCode, itemPrice, remainingBalance);

            System.out.println("Item Name: " + itemName + " | Item Cost: $" + itemPrice + " | Remaining Balance: $ " + remainingBalance + " | Message: " + message);
            moneyAdded = remainingBalance;
            System.out.println("\nWould you like to purchase another item from this category?\n" + "(1) Yes\n(2) No");
            int numberSelection = this.getUserInput();
            if (numberSelection != 1) {
                return;
            }

        }

    }

    //TODO: ITEM  combine methods
    private BigDecimal adjustPrice(int purchaseItemCount) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Month currentMonth = currentDateTime.getMonth();
        if (currentMonth == Month.JULY && purchaseItemCount % 2 == 1) {
            return BigDecimal.valueOf(-1);
        }
        return BigDecimal.ZERO;
    }

//TODO: makeChange class
    private final BigDecimal NICKEL = BigDecimal.valueOf(0.05);
    private final BigDecimal DIME = BigDecimal.valueOf(0.10);
    private final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    private BigDecimal makeChange(BigDecimal moneyAdded) {
        int nickelCount = 0;
        int dimeCount = 0;
        int quarterCount = 0;
        BigDecimal moneyLeft = moneyAdded;
        while (true) {
            while (moneyAdded.compareTo(QUARTER) >= 0.00) {
                moneyAdded = moneyAdded.subtract(QUARTER);
                quarterCount++;
            } while
            (moneyAdded.compareTo(DIME) >= 0.00) {
                moneyAdded = moneyAdded.subtract(DIME);
                dimeCount++;
            } while
            (moneyAdded.compareTo(NICKEL) >= 0.00) {
                moneyAdded = moneyAdded.subtract(NICKEL);
                nickelCount++;
            }
            System.out.println("Change Dispensed: \n" + nickelCount + " Nickels\n" + dimeCount + " Dimes\n" + quarterCount + " Quarters\n");
            this.finishTransaction(moneyAdded);
            writeTransactionLogGiveChange(moneyLeft , moneyAdded);

            break;
        }
        return moneyAdded;
    }

    //TODO: finish transaction method
    private void finishTransaction(BigDecimal moneyAdded) {
        System.out.println("Remaining Balance: $" + moneyAdded);
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}