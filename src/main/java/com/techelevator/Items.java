package com.techelevator;

import java.math.BigDecimal;

public class Items implements Inventory {

    private String itemName;
    private String itemCode;
    private String itemType;
    private BigDecimal itemPrice;
    private int itemQuantity;

    public int getItemQuantity() {
        return itemQuantity;
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


    public Items(String itemCode, String itemName, double itemPrice, String itemType, int itemQuantity) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.itemPrice = BigDecimal.valueOf(itemPrice);
        this.itemQuantity = itemQuantity;
    }

    @Override
    public void addItem(String items) {
    }

    @Override
    public String toString() {
        return "Item Code:" + itemCode + " Item: "+ itemName + " Price: $" + itemPrice
                + " Quanity: " + itemQuantity + "\n";
    }

}


