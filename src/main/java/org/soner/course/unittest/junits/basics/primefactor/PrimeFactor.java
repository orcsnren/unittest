package org.soner.course.unittest.junits.basics.primefactor;

import org.soner.course.unittest.courserecord.exceptions.PrimeFactorException;
import org.soner.course.unittest.interfaces.IPrimeFactor;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactor implements IPrimeFactor {

    @Override
    public List<Integer> generate(int number) throws PrimeFactorException {
        List<Integer> primeFactors = new ArrayList<>();

        if (number <= 0) {
            throw new PrimeFactorException("Number is out of calculation");
        }

        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                primeFactors.add(i);
                number = number / i;
            }
        }

        return primeFactors;
    }
}
