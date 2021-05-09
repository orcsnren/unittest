package org.soner.course.unittest.junits.basics.fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciNumberTest {

    @Test
    @DisplayName("Find Fibonacci Numbers For Specific Order")
    public void findFibonacciNumbers() {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();

        assertThrows(IllegalArgumentException.class, () -> fibonacciNumber.find(0));

        Assertions.assertAll("Fibonacci Numbers",
                () -> assertEquals(1, fibonacciNumber.find(1)),
                () -> assertEquals(1, fibonacciNumber.find(2)),
                () -> assertEquals(2, fibonacciNumber.find(3)),
                () -> assertEquals(3, fibonacciNumber.find(4)),
                () -> assertEquals(5, fibonacciNumber.find(5)),
                () -> assertEquals(8, fibonacciNumber.find(6)),
                () -> assertEquals(13, fibonacciNumber.find(7)),
                () -> assertNotEquals(22, fibonacciNumber.find(8)));
    }

}
