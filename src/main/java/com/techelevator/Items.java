package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Items implements Inventory {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public List<Items> getItems() {
        return items;
    }

    private String itemCode;
    private String itemType;
    private BigDecimal itemPrice;

    public Items(String itemCode, String itemName, double itemPrice, String itemType) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.itemPrice = BigDecimal.valueOf(itemPrice);
    }

    private List<Items> items = getItems();

    public Items() throws FileNotFoundException {
        String filePath = "main.csv";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                String itemCode = parts[0];
                String itemName = parts[1];
                double itemPrice = Double.parseDouble(parts[2]);
                String itemType = parts[3];

                items.add(new Items(itemCode, itemName, itemPrice, itemType));
            }

        }

    }

    @Override
    public void addItem(String items) {

    }

}


