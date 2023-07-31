package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public interface VendingMachineInventory {
    void addItem(String items) throws FileNotFoundException;
    public String getItemName();
    public int getItemQuantity();

    void addItem() throws FileNotFoundException;

    public void setItemQuantity(int itemQuantity);
    public String getItemCode();
    public String getItemType();

    public BigDecimal getItemPrice();
    default void selectProductType(){
    }
    String toString();

    void getItemTypeSelector(int numberSelection);
}


