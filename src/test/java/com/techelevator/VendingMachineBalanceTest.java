package com.techelevator;

import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import static org.junit.Assert.*;

public class VendingMachineBalanceTest {

    private VendingMachineBalance vendingMachineBalance;

    @Before
    public void setup() {
        vendingMachineBalance = new VendingMachineBalance();
    }

    //Tests the getBalance method to verify if the initial balance is 0.00 and whether the balance is correctly updated after adding an amount.
    @Test
    public void TEST_GET_BALANCE() {
        BigDecimal initialBalance = vendingMachineBalance.getBalance();
        assertEquals(BigDecimal.valueOf(0.00), initialBalance);

        vendingMachineBalance.addToBalance(BigDecimal.valueOf(5.00));
        BigDecimal updatedBalance = vendingMachineBalance.getBalance();
        assertEquals(BigDecimal.valueOf(5.00), updatedBalance);
    }

}