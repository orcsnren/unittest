package org.soner.course.unittest.fizzbuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzTest {

    private FizzBuzz fizzBuz;

    @BeforeEach
    void initialize() {
        fizzBuz = new FizzBuzz();
    }

    @Test
    public void returnFizzWhenTheNumberIsDividedByThree() {

        assertEquals("Fizz", fizzBuz.stringFor(3));

    }

    @Test
    public void returnBuzzWhenTheNumberIsDividedByFive() {
        assertEquals("Buzz", fizzBuz.stringFor(5));
    }

    @Test
    public void returnFizzBuzzWhenTheNumberIsDividedBothOfThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuz.stringFor(15));
    }

    @Test
    public void returnTheNumberItselfWhenNoMatched() {
        assertEquals("7", fizzBuz.stringFor(7));
    }

    @Test
    public void throwsIllegalArgumentsExceptionWhenTheNumberIsOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> fizzBuz.stringFor(-1));
        assertThrows(IllegalArgumentException.class, () -> fizzBuz.stringFor(101));
    }
}
