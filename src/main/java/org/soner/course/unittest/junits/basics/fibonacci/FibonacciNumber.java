package org.soner.course.unittest.junits.basics.fibonacci;

public class FibonacciNumber {

    public int find(int order) {
        if (order <= 0)
            throw new IllegalArgumentException();

        if (order <= 2)
            return 1;
        return find(order - 2) + find(order - 1);
    }
}
