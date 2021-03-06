package com.mycompany.guavatest;

import java.math.BigInteger;
import java.math.RoundingMode;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.math.BigIntegerMath;

/**
 *
 * @author jupiter
 */
public class GuavaTest {

    public static void main(String args[]) {
        GuavaTest tester = new GuavaTest();
        tester.testIntMath();
        tester.testLongMath();
        tester.testBigIntegerMath();

    }

    private void testIntMath() {
        try {
            System.out.println(IntMath.checkedAdd(Integer.MAX_VALUE,
                    Integer.MAX_VALUE));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(IntMath.divide(100, 5,
                RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisible by 3 thus rounding 
            // is required, and RoundingMode is set as UNNESSARY
            System.out.println(IntMath.divide(100, 3,
                    RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Log2(2): " + IntMath.log2(2,
                RoundingMode.HALF_EVEN));
        System.out.println("Log10(10): " + IntMath.log10(10,
                RoundingMode.HALF_EVEN));
        System.out.println("sqrt(100): " + IntMath.sqrt(IntMath.pow(10, 2),
                RoundingMode.HALF_EVEN));
        System.out.println("gcd(100,50): " + IntMath.gcd(100, 50));
        System.out.println("modulus(100,50): " + IntMath.mod(100, 50));
        System.out.println("factorial(5): " + IntMath.factorial(5));
    }

    private void testLongMath() {
        try {
            System.out.println(LongMath.checkedAdd(Long.MAX_VALUE,
                    Long.MAX_VALUE));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(LongMath.divide(100, 5,
                RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisible by 
            // 3, thus rounding is required, and
            // RoundingMode is set as UNNESSARY
            System.out.println(LongMath.divide(100, 3,
                    RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Log2(2): " + LongMath.log2(2,
                RoundingMode.HALF_EVEN));
        System.out.println("Log10(10): " + LongMath.log10(10,
                RoundingMode.HALF_EVEN));
        System.out.println("sqrt(100): " + LongMath.sqrt(LongMath.pow(10, 2),
                RoundingMode.HALF_EVEN));
        System.out.println("gcd(100,50): " + LongMath.gcd(100, 50));
        System.out.println("modulus(100,50): " + LongMath.mod(100, 50));
        System.out.println("factorial(5): " + LongMath.factorial(5));
    }

    private void testBigIntegerMath() {
        System.out.println(BigIntegerMath.divide(BigInteger.TEN, new BigInteger("2"), RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisibleby 
            // 3, thus rounding is required, and 
            // RoundingMode is set as UNNESSARY
            System.out.println(BigIntegerMath.divide(BigInteger.TEN, new BigInteger("3"), RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Log2(2): " + BigIntegerMath.log2(new BigInteger("2"), RoundingMode.HALF_EVEN));
        System.out.println(
                "Log10(10):" + BigIntegerMath.log10(BigInteger.TEN, RoundingMode.HALF_EVEN));
        System.out.println("sqrt(100):" + BigIntegerMath.sqrt(BigInteger.TEN.multiply(BigInteger.TEN), RoundingMode.HALF_EVEN
        ));
        System.out.println("factorial(5): " + BigIntegerMath.factorial(5));
    }
}
