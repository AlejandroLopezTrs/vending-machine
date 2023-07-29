package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalReferences {
    public static void main(String[] args) {

        /*
         * Creating BigDecimal values
         */
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal one = BigDecimal.ONE;

        // DO NOT use the double constructor, new BigDecimal(0.1)
        BigDecimal value1 = new BigDecimal("0.1");
        BigDecimal value2 = new BigDecimal("0.2");

        BigDecimal five = new BigDecimal("5");
        BigDecimal two = new BigDecimal("2");

        /*
         * Math operators
         */
        System.out.println(value1 + " + " + value2 + " = " + value1.add(value2));
        System.out.println(value1 + " - " + value2 + " = " + value1.subtract(value2));
        System.out.println(value1 + " * " + value2 + " = " + value1.multiply(value2));
        System.out.println(five  + " / " + two    + " = " + five.divide(two));
        System.out.println(five  + " % " + two    + " = " + five.remainder(two));

        /*
         * Setting the number of digits after the decimal point
         */
        BigDecimal three = new BigDecimal("3");
        BigDecimal quotient = two.divide(three, 5, RoundingMode.HALF_UP);
        System.out.println();
        System.out.println("Scale 5 digits after decimal point. Round half up");
        System.out.println(two + " / " + three + " = " + quotient);

        System.out.println();
        System.out.println("Scale 8 digits after decimal point. Round half up");
        System.out.println(value1 + " * " + value2 + " = " + value1.multiply(value2).setScale(8, RoundingMode.HALF_UP));

        System.out.println();

        /*
         * Comparing BigDecimal objects
         */
        System.out.println(value1 + " compareTo " + value2 + " => " + value1.compareTo(value2));
        System.out.println(value2 + " compareTo " + value1 + " => " + value2.compareTo(value1));
        System.out.println(value1 + " compareTo " + value1 + " => " + value1.compareTo(value1));
    }


}
