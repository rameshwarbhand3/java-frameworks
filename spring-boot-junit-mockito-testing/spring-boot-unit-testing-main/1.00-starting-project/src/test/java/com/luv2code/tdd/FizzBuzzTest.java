package com.luv2code.tdd;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {
    //If the number is divisible by 3,print FIZZ
    //If the number is divisible by 5,print BUZZ
    //If the number is divisible by 3 and 5,print FIZZBUZZ
    //If the number is not divisible by 3 or 5,print number
    @DisplayName("Divisible by three")
    @Test
    @Order(1)
    public void testfordivisiblebythree() {
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.compute(6), "should be divisible by 3");
        assertNotEquals(expected, FizzBuzz.compute(7));
    }
    @DisplayName("Divisible by five")
    @Test
    @Order(2)
    public void testfordivisiblebyfive() {
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5), "should be divisible by 5");
        assertNotEquals(expected, FizzBuzz.compute(7));
    }

    @DisplayName("Divisible by three and five")
    @Test
    @Order(3)
    public void testfordivisiblebyThreeAndfive() {
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.compute(15), "should be divisible by 3 and 5");
        assertNotEquals(expected, FizzBuzz.compute(7));
    }

    @Test
    @DisplayName("Not divisible by three or five")
    @Order(4)
    public void testforNotDivisiblebyThreeOrfive() {
        String expected = "1";
        assertEquals(expected, FizzBuzz.compute(1), "should not be divisible by 3 and 5");
        assertNotEquals(expected, FizzBuzz.compute(3));
    }

    @DisplayName("Testing with small test data from file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    public void testForSmallTestData(int value,String expected) {
       assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with medium test data from file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(6)
    public void testForMediumTestData(int value,String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with large test data from file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @Order(7)
    public void testForLargeTestData(int value,String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

}
