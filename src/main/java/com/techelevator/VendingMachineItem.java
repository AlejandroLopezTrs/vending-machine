package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class VendingMachineItem implements VendingMachineInventory {

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

    public void getItemTypeSelector(int numberSelection) {

    }


    public VendingMachineItem(String itemCode, String itemName, double itemPrice, String itemType, int itemQuantity) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.itemPrice = BigDecimal.valueOf(itemPrice);
        this.itemQuantity = itemQuantity;
    }



    @Override
    public String toString() {
        return "Item Code:" + itemCode + " Item: "+ itemName + " Price: $" + itemPrice
                + " Quantity: " + itemQuantity;
    }
    @Override
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    @Override
    public void addItem(String items) throws FileNotFoundException {

    }
    @Override
    public void addItem() throws FileNotFoundException {

    }
}


