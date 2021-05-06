package org.soner.course.unittest.fizzbuzz;

public class FizzBuzz {
    public String stringFor(int number) {
        if (number < 0 || number > 100)
            throw new IllegalArgumentException();
        return number % 15 == 0 ? "FizzBuzz" : number % 3 == 0 ? "Fizz" : number % 5 == 0 ? "Buzz" : String.valueOf(number);
    }
}
