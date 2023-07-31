package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static com.techelevator.VendingMachineLog.writeTransactionLogProductPurchase;

public class VendingMachineItemService {
    private List<VendingMachineInventory> items = new ArrayList<>();
    private Map<String, String> itemTypeMessage = new HashMap<>();
    private VendingMachineBalance vendingMachineBalance;
    private int numberOfItemsPurchased = 0;

    public VendingMachineItemService(VendingMachineBalance vendingMachineBalance){
        this.vendingMachineBalance = vendingMachineBalance;
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
                items.add(new VendingMachineItem(itemCode, itemName, itemPrice, itemType, 5));
            }

        }
        itemTypeMessage.put("Drink", "Glug Glug, Yum!");
        itemTypeMessage.put("Candy", "Yummy Yummy, So Sweet!");
        itemTypeMessage.put("Munchy", "Crunch Crunch, Yum!");
        itemTypeMessage.put("Gum", "Chew Chew, Yum!");
    }

    public void purchaseItem(VendingMachineInventory selectedItem) throws Exception {
        if (selectedItem == null) {
            throw new Exception("Invalid Item Code: Please try again.");
        }
        if (selectedItem.getItemQuantity() <= 0) {
            throw new Exception("Out of Stock");
        }
        BigDecimal itemPriceAdjustment = this.adjustPrice();
        BigDecimal itemPrice = selectedItem.getItemPrice().add(itemPriceAdjustment);
        BigDecimal remainingBalance = this.vendingMachineBalance.getBalance().subtract(itemPrice);


        if (remainingBalance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Insufficient Funds. Please return to purchase menu to add more funds.");
        }
        selectedItem.setItemQuantity(selectedItem.getItemQuantity() - 1);
        this.numberOfItemsPurchased++;
        String itemCategory = selectedItem.getItemType();
        String message = itemTypeMessage.get(itemCategory);
        String itemName = selectedItem.getItemName();
        remainingBalance = this.vendingMachineBalance.subtractFromBalance(itemPrice);
       writeTransactionLogProductPurchase(itemName, selectedItem.getItemCode(), itemPrice, remainingBalance);
        System.out.println("Item Name: " + itemName + " | Item Cost: $" + itemPrice + " | Remaining Balance: $ " + remainingBalance + " | Message: " + message);

    }
    public List<VendingMachineInventory> itemTypeSelector(int numberSelection) throws Exception {
        if (numberSelection == 1) {
           return items.subList(0, 4);

        } else if (numberSelection == 2) {
            return items.subList(4, 8);

        } else if (numberSelection == 3) {
          return items.subList(8, 12);

        } else if (numberSelection == 4) {
            return items.subList(12, 16);
        } else if (numberSelection == 5) {
            return null;
        }
        throw new Exception("Invalid number selection: Please enter a number between 1 and 5 and try again.");
    }
    private BigDecimal adjustPrice() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Month currentMonth = currentDateTime.getMonth();
        if (currentMonth == Month.JULY && getNumberOfItemsPurchased() % 2 == 1) {
            return BigDecimal.valueOf(-1);
        }
        return BigDecimal.ZERO;
    }
    public List<VendingMachineInventory> getItems() {
        return items;
    }

    public int getNumberOfItemsPurchased() {
        return numberOfItemsPurchased;
    }
}