package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Matrix4fTest {

    @Test
    public void testMatrixAddition() {
        Matrix4f matrix1 = new Matrix4f(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f matrix2 = new Matrix4f(new double[][]{
                {9, 8, 7, 6},
                {5, 4, 3, 2},
                {1, 2, 3, 4},
                {4, 3, 2, 1}});
        Matrix4f result = matrix1.add(matrix2);

        double[][] expected = {
                {10, 10, 10, 10},
                {10, 10, 10, 10},
                {10, 12, 14, 16},
                {17, 17, 17, 17}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testMatrixSubtraction() {
        Matrix4f matrix1 = new Matrix4f(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f matrix2 = new Matrix4f(new double[][]{
                {9, 8, 7, 6},
                {5, 4, 3, 2},
                {1, 2, 3, 4},
                {4, 3, 2, 1}});
        Matrix4f result = matrix1.subtract(matrix2);

        double[][] expected = {
                {-8, -6, -4, -2},
                {0, 2, 4, 6},
                {8, 8, 8, 8},
                {9, 11, 13, 15}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testVectorMultiplication() {
        Matrix4f m = new Matrix4f(new double[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Vector4f v = new Vector4f(1, 2, 3, 4);
        double[] result = m.multiplyVector(v).getVectorInArray();
        double[] expected = new double[] {30, 70, 110, 150};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix4f matrix1 = new Matrix4f(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        Matrix4f matrix2 = new Matrix4f(new double[][]{
                {9, 8, 7, 6},
                {5, 4, 3, 2},
                {1, 2, 3, 4},
                {4, 3, 2, 1}});
        double[][] result = matrix1.multiplyMatrix(matrix2).getMatrix();

        double[][] expected = {
                {38, 34, 30, 26},
                {114, 102, 90, 78},
                {190, 170, 150, 130},
                {266, 238, 210, 182}};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMatrixTranspose() {
        Matrix4f matrix = new Matrix4f(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}});
        double[][] result = matrix.transpose().getMatrix();

        double[][] expected = {
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 15},
                {4, 8, 12, 16}};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testIdentityMatrix() {
        double[][] identity = Matrix4f.setIdentityMatrix().getMatrix();
        double[][] expected = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};
        assertArrayEquals(expected, identity);
    }

    @Test
    public void getNullMatrix_returns4x4MatrixWithAllElementsZero() {
        double[][] matrix = Matrix4f.setNullMatrix().getMatrix();
        double[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        assertArrayEquals(expected, matrix);
    }

    @Test
    public void testInvalidMatrixInitialization() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] invalidMatrixData = {
                    {1, 2, 3},
                    {4, 5, 6}};
            new Matrix4f(invalidMatrixData);
        });
    }

    @Test
    public void testSetMatrixFromArray() {
        double[] matrixInArray = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(matrixInArray);
        assertArrayEquals(matrixInArray, matrix.getMatrixInArray());
    }

//    @Test
//    public void testGetDet() {
//        Matrix4x4 m = new Matrix4x4(new double[][]{
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}});
//        double expected = 0;
//        double result = m.getDet();
//        assertEquals(expected, result);
//    }
}


