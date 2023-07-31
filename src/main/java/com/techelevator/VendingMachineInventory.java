package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public interface VendingMachineInventory {
    void addItem(String items) throws FileNotFoundException;

    String getItemName();

    int getItemQuantity();

    void setItemQuantity(int itemQuantity);

    void addItem() throws FileNotFoundException;

    String getItemCode();

    String getItemType();

    BigDecimal getItemPrice();

    default void selectProductType() {
    }

    String toString();

}


