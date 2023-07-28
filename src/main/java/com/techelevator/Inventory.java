package com.techelevator;

import java.math.BigDecimal;

public interface Inventory {
    void addItem(String items);

    public String getItemName();

    public int getItemQuantity();

    public String getItemCode();

    public String getItemType();

    public int setItemQuantity(int newQuantity);

    public BigDecimal getItemPrice();
}


