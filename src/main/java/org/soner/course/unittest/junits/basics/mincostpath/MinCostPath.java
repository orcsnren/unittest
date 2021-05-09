package org.soner.course.unittest.junits.basics.mincostpath;

public class MinCostPath {


    public int find(int[][] matrix, Cell start, Cell target) {

        validateIfTheCellIsInMatrixBound(matrix, start);
        validateIfTheCellIsInMatrixBound(matrix, target);

        if (start.equals(target)) {
            return -1;
        }
        return findMinCostPath(matrix, start, target);
    }

    private void validateIfTheCellIsInMatrixBound(int[][] matrix, Cell cell) {
        if (cell.getRow() < 0 || cell.getRow() >= matrix.length) {
            throw new IllegalArgumentException();
        }

        if (cell.getColumn() < 0 || cell.getColumn() >= matrix[0].length) {
            throw new IllegalArgumentException();
        }
    }


    private int findMinCostPath(int[][] matrix, Cell start, Cell target) {

        if (start.getRow() > target.getRow() || start.getColumn() > target.getColumn()) {
            return Integer.MAX_VALUE;
        }

        if (start.equals(target)) {
            return matrix[start.getRow()][start.getColumn()];
        }

        int rightPathCost = findMinCostPath(matrix, new Cell(start.getRow(), start.getColumn() + 1), target);
        int downPathCost = findMinCostPath(matrix, new Cell(start.getRow() + 1, start.getColumn()), target);
//        int diagonalPathCost = findMinCostPath(matrix, new Cell(start.getRow() + 1, start.getColumn() + 1), target);

        final int min = Math.min(rightPathCost, downPathCost);

        return min + matrix[start.getRow()][start.getColumn()];

    }
}
