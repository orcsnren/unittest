package org.soner.course.unittest.junits.basics.mincostpath;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinCostPathTest {

    private MinCostPath minCostPath;
    private static int[][] matrix;

    @BeforeAll
    public static void initMatrix() {
        matrix = new int[][]{
                {4, 5, 6, 3},
                {3, 4, 1, 4},
                {7, 2, 9, 7}
        };
    }

    @BeforeEach
    public void setUp() {
        minCostPath = new MinCostPath();
    }


    private Cell cell(int row, int col) {
        return new Cell(row, col);
    }

    @Test
    @DisplayName("Throw IllegalArgument If Cell is the Out of the Matrix Bound")
    public void throwsIllegalArgumentsExceptionWhenTheCellOutOfTheMatrixBound() {

        MinCostPath minCostPath = new MinCostPath();
        assertAll("Start cell must be in Matrix",
                () -> minCostPath.find(matrix, cell(0, 0), cell(2, 2)),
                () -> minCostPath.find(matrix, cell(2, 1), cell(2, 2)));


        assertAll("Target cell must be in Matrix",
                () -> minCostPath.find(matrix, cell(0, 0), cell(0, 2)),
                () -> minCostPath.find(matrix, cell(0, 1), cell(2, 1)));


        assertAll("Throw illegal argument",
                () -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, cell(-1, 0), cell(3, 2))),
                () -> assertThrows(IllegalArgumentException.class, () -> minCostPath.find(matrix, cell(1, 0), cell(2, 4))));


    }


    @Test
    @DisplayName("Return the cost of start cell when the start cell equals to target cell")
    public void returnTheCostWhenStartCellEqualsToTargetCell() {
        assertEquals(-1, minCostPath.find(matrix, cell(0, 0), cell(0, 0)));
        assertNotEquals(-1, minCostPath.find(matrix, cell(0, 0), cell(1, 1)));
    }

    @Test
    @DisplayName("Find the cost from the start cell to target cell")
    public void findCostFromStartCellToTargetCell(){
        assertAll("Find min costs",
                () -> assertEquals(13,minCostPath.find(matrix,cell(0,0),cell(2,1))),
                () -> assertEquals(12,minCostPath.find(matrix,cell(0,0),cell(1,2)))
        );
    }


}
