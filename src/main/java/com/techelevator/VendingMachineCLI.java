package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


public class VendingMachineCLI implements TImeAndDate {

    List<Inventory> items = new ArrayList<>();
    private BigDecimal moneyUsed = BigDecimal.valueOf(0.00);
    private BigDecimal moneyAdded = BigDecimal.valueOf(0.00);
    private final BigDecimal ONE_DOLLAR = BigDecimal.valueOf(1.00);
    private final BigDecimal FIVE_DOLLAR = BigDecimal.valueOf(5.00);
    private final BigDecimal TEN_DOLLAR = BigDecimal.valueOf(10.00);
    private BigDecimal remainingBalance = BigDecimal.valueOf(0.00);
    private int itemQuantity = 5;
    private int purchaseItemCount = 0;


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
                System.out.println("Thank you! Enjoy your snacks!");
                break;
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

    //TODO: get user input method for purchse selection
    public String getUserInputPurchase() {
        Scanner userInput = new Scanner(System.in);
        String userInputItemCode = (userInput.nextLine());
        return userInputItemCode;
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

    //TODO: select product method2
    public void selectProduct() {
        while (true) {
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
                System.out.println("Invalid Item Code. Please try again.");
                continue;
            }
            BigDecimal itemPrice = selectedItem.getItemPrice();
            BigDecimal remainingBalance = moneyAdded.subtract(itemPrice);
            if (remainingBalance.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Insufficient Funds. Please return to purchase menu to add more funds.");
                return;
            }
            int purchaseQuantity = selectedItem.getItemQuantity();
            if (purchaseQuantity == 0) {
                System.out.println("Item is SOLD OUT! Please enter another item code!");
                continue;
            }

            BigDecimal discountPrice = itemPrice;
            if (purchaseItemCount % 2 == 1) {
                discountPrice = itemPrice.subtract(BigDecimal.ONE);
            }
            String itemCategory = selectedItem.getItemType();
            String message = itemTypeMessage.get(itemCategory);
            String itemName = selectedItem.getItemName();
            System.out.println("Item Name: " + itemName + " | Item Cost: $" + itemPrice + " | Remaining Balance: $ " + remainingBalance + " | Message: " + message);
            moneyAdded = remainingBalance.subtract(discountPrice);
            purchaseItemCount++;
            selectedItem.setItemQuantity(purchaseQuantity - 1);

            System.out.println("\nWould you like to purchase another item from this category?\n" + "(1) Yes\n(2) No");
            int numberSelection = this.getUserInput();
            if (numberSelection != 1) {
                return;
            }
        }
    }

    // TODO: BOGODO AUGUST CHECK
    public boolean isJuly(TImeAndDate timeAndDate) {

        LocalDateTime currentDateTime = timeAndDate.getDateTime();
        Month currentMonth = currentDateTime.getMonth();

        return currentMonth == Month.JULY;
    }


    //TODO: ITEM PRICE
    public BigDecimal itemPrice(String userInputItemCode) {
        for (Inventory item : items) {
            if (item.getItemCode().equals(userInputItemCode)) {
                return item.getItemPrice();
            }
        }
        return BigDecimal.ZERO;
    }
    //TODO: ITEM  combine methods
    private BigDecimal getItemPrice(String userInputItemCode, int purchaseItemCount) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Month currentMonth = currentDateTime.getMonth();
        BigDecimal itemPrice = BigDecimal.ZERO;
        if (purchaseItemCount % 2 == 0 && currentMonth == Month.JULY) {
            itemPrice.subtract(BigDecimal.ONE);
        }
        for (Inventory item : items) {
            if (item.getItemCode().equals(userInputItemCode)) {
                return itemPrice;
            }
        }
        return itemPrice;
    }

    //TODO: finish transaction method
    private void finishTransaction() {
        System.out.println(remainingBalance);

    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}