package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineItem implements VendingMachineInventory {

    private final String itemName;
    private final String itemCode;
    private final String itemType;
    private final BigDecimal itemPrice;
    private int itemQuantity;


    public VendingMachineItem(String itemCode, String itemName, double itemPrice, String itemType, int itemQuantity) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.itemPrice = BigDecimal.valueOf(itemPrice);
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    @Override
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

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

    @Override
    public String toString() {
        return "Item Code:" + itemCode + " Item: " + itemName + " Price: $" + itemPrice
                + " Quantity: " + itemQuantity;
    }

    @Override
    public void addItem(String items) {

    }

    @Override
    public void addItem() {

    }
}


