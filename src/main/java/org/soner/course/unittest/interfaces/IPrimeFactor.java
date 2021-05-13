package org.soner.course.unittest.interfaces;

import org.soner.course.unittest.courserecord.exceptions.PrimeFactorException;

import java.util.List;

public interface IPrimeFactor {

    List<Integer> generate(int number) throws PrimeFactorException;
}
