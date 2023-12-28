package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Matrix3x3Test {

    @Test
    public void testMatrixAddition() {
        Matrix3x3 matrix1 = new Matrix3x3(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3x3 matrix2 = new Matrix3x3(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        Matrix3x3 result = matrix1.add(matrix2);

        double[][] expected = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testMatrixSubtraction() {
        Matrix3x3 matrix1 = new Matrix3x3(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3x3 matrix2 = new Matrix3x3(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        Matrix3x3 result = matrix1.subtract(matrix2);

        double[][] expected = {{-8, -6, -4}, {-2, 0, 2}, {4, 6, 8}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix3x3 matrix1 = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3x3 matrix2 = new Matrix3x3(new double[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}});
        Matrix3x3 result = matrix1.multiplyMatrix(matrix2);

        double[][] expected = {
                {30, 24, 18},
                {84, 69, 54},
                {138, 114, 90}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testVectorMultiplication() {
        Matrix3x3 matrix = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Vector3f v = new Vector3f(1, 2, 3);
        Vector3f result = matrix.multiplyVector(v);

        double[] expected = new double[]{14, 32, 50};
        assertArrayEquals(expected, new double[]{result.getX(), result.getY(), result.getZ()});
    }

    @Test
    public void testMatrixTranspose() {
        Matrix3x3 matrix = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3x3 result = matrix.transpose();
        assertArrayEquals(new double[][] {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}}, result.getMatrix());
    }

    @Test
    public void testIdentityMatrix() {
        Matrix3x3 identity = Matrix3x3.setIdentityMatrix();
        assertArrayEquals(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}}, identity.getMatrix());
    }

    @Test
    public void testInvalidMatrixInitialization() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] invalidMatrixData = {{1, 2, 3}, {4, 5, 6}};
            new Matrix3x3(invalidMatrixData);
        });
    }

    @Test
    public void testNullMatrix() {
        Matrix3x3 nullMatrix = Matrix3x3.setNullMatrix();
        assertArrayEquals(new double[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}}, nullMatrix.getMatrix());
    }

    @Test
    public void testMatrixDeterminant() {
        Matrix3x3 matrix = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 3, 6},
                {7, 8, 9}});
        assertEquals(24, matrix.getDet());

        matrix = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 90}});
        assertEquals(-243, matrix.getDet());

        matrix = new Matrix3x3(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        assertEquals(0, matrix.getDet());
    }
}

