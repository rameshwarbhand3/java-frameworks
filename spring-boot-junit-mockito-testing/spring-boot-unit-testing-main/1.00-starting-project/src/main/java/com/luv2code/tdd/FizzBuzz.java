package com.luv2code.tdd;

public class FizzBuzz {
    public static String compute(int num) {
        if (num % 3 == 0 && num % 5 == 0) {
            return "FizzBuzz";
        } else if (num % 3 == 0) {
            return "Fizz";
        } else if (num % 5 == 0) {
            return "Buzz";
        }else
        return Integer.toString(num);
    }

    public static void main(String args[]) {
        System.out.println(FizzBuzz.compute(7));
    }
}

