package org.soner.course.unittest.junits.basics.primefactor;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.soner.course.unittest.courserecord.exceptions.PrimeFactorException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeFactorTest {

    PrimeFactor primeFactor;
    private Map<Integer, List<Integer>> primeFactorExpectations = new HashMap<>();


    @BeforeEach
    public void init() {
        primeFactor = new PrimeFactor();
        primeFactorExpectations.put(1, Collections.EMPTY_LIST);
        primeFactorExpectations.put(2, List.of(2));
        primeFactorExpectations.put(3, List.of(3));
        primeFactorExpectations.put(4, List.of(2, 2));
        primeFactorExpectations.put(5, List.of(5));
        primeFactorExpectations.put(6, List.of(2, 3));
        primeFactorExpectations.put(7, List.of(7));
        primeFactorExpectations.put(8, List.of(2, 2, 2));
        primeFactorExpectations.put(9, List.of(3, 3));
    }

    @Test
    @DisplayName("Throws when the number is negative")
    public void throwsWhenTheNumberIsNegative() {
        final PrimeFactorException primeFactorException = Assertions.assertThrows(PrimeFactorException.class, () -> primeFactor.generate(0));
        assertEquals("Number is out of calculation", primeFactorException.getMessage());
    }

    @Test
    @DisplayName("Generate prime factors")
    public void generatePrimeFactors() {
        assertAll("Prime Factors Equals",
                () -> assertEquals(List.of(2, 5), primeFactor.generate(10)),
                () -> assertEquals(List.of(2, 2, 3), primeFactor.generate(12)),
                () -> assertEquals(List.of(5, 7), primeFactor.generate(35)),
                () -> assertEquals(List.of(2, 5, 5), primeFactor.generate(50)),
                () -> assertEquals(List.of(2, 2, 2, 2, 7), primeFactor.generate(112))
        );

        assertAll("Prime Factors Not Equals",
                () -> assertNotEquals(List.of(10), primeFactor.generate(10)),
                () -> assertNotEquals(List.of(2, 6), primeFactor.generate(12)),
                () -> assertNotEquals(List.of(7, 5), primeFactor.generate(35)),
                () -> assertNotEquals(List.of(2, 3, 5), primeFactor.generate(50)),
                () -> assertNotEquals(List.of(2, 2, 4, 7), primeFactor.generate(112))
        );

    }

    @RepeatedTest(9)
    void generateWithRepeatedTest(RepetitionInfo repetitionInfo) {
        assertEquals(primeFactorExpectations.get(repetitionInfo.getCurrentRepetition()), primeFactor.generate(repetitionInfo.getCurrentRepetition()));
    }

    @ParameterizedTest(name = "Generate Prime Factors for {arguments}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void generateWitParameterizedTest(Integer number) {
        assertEquals(primeFactorExpectations.get(number), primeFactor.generate(number));
    }

    @TestFactory
    Stream<DynamicTest> generateWithDynamicTest() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(number -> DynamicTest.dynamicTest("Generate prime factors for " + number,
                        () -> assertEquals(primeFactorExpectations.get(number), primeFactor.generate(number))));
    }

}
