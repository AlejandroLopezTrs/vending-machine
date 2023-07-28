package com.techelevator;

import java.math.BigDecimal;

public class Transactions{


    private BigDecimal startingAmount = BigDecimal.valueOf(0.00);
    private final BigDecimal ONE_DOLLAR = BigDecimal.valueOf(1.00);
    private final BigDecimal FIVE_DOLLAR = BigDecimal.valueOf(5.00);
    private final BigDecimal TEN_DOLLAR = BigDecimal.valueOf(10.00);

    public BigDecimal getStartingAmount() {
        return startingAmount;
    }

    public BigDecimal getONE_DOLLAR() {
        return ONE_DOLLAR;
    }

    public BigDecimal getFIVE_DOLLAR() {
        return FIVE_DOLLAR;
    }

    public BigDecimal getTEN_DOLLAR() {
        return TEN_DOLLAR;
    }
//            System.out.println("Current Money Provided: $" + currentMoney);
//            System.out.println("(1)Feed Money\n" +
//                    "(2) Select Product\n" +
//                    "(3) Finish Transaction\n");
//            System.out.print("Please choose an option: ");
//            Scanner scanner = new Scanner(System.in);
//            String input = scanner.nextLine();
//            if (input.equals("1")) {
//                System.out.print("Please enter a valid dollar amount to feed into the machine: ");
//                double amount = scanner.nextDouble();
//                currentMoney += amount;
//            } else if (input.equals("2")) {
//                System.out.print("Please enter the slot location of the product you would like to purchase: ");
//                String slotLocation = scanner.nextLine();
//                Items selectedItem = null;
//                for (Inventory item : items) {
//                    if (item.getItemCode().equals(slotLocation)) {
//                       // selectedItem = item;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//}


}
