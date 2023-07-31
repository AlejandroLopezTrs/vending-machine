package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineBalance {
    public static final BigDecimal ONE_DOLLAR = BigDecimal.valueOf(1.00).setScale(2);
    public static final BigDecimal FIVE_DOLLAR = BigDecimal.valueOf(5.00).setScale(2);
    public static final BigDecimal TEN_DOLLAR = BigDecimal.valueOf(10.00).setScale(2);
    public static final BigDecimal NICKEL = BigDecimal.valueOf(0.05);
    public static final BigDecimal DIME = BigDecimal.valueOf(0.10);
    public static final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    private BigDecimal balance = BigDecimal.valueOf(0.00);
    private final BigDecimal remainingBalance = BigDecimal.valueOf(0.00).setScale(2);
public BigDecimal addToBalance(BigDecimal amount){
    this.balance = this.balance.add(amount);
    return this.balance;
}
    public BigDecimal subtractFromBalance(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
