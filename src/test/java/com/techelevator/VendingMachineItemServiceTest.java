package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineItemServiceTest {

    private VendingMachineItemService vendingMachineItemService;

    @Before
    public void setup() {
        vendingMachineItemService = new VendingMachineItemService(new VendingMachineBalance());

    }

    // Test to see if loadInventory method correctly loads items into the items list
    @Test
    public void TEST_LOAD_INVENTORY() throws FileNotFoundException {
        vendingMachineItemService.loadInventory();
        List<VendingMachineInventory> items = vendingMachineItemService.getItems();
        assertEquals(16, items.size());
    }

}