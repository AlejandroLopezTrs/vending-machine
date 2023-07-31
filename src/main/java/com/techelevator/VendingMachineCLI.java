package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.techelevator.VendingMachineBalance.*;
import static com.techelevator.VendingMachineLog.writeTransactionLog;
import static com.techelevator.VendingMachineLog.writeTransactionLogGiveChange;


public class VendingMachineCLI implements TImeAndDate, VendingMachineInventory, UserInputs {

    private VendingMachineBalance vendingMachineBalance = new VendingMachineBalance();
    private VendingMachineItemService itemService = new VendingMachineItemService(vendingMachineBalance);

    public static void main(String[] args) throws FileNotFoundException {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();

    }


    public void run() throws FileNotFoundException {

        this.itemService.loadInventory();
        this.vendingMachineLog();
        this.mainMenu();
        this.makeChange(vendingMachineBalance.getBalance());
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
                    for (VendingMachineInventory item : this.itemService.getItems()) {
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

    //TODO: purchase menu method
    public void purchaseMenu() {

        while (true) {
            try {
                System.out.println("Current Money Provided: " + vendingMachineBalance.getBalance() + "\n");
                System.out.println("(1) Feed Money\n" +
                        "(2) Select Product\n" +
                        "(3) Finish Transaction\n");
                int numberSelection = this.getUserInput();
                if (numberSelection == 1) {
                    this.moneyInAccount();
                } else if (numberSelection == 2) {
                    this.selectProductType();
                } else if (numberSelection == 3) {
                    this.finishTransaction(vendingMachineBalance.getBalance());
                    return;
                } else if (numberSelection < 1 || numberSelection > 3) {
                    System.out.println("Invalid selection: Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: Please enter a number and try again.");
            }
        }
    }

    //TODO: select product method2
    public void selectProductType() {
        while (true) {
            try {

                System.out.println("Which type of item would you like?\n" + "(1) Drink\n"
                        + "(2) Candy\n" + "(3) Munchy\n" + "(4) Gum\n" + "(5) Return to purchase menu");
                int numberSelection = this.getUserInput();
                List<VendingMachineInventory> selectedItems = this.itemService.itemTypeSelector(numberSelection);
                if (selectedItems == null){
                    return;
                }
                this.itemPurchase(selectedItems);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: Please enter a number and try again.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    //TODO: purchasing item method
    private void itemPurchase(List<VendingMachineInventory> selectedItems) {
        String userInputItemCode;
        VendingMachineInventory selectedItem;
        while (true) {
            for(VendingMachineInventory item : selectedItems){
                System.out.println(item);
            }
            System.out.println("Please enter the item code you would like to purchase.");
            userInputItemCode = this.getUserInputPurchase();
            selectedItem = null;
            for (VendingMachineInventory item : selectedItems) {
                if (item.getItemCode().equals(userInputItemCode)) {
                    selectedItem = item;
                    break;
                }
            }
            try{
                this.itemService.purchaseItem(selectedItem);
            } catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("\nWould you like to purchase another item from this category?\n" + "(1) Yes\n(2) No");
            int numberSelection = this.getUserInput();
            if (numberSelection != 1) {
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
                System.out.println("Current Money Provided: $ " + vendingMachineBalance.getBalance() + "\n");
                System.out.println("How much would you like to add?\n" +
                        "(1) $1.00\n" + "(2) $5.00\n" + "(3) $10.00\n" + "(4) Back to purchase menu");
                int numberSelection = this.getUserInput();
                if (numberSelection == 1) {
                    vendingMachineBalance.addToBalance(ONE_DOLLAR);

                    writeTransactionLog(ONE_DOLLAR, vendingMachineBalance.getBalance());

                } else if (numberSelection == 2) {
                    vendingMachineBalance.addToBalance(FIVE_DOLLAR);

                    writeTransactionLog(FIVE_DOLLAR, vendingMachineBalance.getBalance());

                } else if (numberSelection == 3) {
                    vendingMachineBalance.addToBalance(TEN_DOLLAR);
                    writeTransactionLog(TEN_DOLLAR, vendingMachineBalance.getBalance());

                } else if (numberSelection == 4) {
                    return vendingMachineBalance.getBalance();
                } else if
                (numberSelection < 1 || numberSelection > 4) {
                    System.out.println("Invalid number selection: Please enter a number between 1 and 4 and try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: Please enter a number and try again.");
            }
        }
    }


    private void makeChange(BigDecimal balance) {
        int nickelCount = 0;
        int dimeCount = 0;
        int quarterCount = 0;
        BigDecimal moneyLeft = balance;
        while (balance.compareTo(QUARTER) >= 0.00) {
            balance = balance.subtract(QUARTER);
            quarterCount++;
        }
        while (balance.compareTo(DIME) >= 0.00) {
            balance = balance.subtract(DIME);
            dimeCount++;
        }
        while (balance.compareTo(NICKEL) >= 0.00) {
            balance = balance.subtract(NICKEL);
            nickelCount++;
        }
        System.out.println("Change Dispensed: \n" + nickelCount + " Nickels\n" + dimeCount + " Dimes\n" + quarterCount + " Quarters\n");
        this.finishTransaction(balance);
        writeTransactionLogGiveChange(moneyLeft, balance);
    }


    //TODO: ITEM  combine methods


    //TODO: finish transaction method
    void finishTransaction(BigDecimal moneyAdded) {
        System.out.println("Remaining Balance: $" + moneyAdded);
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public void addItem(String items) throws FileNotFoundException {

    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public int getItemQuantity() {
        return 0;
    }

    @Override
    public void addItem() throws FileNotFoundException {

    }

    @Override
    public void setItemQuantity(int itemQuantity) {

    }

    @Override
    public String getItemCode() {
        return null;
    }

    @Override
    public String getItemType() {
        return null;
    }

    @Override
    public BigDecimal getItemPrice() {
        return null;
    }
    @Override
    public void getItemTypeSelector(int numberSelection) {
    }


}