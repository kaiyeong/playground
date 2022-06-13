package com.ky.playground;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class PrimeNumber {
    public static int squareRoot(int number) {
        double temp;
        double t = 0;
        temp = number/2;
        while(temp != t) {
            t = temp;
            temp = (number/temp + temp)/2;
        }
        return (int)temp;
    }

    public static int sqrtMath(int number) {
        return (int)Math.sqrt(number);
    }

    public static boolean isPrime(int number) {
        return number > 1
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }

    public static boolean isPrimeNumber(int n, boolean usingMathLib) {
        boolean isPrimeNumber = true;

        if(n == 0 || n == 1) {
            isPrimeNumber = false;
        } else {
            int sqrt = 0;
            if(usingMathLib) {
                sqrt = sqrtMath(n);
            } else {
                sqrt = squareRoot(n);
            }
            for(int i = 2; i < sqrt; i++) {
                if((n % i) == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
        }

        return isPrimeNumber;
    }

    public static int nextPrimeNumber(int n,  boolean usingMathLib) {
        while(true) {
            if(PrimeNumber.isPrimeNumber(n, usingMathLib)) {
                return n;
            }
            n++;
        }
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        for(int j = 0; j < 1000; j++) {
            System.out.println( j + " nextPrimeNumber : " + PrimeNumber.nextPrimeNumber(j, false));
        }
        System.out.println(Integer.MAX_VALUE + " nextPrimeNumber : " + PrimeNumber.nextPrimeNumber(Integer.MAX_VALUE, false));
        Instant end = Instant.now();
        System.out.println("total process time "+ Duration.between(start, end).toMillis() + "ms");


        start = Instant.now();
        for(int j = 0; j < 1000; j++) {
            System.out.println( j + " nextPrimeNumber : " + PrimeNumber.nextPrimeNumber(j, true));
        }
        System.out.println(Integer.MAX_VALUE + " nextPrimeNumber : " + PrimeNumber.nextPrimeNumber(Integer.MAX_VALUE, true));
        end = Instant.now();
        System.out.println("total process time using math lib "+ Duration.between(start, end).toMillis() + "ms");
    }
}


