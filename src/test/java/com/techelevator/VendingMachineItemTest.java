package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineItemTest {

    private VendingMachineItem vendingMachineItem;

    @Before
    public void setup() {
        vendingMachineItem = new VendingMachineItem("A1,", "Ginger Ayle", 1.85, "Drink", 5);
    }

    // Test to ensure it returns the correct item quantity
    @Test
    public void TEST_GET_ITEM_QUANTITY() {
        int expectedQuantity = 5;
        int actualQuantity = vendingMachineItem.getItemQuantity();
        assertEquals(expectedQuantity, actualQuantity);
    }

    // Test to check if it returns expected string representation of VendingMachineItem
    @Test
    public void TEST_TO_STRING() {
        String expectedString = "Item Code:A1, Item: Ginger Ayle Price: $1.85 Quantity: 5";
        String actualString = vendingMachineItem.toString();
        assertEquals(expectedString, actualString);
    }

}